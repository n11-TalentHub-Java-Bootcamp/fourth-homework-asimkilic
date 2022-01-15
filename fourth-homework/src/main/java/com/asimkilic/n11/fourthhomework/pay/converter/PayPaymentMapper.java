package com.asimkilic.n11.fourthhomework.pay.converter;

import com.asimkilic.n11.fourthhomework.dbt.converter.DbtDebtMapper;
import com.asimkilic.n11.fourthhomework.dbt.dto.DbtDebtDto;
import com.asimkilic.n11.fourthhomework.pay.dto.PayPaymentDto;
import com.asimkilic.n11.fourthhomework.pay.dto.PayPaymentSaveRequestDto;
import com.asimkilic.n11.fourthhomework.pay.entity.PayPayment;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PayPaymentMapper {
    PayPaymentMapper INSTANCE = Mappers.getMapper(PayPaymentMapper.class);

    @Mapping(source = "payPayedPrice", target = "payedPrice")
    PayPaymentDto convertToPayPaymentDto(PayPayment from);

    @AfterMapping
    default void setDbtDebtDto(@MappingTarget PayPaymentDto payPaymentDto, PayPayment payPayment) {
        DbtDebtMapper INSTANCE_DBTDEBTMAPPER = Mappers.getMapper(DbtDebtMapper.class);
        payPaymentDto.setDbtDebtDto(INSTANCE_DBTDEBTMAPPER.convertToDbtDebtDto(payPayment.getDbtDebt()));
        payPaymentDto.getDbtDebtDto()
                .setTotalDebt(BigDecimal.ZERO
                );
    }

    List<PayPaymentDto> convertToPayPaymentDtoList(List<PayPayment> from);

    @Mapping(source = "debtId", target = "dbtDebt.id")
    @Mapping(source = "debtPrice", target = "payPayedPrice")
    PayPayment convertToPayPayment(PayPaymentSaveRequestDto from);
}
