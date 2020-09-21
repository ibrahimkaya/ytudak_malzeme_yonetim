package com.ytudak.malzeme.model;

/***
 * Bir üyenin teslim ettiği malzemenin tutulduğu model
 *
 */

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Teslim {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long malzemeNo;

    private String alanKisi;

    private String verenMalzemeci;

    //üyenin malzemeyi kulüpten teslim aldığı tarih
    private Date verilmeTarihi;

    private String verilmeNot;

    private String getirenKisi;

    private String teslimAlanMalzemeci;

    private String teslimNot;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date teslimTarihi;

    @Transient
    private String malzemeNoList;

    @Transient
    private  Malzeme malzeme;
}
