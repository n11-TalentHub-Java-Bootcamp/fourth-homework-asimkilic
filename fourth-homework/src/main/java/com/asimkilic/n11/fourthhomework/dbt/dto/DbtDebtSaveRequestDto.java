package com.asimkilic.n11.fourthhomework.dbt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Schema(name="Yeni bir borç oluştururken kullanılır.", description = "Gecikme sebebi ile doğan borçların eklenmesi otomatik yapılmaktadır. Sadece yeni borçlar için kullanın.")
public class DbtDebtSaveRequestDto {


    @NotNull
    private String  usrUserId; //borç sahibi

    @NotNull
    @Min(value=0,message = "Eksi değer giremezsiniz")
    private BigDecimal mainDebt; //anaborç

//    private BigDecimal remainingDebt; //kalan borç anaborca eşitlenecek ilk borç oluşturma

//    private EnumDebtType debtType; //borç türü Normal Borç olmalı

   // private LocalDateTime creationDate; //oluşturulma zamanı service katmanında verilmeli

    private LocalDateTime fallDueOn;  // vade tarihi
    //TODO : admin id eklenecek
}
