package com.ytudak.malzeme.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

/***
 * Malzemelerin bilgilerinin tututlduğu model
 */


@Entity
@Data
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

    /***
     * malzeme kulüpte mi yoksa biri tarafından kullanıluyor mu
     */
    private Boolean aktiflik = true;
    // malzeme onaylanmis mi - kullanim disi mi - kullanilabilir mi ayrimi icin
    /***
     * Malzemenin kulanılabilir silinme gibi durumlarını temsil eder
     */
    private Status status;
}
