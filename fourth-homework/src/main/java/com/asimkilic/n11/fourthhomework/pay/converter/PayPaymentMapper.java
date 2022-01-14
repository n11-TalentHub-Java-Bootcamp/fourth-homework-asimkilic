package com.asimkilic.n11.fourthhomework.pay.converter;

import com.asimkilic.n11.fourthhomework.dbt.dto.DbtDebtDto;
import com.asimkilic.n11.fourthhomework.pay.dto.PayPaymentDto;
import com.asimkilic.n11.fourthhomework.pay.dto.PayPaymentSaveRequestDto;
import com.asimkilic.n11.fourthhomework.pay.entity.PayPayment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PayPaymentMapper {
    PayPaymentMapper INSTANCE = Mappers.getMapper(PayPaymentMapper.class);

    PayPaymentDto convertToPayPaymentDto(PayPayment from);

    List<PayPaymentDto> convertToPayPaymentDtoList(List<PayPayment> from);

    @Mapping(source="debtId",target="dbtDebt.id")
    @Mapping(source = "debtPrice",target = "payPayedPrice")
    PayPayment convertToPayPayment(PayPaymentSaveRequestDto from);
}
