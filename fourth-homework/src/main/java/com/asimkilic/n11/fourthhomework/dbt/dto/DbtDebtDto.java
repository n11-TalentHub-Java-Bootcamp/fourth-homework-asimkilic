package com.asimkilic.n11.fourthhomework.dbt.dto;

import com.asimkilic.n11.fourthhomework.dbt.entity.DbtDebt;
import com.asimkilic.n11.fourthhomework.dbt.enums.EnumDebtType;
import com.asimkilic.n11.fourthhomework.usr.entity.UsrUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Schema(name="Mevcut borç görüntülemek için kullanılır.")
public class DbtDebtDto {

    private String id;

    private String dbtDebtId; //bağlı borç id

    private String usrUserId; //borç sahibi

    private BigDecimal mainDebt; //anaborç

    private BigDecimal remainingDebt; //kalan borç

    //private EnumDebtType debtType; //borç türü

    private LocalDateTime creationDate; //oluşturulma zamanı

    private LocalDateTime fallDueOn;  // vade tarihi
    private BigDecimal lateFeeDebt=BigDecimal.ZERO;
    private BigDecimal totalDebt;

}
