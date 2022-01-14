package com.asimkilic.n11.fourthhomework.dbt.entity;

import com.asimkilic.n11.fourthhomework.dbt.enums.EnumDebtType;
import com.asimkilic.n11.fourthhomework.gen.entity.BaseEntity;
import com.asimkilic.n11.fourthhomework.pay.entity.PayPayment;
import com.asimkilic.n11.fourthhomework.usr.entity.UsrUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "DBT_DEBT")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DbtDebt implements Serializable, BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="dbt_debt_id",nullable = true)
    private DbtDebt dbtDebt; //bağlı borç id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="usr_user_id", nullable = false)
    private UsrUser usrUser; //borç sahibi

    @Column(updatable = false,precision = 11,scale = 2)
    private BigDecimal mainDebt; //anaborç

    @Column(precision = 11,scale = 2)
    private BigDecimal remainingDebt; //kalan borç

    private EnumDebtType debtType; //borç türü

    @Column(nullable = false)
    private LocalDateTime creationDate; //oluşturulma zamanı


    private LocalDateTime fallDueOn;  // vade tarihi


    @OneToMany(mappedBy ="dbtDebt",fetch = FetchType.LAZY)
    private Set<PayPayment> payments = new HashSet<>();


}
