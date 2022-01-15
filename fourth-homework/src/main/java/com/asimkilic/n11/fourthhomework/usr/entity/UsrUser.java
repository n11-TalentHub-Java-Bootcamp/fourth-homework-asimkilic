package com.asimkilic.n11.fourthhomework.usr.entity;

import com.asimkilic.n11.fourthhomework.dbt.entity.DbtDebt;
import com.asimkilic.n11.fourthhomework.gen.entity.BaseEntity;
import com.asimkilic.n11.fourthhomework.pay.entity.PayPayment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "USR_USER")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UsrUser implements Serializable,BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private Long tckn;

    private String imageUrl;

    private String email;

    private String cellPhone;

    @OneToMany(mappedBy ="usrUser",fetch = FetchType.LAZY)
    private Set<DbtDebt> debts = new HashSet<>();


    @OneToMany(mappedBy ="usrUser",fetch = FetchType.LAZY)
    private Set<PayPayment> payments = new HashSet<>();


}
