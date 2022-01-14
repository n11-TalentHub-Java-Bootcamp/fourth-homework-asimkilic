package com.asimkilic.n11.fourthhomework.dbt.converter;

import com.asimkilic.n11.fourthhomework.dbt.dto.DbtDebtDto;
import com.asimkilic.n11.fourthhomework.dbt.dto.DbtDebtLateFeeSaveRequestDto;
import com.asimkilic.n11.fourthhomework.dbt.dto.DbtDebtSaveRequestDto;
import com.asimkilic.n11.fourthhomework.dbt.entity.DbtDebt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DbtDebtMapper {
    DbtDebtMapper INSTANCE = Mappers.getMapper(DbtDebtMapper.class);

    @Mapping(source = "dbtDebt.id", target = "dbtDebtId")
    @Mapping(source = "usrUser.id", target = "usrUserId")
    DbtDebtDto convertToDbtDebtDto(DbtDebt from);

    @Mapping(source = "dbtDebt.id", target = "dbtDebtId")
    @Mapping(source = "userUser.id", target = "usrUserId")
    List<DbtDebtDto> convertToDbtDebtDtoList(List<DbtDebt> from);

    @Mapping(source = "usrUserId", target = "usrUser.id")
    DbtDebt convertToDbtDebt(DbtDebtSaveRequestDto from);

    @Mapping(source = "topDbtDebtId", target = "dbtDebt.id")
    @Mapping(source = "usrUserId", target = "usrUser.id")
    DbtDebt convertToDbtDebt(DbtDebtLateFeeSaveRequestDto from);
}
