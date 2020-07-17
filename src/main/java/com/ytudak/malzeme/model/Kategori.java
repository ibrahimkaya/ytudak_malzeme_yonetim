package com.ytudak.malzeme.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*

Malzemelerin genel kategorilerinin tutulduğu yapı

 */
@Entity
public class Kategori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "kategori")
    private List<Malzeme> malzemeList = new ArrayList<>();

    private String kategori;

    public Kategori() {
    }

    public Kategori( String kategori) {
        this.kategori = kategori;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public List<Malzeme> getMalzemeList() {
        return malzemeList;
    }

    public void setMalzemeList(List<Malzeme> malzemeList) {
        this.malzemeList = malzemeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Kategori kategori1 = (Kategori) o;

        if (id != null ? !id.equals(kategori1.id) : kategori1.id != null) return false;
        return kategori != null ? kategori.equals(kategori1.kategori) : kategori1.kategori == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (kategori != null ? kategori.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Kategori{" +
                "id=" + id +
                ", malzemeList=" + malzemeList +
                ", kategori='" + kategori + '\'' +
                '}';
    }
}
