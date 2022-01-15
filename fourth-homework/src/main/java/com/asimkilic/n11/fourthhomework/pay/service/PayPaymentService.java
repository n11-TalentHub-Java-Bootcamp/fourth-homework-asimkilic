package com.asimkilic.n11.fourthhomework.pay.service;

import com.asimkilic.n11.fourthhomework.dbt.dto.DbtDebtDto;
import com.asimkilic.n11.fourthhomework.dbt.dto.DbtDebtLateFeeSaveRequestDto;
import com.asimkilic.n11.fourthhomework.dbt.entity.DbtDebt;
import com.asimkilic.n11.fourthhomework.dbt.service.DbtDebtService;
import com.asimkilic.n11.fourthhomework.gen.LateFees;
import com.asimkilic.n11.fourthhomework.pay.dto.PayPaymentBetweenDatesRequestDto;
import com.asimkilic.n11.fourthhomework.pay.dto.PayPaymentDto;
import com.asimkilic.n11.fourthhomework.pay.dto.PayPaymentSaveRequestDto;
import com.asimkilic.n11.fourthhomework.pay.entity.PayPayment;
import com.asimkilic.n11.fourthhomework.pay.exception.PayPaymentDebtCouldNotFoundException;
import com.asimkilic.n11.fourthhomework.pay.exception.PayPaymentPriceNotEqualToDebtException;
import com.asimkilic.n11.fourthhomework.pay.service.entityservice.PayPaymentEntityService;
import com.asimkilic.n11.fourthhomework.usr.entity.UsrUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.asimkilic.n11.fourthhomework.pay.converter.PayPaymentMapper.INSTANCE;

@Service
@RequiredArgsConstructor
public class PayPaymentService {

    private final PayPaymentEntityService payPaymentEntityService;
    private final DbtDebtService dbtDebtService;
    private final Clock clock;


    public List<PayPaymentDto> findAllPayPayment() {
        return payPaymentEntityService
                .findAll()
                .stream()
                .map(INSTANCE::convertToPayPaymentDto)
                .collect(Collectors.toList());
    }

    public List<PayPaymentDto> findAllPayPaymentByUserId(String id) {
        return payPaymentEntityService
                .findAllByUserId(id)
                .stream()
                .map(INSTANCE::convertToPayPaymentDto)
                .collect(Collectors.toList());
    }

    public BigDecimal findTotalLateFeePayPaymentByUserId(String usrUserId) {
        return payPaymentEntityService
                .findAllByUserId(usrUserId)
                .stream()
                .map(INSTANCE::convertToPayPaymentDto)
                .filter(x -> x.getDbtDebtDto().getDbtDebtId() != null)
                .map(PayPaymentDto::getPayedPrice)
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public PayPaymentDto savePayPayment(PayPaymentSaveRequestDto payPaymentSaveRequestDto) {
        DbtDebtDto unpaidDbtDto = dbtDebtService.findUnpaidDbtDebtByDebtIdForPaymentService(payPaymentSaveRequestDto.getDebtId());
        if (unpaidDbtDto == null) {
            throw new PayPaymentDebtCouldNotFoundException("Id'ye ait borç bulunamadı. ID: " + payPaymentSaveRequestDto.getDebtId());
        }

        if (!Objects.equals(payPaymentSaveRequestDto.getDebtPrice(), unpaidDbtDto.getTotalDebt())) {
            throw new PayPaymentPriceNotEqualToDebtException("Ödenen miktar borç ile aynı değil. Ödenmesi gereken miktar: " + unpaidDbtDto.getTotalDebt());
        }

        //  ana borcun kalan ödemesini sıfırla
        String paidDebtId = dbtDebtService.resetRemaningDebt(payPaymentSaveRequestDto.getDebtId());
        DbtDebt dbtDebt = new DbtDebt();
        dbtDebt.setId(paidDebtId);
        UsrUser usrUser = new UsrUser();
        usrUser.setId(unpaidDbtDto.getUsrUserId());
        // sıfırlanan miktar kadar tahsilat kaydı gir
        PayPayment payPayment = new PayPayment();
        payPayment.setUsrUser(usrUser);
        payPayment.setCreationDate(getLocalDateTimeNow());
        payPayment.setPayPayedPrice(unpaidDbtDto.getRemainingDebt());
        payPayment.setDbtDebt(dbtDebt);
        payPaymentEntityService.save(payPayment);

        //gecikme zammı varsa borç kaydı oluştur
        if (unpaidDbtDto.getLateFeeDebt().compareTo(BigDecimal.ZERO) > 0) {
            DbtDebtLateFeeSaveRequestDto lateFeeDto = new DbtDebtLateFeeSaveRequestDto();
            lateFeeDto.setTopDbtDebtId(paidDebtId);
            lateFeeDto.setUsrUserId(unpaidDbtDto.getUsrUserId());
            lateFeeDto.setMainDebt(unpaidDbtDto.getLateFeeDebt());
            DbtDebtDto lateFeeDbtDebt = dbtDebtService.savePaidLateFeeDbtDebt(lateFeeDto);
            // gecikme zammının tahsilat kaydını gir
            PayPayment payPaymentLateFee = new PayPayment();
            payPaymentLateFee.setCreationDate(getLocalDateTimeNow());
            payPaymentLateFee.setPayPayedPrice(unpaidDbtDto.getLateFeeDebt());
            DbtDebt dbtDebtForLateFee = new DbtDebt();
            dbtDebtForLateFee.setId(lateFeeDbtDebt.getId());
            payPaymentLateFee.setDbtDebt(dbtDebtForLateFee);
            payPaymentLateFee.setUsrUser(usrUser);
            payPaymentEntityService.save(payPaymentLateFee);

        }

        return INSTANCE.convertToPayPaymentDto(payPayment);
    }

    public List<PayPaymentDto> findAllPaymentsBetweenDates(PayPaymentBetweenDatesRequestDto payPaymentBetweenDatesRequestDto) {
        return payPaymentEntityService
                .findAllPaymentsBetweenDates(
                        payPaymentBetweenDatesRequestDto.getStartDate(),
                        payPaymentBetweenDatesRequestDto.getEndDate()
                )
                .stream()
                .map(INSTANCE::convertToPayPaymentDto)
                .collect(Collectors.toList());
    }


    private LocalDateTime getLocalDateTimeNow() {
        // Test edilebilirlik için
        Instant instant = clock.instant();
        return LocalDateTime.ofInstant(instant, Clock.systemDefaultZone().getZone());
    }


}
