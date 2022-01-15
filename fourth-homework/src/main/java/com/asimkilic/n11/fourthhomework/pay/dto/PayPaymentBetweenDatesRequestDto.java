package com.asimkilic.n11.fourthhomework.pay.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "İki tarih arasında yapılacak sorgular için kullanılır")
public class PayPaymentBetweenDatesRequestDto {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
