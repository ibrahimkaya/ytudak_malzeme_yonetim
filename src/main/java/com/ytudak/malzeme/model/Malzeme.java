package com.ytudak.malzeme.model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

/*
Tüm malzemelerin özlülük bilgilerinin tutulduğu model

 */
@Entity
public class Malzeme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "kategoriID")
    private Kategori kategori;

    @Value("")
    private String tip;
    @Value("")
    private String model;
    @Value("")
    private String isim;
    @Value("")
    private String numara_boy;
    @Value("")
    private String durum_not;

    private Boolean aktiflik = true;

    public Malzeme() {
    }

    public Malzeme(Kategori kategori, String tip, String model, String isim, String numara_boy, String durum_not, Boolean aktiflik) {
        this.kategori = kategori;
        this.tip = tip;
        this.model = model;
        this.isim = isim;
        this.numara_boy = numara_boy;
        this.durum_not = durum_not;
        this.aktiflik = aktiflik;
    }

    public Long getId() {
        return id;
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

    public void setModel(String marka) {
        this.model = marka;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Malzeme malzeme = (Malzeme) o;

        if (!id.equals(malzeme.id)) return false;
        if (!kategori.equals(malzeme.kategori)) return false;
        if (tip != null ? !tip.equals(malzeme.tip) : malzeme.tip != null) return false;
        if (model != null ? !model.equals(malzeme.model) : malzeme.model != null) return false;
        if (isim != null ? !isim.equals(malzeme.isim) : malzeme.isim != null) return false;
        if (numara_boy != null ? !numara_boy.equals(malzeme.numara_boy) : malzeme.numara_boy != null) return false;
        if (durum_not != null ? !durum_not.equals(malzeme.durum_not) : malzeme.durum_not != null) return false;
        return aktiflik != null ? aktiflik.equals(malzeme.aktiflik) : malzeme.aktiflik == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + kategori.hashCode();
        result = 31 * result + (tip != null ? tip.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (isim != null ? isim.hashCode() : 0);
        result = 31 * result + (numara_boy != null ? numara_boy.hashCode() : 0);
        result = 31 * result + (durum_not != null ? durum_not.hashCode() : 0);
        result = 31 * result + (aktiflik != null ? aktiflik.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Malzeme{" +
                "id=" + id +
                ", kategori=" + kategori +
                ", tip='" + tip + '\'' +
                ", model='" + model + '\'' +
                ", isim='" + isim + '\'' +
                ", numara_boy='" + numara_boy + '\'' +
                ", durum_not='" + durum_not + '\'' +
                ", aktiflik=" + aktiflik +
                '}';
    }
}
