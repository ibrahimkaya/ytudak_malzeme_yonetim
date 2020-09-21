package com.ytudak.malzeme.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/***
 * Bir kullanıcının kulüpten malzeme aldığında oluşan kayıdın tutulduğu model
 */

@Entity
@Data
@RequiredArgsConstructor
public class Zimmet {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long malzemeNo;

    @Transient
    private Malzeme malzeme;

    private String alanKisi;

    private String verenMalzemeci;

    //üyenin malzemeyi kulüpten teslim aldığı tarih
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date verilmeTarihi;

    private String verilmeNot;

}
