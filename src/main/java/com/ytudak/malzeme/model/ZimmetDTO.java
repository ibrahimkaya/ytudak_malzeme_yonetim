package com.ytudak.malzeme.model;

import com.ytudak.malzeme.entity.Malzeme;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/***
 * Bir kullanıcının kulüpten malzeme aldığında oluşan kayıdın tutulduğu model
 */

@Data
public class ZimmetDTO {

    private Long id;

    private Long malzemeNo;

    private String malzemeNoList;

    private Malzeme malzeme;

    private String alanKisi;

    private String verenMalzemeci;

    private Date verilmeTarihi;

    private String verilmeNot;
}
