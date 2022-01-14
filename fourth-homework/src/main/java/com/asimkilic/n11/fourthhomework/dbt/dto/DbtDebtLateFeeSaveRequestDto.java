package com.asimkilic.n11.fourthhomework.dbt.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class DbtDebtLateFeeSaveRequestDto {
    @NotNull
    private String topDbtDebtId;
    @NotNull
    private String usrUserId;
    @NotNull
    @Min(value=0,message = "Eksi değer giremezsiniz")
    private BigDecimal mainDebt;

}
