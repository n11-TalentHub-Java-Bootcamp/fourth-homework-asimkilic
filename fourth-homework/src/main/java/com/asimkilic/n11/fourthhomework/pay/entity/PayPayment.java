package com.asimkilic.n11.fourthhomework.pay.entity;

import com.asimkilic.n11.fourthhomework.dbt.entity.DbtDebt;
import com.asimkilic.n11.fourthhomework.gen.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "PAY_PAYMENT")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PayPayment implements Serializable, BaseEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="dbt_debt_id", nullable = false)
    private DbtDebt dbtDebt; //bağlı borç id

    @Column(updatable = false,precision = 11,scale = 2)
    private BigDecimal payPayedPrice;

    @Column(nullable = false)
    private LocalDateTime creationDate; //oluşturulma zamanı
}
