package com.asimkilic.n11.fourthhomework.pay.dto;

import com.asimkilic.n11.fourthhomework.dbt.dto.DbtDebtDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(name = "Ödeme kaydı görüntülemek için kullanılır.")
public class PayPaymentDto {

    private String id;

    private BigDecimal payedPrice;

    private LocalDateTime creationDate;

    private DbtDebtDto dbtDebtDto;
}
