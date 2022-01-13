package com.asimkilic.n11.fourthhomework.dbt.service;

import com.asimkilic.n11.fourthhomework.dbt.converter.DbtDebtMapper;
import com.asimkilic.n11.fourthhomework.dbt.dto.DbtDebtBeetweenDatesRequestDto;
import com.asimkilic.n11.fourthhomework.dbt.dto.DbtDebtDto;
import com.asimkilic.n11.fourthhomework.dbt.dto.DbtDebtSaveRequestDto;
import com.asimkilic.n11.fourthhomework.dbt.entity.DbtDebt;
import com.asimkilic.n11.fourthhomework.dbt.enums.EnumDebtType;
import com.asimkilic.n11.fourthhomework.dbt.exception.DbtDebtFallDueOnCantBeforeNowException;
import com.asimkilic.n11.fourthhomework.dbt.service.entityservice.DbtDebtEntityService;
import com.asimkilic.n11.fourthhomework.gen.LateFees;
import com.asimkilic.n11.fourthhomework.usr.dto.UsrUserDto;
import com.asimkilic.n11.fourthhomework.usr.exception.UsrUserNotFoundException;
import com.asimkilic.n11.fourthhomework.usr.service.UsrUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.asimkilic.n11.fourthhomework.dbt.converter.DbtDebtMapper.INSTANCE;

@Service
@RequiredArgsConstructor
public class DbtDebtService {
    private final DbtDebtEntityService dbtDebtEntityService;
    private final UsrUserService usrUserService;
    private final Clock clock;

    public DbtDebtDto saveDbtDebt(DbtDebtSaveRequestDto saveRequestDto) {
        if (saveRequestDto.getFallDueOn().isBefore(getLocalDateTimeNow())) {
            throw new DbtDebtFallDueOnCantBeforeNowException("Vade tarihi geçmiş gün olamaz.");
        }

        DbtDebt dbtDebt = INSTANCE.convertToDbtDebt(saveRequestDto);
        dbtDebt.setRemainingDebt(dbtDebt.getMainDebt()); // İlk borç eklenirken ödenmemiş borç ana borca eşittir
        dbtDebt.setDebtType(EnumDebtType.NORMAL);
        dbtDebt.setCreationDate(getLocalDateTimeNow());
        dbtDebt.setDbtDebt(null);
        dbtDebt = dbtDebtEntityService.save(dbtDebt);

        return INSTANCE.convertToDbtDebtDto(dbtDebt);

    }

    public List<DbtDebtDto> findAllDbtDebt() {
        return dbtDebtEntityService
                .findAll()
                .stream()
                .map(INSTANCE::convertToDbtDebtDto)
                .map(this::calculateTotalDebt)
                .collect(Collectors.toList());
    }

    public List<DbtDebtDto> findAllDbtDebtBetweenDates(DbtDebtBeetweenDatesRequestDto dbtDebtBeetweenDatesRequestDto) {
        return dbtDebtEntityService
                .findAllDbtDebtBetweenDates(dbtDebtBeetweenDatesRequestDto.getStartDate(),
                        dbtDebtBeetweenDatesRequestDto.getEndDate())
                .stream()
                .filter(x -> dbtDebtBeetweenDatesRequestDto.getUsrUserId() == null ||
                        Objects.equals(x.getUsrUser().getId(), dbtDebtBeetweenDatesRequestDto.getUsrUserId()))
                .map(INSTANCE::convertToDbtDebtDto)
                .map(this::calculateTotalDebt)
                .collect(Collectors.toList());
    }

    public List<DbtDebtDto> findAllUnpaidDbtDebtByUserId(String usrUserId) {
        checkIsUsrUserExist(usrUserId);

        return dbtDebtEntityService
                .findAllUnpaidDbtDebtByUserId(usrUserId)
                .stream()
                .map(INSTANCE::convertToDbtDebtDto)
                .map(this::calculateTotalDebt)
                .collect(Collectors.toList());
    }

    public List<DbtDebtDto> findAllUnpaidOverDueDbtDebtByUserId(String usrUserId) {
        checkIsUsrUserExist(usrUserId);

        return dbtDebtEntityService
                .findAllUnpaidOverdueDbtDebtByUserId(usrUserId, getLocalDateTimeNow())
                .stream()
                .map(INSTANCE::convertToDbtDebtDto)
                .map(this::calculateTotalDebt)
                .collect(Collectors.toList());

    }

    public BigDecimal findUnpaidTotalDbtDebtsByUserId(String usrUserId) {
        checkIsUsrUserExist(usrUserId);

        return dbtDebtEntityService.findAllUnpaidDbtDebtByUserId(usrUserId)
                .stream()
                .map(INSTANCE::convertToDbtDebtDto)
                .map(this::calculateTotalDebt)
                .map(DbtDebtDto::getTotalDebt)
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

    }

    private LocalDateTime getLocalDateTimeNow() {
        // Test edilebilirlik için
        Instant instant = clock.instant();
        return LocalDateTime.ofInstant(instant, Clock.systemDefaultZone().getZone());
    }

    private Duration getDurationBetweenDates(LocalDateTime start, LocalDateTime end) {
        return Duration.between(start, end);
    }

    // Use this method for calculation, pass list reference
    private void calculateTotalDebtList(List<DbtDebtDto> dbtDebtDtoList) {
        dbtDebtDtoList.forEach(debt -> {
            debt.setTotalDebt(debt.getRemainingDebt());
            if (debt.getFallDueOn().isBefore(getLocalDateTimeNow())) {
                debt.setTotalDebt(debt.getTotalDebt().add(calculateLateFee(debt.getFallDueOn())));
            }
            debt.setLateFeeDebt(debt.getTotalDebt().subtract(debt.getRemainingDebt()));
        });
    }

    private DbtDebtDto calculateTotalDebt(DbtDebtDto debt) {

        debt.setTotalDebt(debt.getRemainingDebt());
        if (debt.getFallDueOn().isBefore(getLocalDateTimeNow())) {
            debt.setTotalDebt(debt.getTotalDebt().add(calculateLateFee(debt.getFallDueOn())));
        }
        debt.setLateFeeDebt(debt.getTotalDebt().subtract(debt.getRemainingDebt()));
        return debt;
    }

    // This method calculates only late fee.Use calculateTotalDebt. It calculates late fee and sets.
    private BigDecimal calculateLateFee(LocalDateTime fallDueOn) {
        long diff = Duration.between(fallDueOn, getLocalDateTimeNow()).toDays();

        if (fallDueOn.isAfter(LocalDateTime.of(2018, 1, 1, 0, 0))) {
            return (BigDecimal.valueOf(diff).multiply(LateFees.NORMAL)).setScale(0, RoundingMode.CEILING);
        }
        return (BigDecimal.valueOf(diff).multiply(LateFees.OLDER_THAN_2018)).setScale(0, RoundingMode.CEILING);
    }

    private void checkIsUsrUserExist(String usrUserId) {
        boolean isUserRegistered = dbtDebtEntityService.findIsUserRegisteredNative(usrUserId) > 0;

        if (!isUserRegistered) {
            throw new UsrUserNotFoundException("Kullanıcı bulunamadı.  ID: " + usrUserId);
        }

    }


}
