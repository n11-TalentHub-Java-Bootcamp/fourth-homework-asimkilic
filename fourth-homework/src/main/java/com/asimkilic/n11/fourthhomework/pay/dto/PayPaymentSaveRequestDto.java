package com.asimkilic.n11.fourthhomework.pay.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Schema(name="Yeni ödeme işlemi oluşturulurken kullanılır",description = "Gecikme zamları ödemelerin içine dahildir, gecikme zammı için kullanılmaz")
public class PayPaymentSaveRequestDto {

    @NotNull
    private String debtId;

    @NotNull
    @Min(value=0,message = "Eksi değer giremezsiniz")
    private BigDecimal debtPrice;

}
