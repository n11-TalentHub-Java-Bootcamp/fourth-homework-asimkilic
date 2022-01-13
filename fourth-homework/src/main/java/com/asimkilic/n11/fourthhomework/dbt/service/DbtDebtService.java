package com.asimkilic.n11.fourthhomework.dbt.service;

import com.asimkilic.n11.fourthhomework.dbt.converter.DbtDebtMapper;
import com.asimkilic.n11.fourthhomework.dbt.dto.DbtDebtDto;
import com.asimkilic.n11.fourthhomework.dbt.dto.DbtDebtSaveRequestDto;
import com.asimkilic.n11.fourthhomework.dbt.entity.DbtDebt;
import com.asimkilic.n11.fourthhomework.dbt.enums.EnumDebtType;
import com.asimkilic.n11.fourthhomework.dbt.exception.DbtDebtFallDueOnCantBeforeNowException;
import com.asimkilic.n11.fourthhomework.dbt.service.entityservice.DbtDebtEntityService;
import com.asimkilic.n11.fourthhomework.usr.service.UsrUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import static com.asimkilic.n11.fourthhomework.dbt.converter.DbtDebtMapper.INSTANCE;

@Service
@RequiredArgsConstructor
public class DbtDebtService {
    private final DbtDebtEntityService dbtDebtEntityService;
    private final UsrUserService usrUserService;
    private final Clock clock;

    public DbtDebtDto saveDbtDebt(DbtDebtSaveRequestDto saveRequestDto){
        if(saveRequestDto.getFallDueOn().isBefore(getLocalDateTimeNow())){
            throw new DbtDebtFallDueOnCantBeforeNowException("FallDueOn cant before today.");
        }

        DbtDebt dbtDebt = INSTANCE.convertToDbtDebt(saveRequestDto);
        dbtDebt.setRemainingDebt(dbtDebt.getMainDebt()); // İlk borç eklenirken ödenmemiş borç ana borca eşittir
        dbtDebt.setDebtType(EnumDebtType.NORMAL);
        dbtDebt.setCreationDate(getLocalDateTimeNow());
        dbtDebt.setDbtDebt(null);
        dbtDebt  = dbtDebtEntityService.save(dbtDebt);

        return INSTANCE.convertToDbtDebtDto(dbtDebt);

    }
    public List<DbtDebtDto> findAllDbtDebt(){
        List<DbtDebt> dbtDebtList = dbtDebtEntityService.findAll();
        List<DbtDebtDto> dbtDebtDtoList = INSTANCE.convertToDbtDebtDtoList(dbtDebtList);
        return dbtDebtDtoList;
    }


    private LocalDateTime getLocalDateTimeNow(){
        // Test edilebilirlik için
        Instant instant = clock.instant();
        return LocalDateTime.ofInstant(instant,Clock.systemDefaultZone().getZone());
    }
}
