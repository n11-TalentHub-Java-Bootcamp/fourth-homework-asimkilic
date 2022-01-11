package com.asimkilic.n11.fourthhomework.usr.entity;

import com.asimkilic.n11.fourthhomework.gen.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "USR_USER")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UsrUser implements BaseEntity {

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



}
