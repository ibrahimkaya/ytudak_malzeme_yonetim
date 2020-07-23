package com.ytudak.malzeme.model;

import javax.persistence.*;

@Entity
public class MalzemeDuzenle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long malzemeNo;
    @ManyToOne @JoinColumn(name = "kategoriID") private Kategori kategori;
    private String tip;
    private String model;
    private String isim;
    private String numara_boy;
    private String durum_not;
    private Boolean aktiflik = true;
    private Status status;

    public MalzemeDuzenle() {
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getNumara_boy() {
        return numara_boy;
    }

    public void setNumara_boy(String numara_boy) {
        this.numara_boy = numara_boy;
    }

    public String getDurum_not() {
        return durum_not;
    }

    public void setDurum_not(String durum_not) {
        this.durum_not = durum_not;
    }

    public Boolean getAktiflik() {
        return aktiflik;
    }

    public void setAktiflik(Boolean aktiflik) {
        this.aktiflik = aktiflik;
    }

    public Long getMalzemeNo() {
        return malzemeNo;
    }

    public void setMalzemeNo(Long malzemeNo) {
        this.malzemeNo = malzemeNo;
    }
}
