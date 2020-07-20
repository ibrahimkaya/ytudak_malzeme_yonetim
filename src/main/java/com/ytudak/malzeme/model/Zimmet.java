package com.ytudak.malzeme.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/*

Bir kullanıcının kulüpten malzeme aldığında oluşan kayıdın tutulduğu model

 */
@Entity
public class Zimmet {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long malzemeNo;

    // veri tabanında olusmayacak
    // formda gonderilen malzeme id lerini tutmasi icin var sadece
    @Transient
    private String malzemeNoList;

    @Transient
    private Malzeme malzeme;

    private String alanKisi;

    private String verenMalzemeci;

    //üyenin malzemeyi kulüpten teslim aldığı tarih
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date verilmeTarihi;

    private String verilmeNot;

    public Zimmet() {
    }

    public Zimmet(Long malzemeNo, String alanKisi, String verenMalzemeci, String verilmeNot) {
        this.alanKisi = alanKisi;
        this.verenMalzemeci = verenMalzemeci;
        this.verilmeNot = verilmeNot;
        this.malzemeNo = malzemeNo;
    }

    public Long getMalzemeNo() {
        return malzemeNo;
    }

    public void setMalzemeNo(Long malzemeNo) {
        this.malzemeNo = malzemeNo;
    }

    public Long getId() {
        return id;
    }

    public String getMalzemeNoList() {
        return malzemeNoList;
    }

    public void setMalzemeNoList(String malzemeNoList) {
        this.malzemeNoList = malzemeNoList;
    }

    public String getAlanKisi() {
        return alanKisi;
    }

    public void setAlanKisi(String alanKisi) {
        this.alanKisi = alanKisi;
    }

    public String getVerenMalzemeci() {
        return verenMalzemeci;
    }

    public void setVerenMalzemeci(String verenMalzemeci) {
        this.verenMalzemeci = verenMalzemeci;
    }

    public Date getVerilmeTarihi() {
        return verilmeTarihi;
    }

    public void setVerilmeTarihi(Date verilmeTarihi) {
        this.verilmeTarihi = verilmeTarihi;
    }

    public String getVerilmeNot() {
        return verilmeNot;
    }

    public void setVerilmeNot(String verilmeNot) {
        this.verilmeNot = verilmeNot;
    }

    public Malzeme getMalzeme() {
        return malzeme;
    }

    public void setMalzeme(Malzeme malzeme) {
        this.malzeme = malzeme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Zimmet zimmet = (Zimmet) o;

        if (!id.equals(zimmet.id)) return false;
        if (!alanKisi.equals(zimmet.alanKisi)) return false;
        if (!verenMalzemeci.equals(zimmet.verenMalzemeci)) return false;
        if (!verilmeTarihi.equals(zimmet.verilmeTarihi)) return false;
        return verilmeNot != null ? verilmeNot.equals(zimmet.verilmeNot) : zimmet.verilmeNot == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + alanKisi.hashCode();
        result = 31 * result + verenMalzemeci.hashCode();
        result = 31 * result + verilmeTarihi.hashCode();
        result = 31 * result + (verilmeNot != null ? verilmeNot.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Zimmet{" +
                "id=" + id +
                ", malzemeNo=" + malzemeNo +
                ", malzemeNoList='" + malzemeNoList + '\'' +
                ", alanKisi='" + alanKisi + '\'' +
                ", verenMalzemeci='" + verenMalzemeci + '\'' +
                ", verilmeTarihi=" + verilmeTarihi +
                ", verilmeNot='" + verilmeNot + '\'' +
                '}';
    }
}
