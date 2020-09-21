package com.ytudak.malzeme.entity;

import lombok.Data;

import javax.persistence.*;

/***
 * Düzenlenen malzemenin baskan onay veya red işlemine kadar tutulduğu model
 * @see Malzeme
 *
 */
@Entity
@Data
public class MalzemeDuzenle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long malzemeNo;
    @ManyToOne
    @JoinColumn(name = "kategoriID")
    private Kategori kategori;
    private String tip;
    private String model;
    private String isim;
    private String numara_boy;
    private String durum_not;
    private Boolean aktiflik = true;
    private Status status;
}
