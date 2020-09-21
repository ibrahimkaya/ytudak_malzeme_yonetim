package com.ytudak.malzeme.model;

import com.ytudak.malzeme.entity.Status;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/***
 * Malzemelerin bilgilerinin tututlduğu model
 */

@Data
public class MalzemeDTO {

    private long id;

    private String kategori;

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
