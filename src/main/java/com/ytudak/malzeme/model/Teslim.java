package com.ytudak.malzeme.model;

/*
Bir üyenin teslim ettiği malzemenin tutulduğu model

zimmet alınan malzeme teslim modeli ile saklanır

 */

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
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


    public Teslim() {
    }

    public Teslim(Long malzemeNo, String alanKisi, String verenMalzemeci, Date verilmeTarihi, String verilmeNot, String getirenKisi, String teslimAlanMalzemeci, String teslimNot) {
        this.malzemeNo = malzemeNo;
        this.alanKisi = alanKisi;
        this.verenMalzemeci = verenMalzemeci;
        this.verilmeTarihi = verilmeTarihi;
        this.verilmeNot = verilmeNot;
        this.getirenKisi = getirenKisi;
        this.teslimAlanMalzemeci = teslimAlanMalzemeci;
        this.teslimNot = teslimNot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMalzemeNo() {
        return malzemeNo;
    }

    public void setMalzemeNo(Long malzemeNo) {
        this.malzemeNo = malzemeNo;
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

    public String getGetirenKisi() {
        return getirenKisi;
    }

    public void setGetirenKisi(String getirenKisi) {
        this.getirenKisi = getirenKisi;
    }

    public String getTeslimAlanMalzemeci() {
        return teslimAlanMalzemeci;
    }

    public void setTeslimAlanMalzemeci(String teslimAlanMalzemeci) {
        this.teslimAlanMalzemeci = teslimAlanMalzemeci;
    }

    public String getTeslimNot() {
        return teslimNot;
    }

    public void setTeslimNot(String teslimNot) {
        this.teslimNot = teslimNot;
    }

    public Date getTeslimTarihi() {
        return teslimTarihi;
    }

    public void setTeslimTarihi(Date teslimTarihi) {
        this.teslimTarihi = teslimTarihi;
    }

    public String getMalzemeNoList() {
        return malzemeNoList;
    }

    public void setMalzemeNoList(String malzemeNoList) {
        this.malzemeNoList = malzemeNoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teslim teslim = (Teslim) o;

        if (id != null ? !id.equals(teslim.id) : teslim.id != null) return false;
        if (malzemeNo != null ? !malzemeNo.equals(teslim.malzemeNo) : teslim.malzemeNo != null) return false;
        if (alanKisi != null ? !alanKisi.equals(teslim.alanKisi) : teslim.alanKisi != null) return false;
        if (verenMalzemeci != null ? !verenMalzemeci.equals(teslim.verenMalzemeci) : teslim.verenMalzemeci != null)
            return false;
        if (verilmeTarihi != null ? !verilmeTarihi.equals(teslim.verilmeTarihi) : teslim.verilmeTarihi != null)
            return false;
        if (verilmeNot != null ? !verilmeNot.equals(teslim.verilmeNot) : teslim.verilmeNot != null) return false;
        if (getirenKisi != null ? !getirenKisi.equals(teslim.getirenKisi) : teslim.getirenKisi != null) return false;
        if (teslimAlanMalzemeci != null ? !teslimAlanMalzemeci.equals(teslim.teslimAlanMalzemeci) : teslim.teslimAlanMalzemeci != null)
            return false;
        if (teslimNot != null ? !teslimNot.equals(teslim.teslimNot) : teslim.teslimNot != null) return false;
        return teslimTarihi != null ? teslimTarihi.equals(teslim.teslimTarihi) : teslim.teslimTarihi == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (malzemeNo != null ? malzemeNo.hashCode() : 0);
        result = 31 * result + (alanKisi != null ? alanKisi.hashCode() : 0);
        result = 31 * result + (verenMalzemeci != null ? verenMalzemeci.hashCode() : 0);
        result = 31 * result + (verilmeTarihi != null ? verilmeTarihi.hashCode() : 0);
        result = 31 * result + (verilmeNot != null ? verilmeNot.hashCode() : 0);
        result = 31 * result + (getirenKisi != null ? getirenKisi.hashCode() : 0);
        result = 31 * result + (teslimAlanMalzemeci != null ? teslimAlanMalzemeci.hashCode() : 0);
        result = 31 * result + (teslimNot != null ? teslimNot.hashCode() : 0);
        result = 31 * result + (teslimTarihi != null ? teslimTarihi.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Teslim{" +
                "malzemeNo=" + malzemeNo +
                ", alanKisi='" + alanKisi + '\'' +
                ", verenMalzemeci='" + verenMalzemeci + '\'' +
                ", verilmeTarihi=" + verilmeTarihi +
                ", verilmeNot='" + verilmeNot + '\'' +
                ", getirenKisi='" + getirenKisi + '\'' +
                ", teslimAlanMalzemeci='" + teslimAlanMalzemeci + '\'' +
                ", teslimNot='" + teslimNot + '\'' +
                ", teslimTarihi=" + teslimTarihi +
                '}';
    }
}
