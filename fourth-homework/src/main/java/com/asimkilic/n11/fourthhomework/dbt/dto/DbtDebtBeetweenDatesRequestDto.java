package com.asimkilic.n11.fourthhomework.dbt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Schema(name="Belirtilen iki tarih arasındaki borç istekleri için kullanılır.",description = "Kullanıcı id borç sahibinin ID'sidir.")
public class DbtDebtBeetweenDatesRequestDto {

    private String usrUserId;
    @NotNull
    private LocalDateTime startDate;
    @NotNull
    private LocalDateTime endDate;

}
