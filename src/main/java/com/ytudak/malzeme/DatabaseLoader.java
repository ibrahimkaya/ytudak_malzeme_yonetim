package com.ytudak.malzeme;

import com.ytudak.malzeme.model.Kategori;
import com.ytudak.malzeme.model.Malzeme;
import com.ytudak.malzeme.model.Zimmet;
import com.ytudak.malzeme.repository.KategoriRepository;
import com.ytudak.malzeme.repository.MalzemeRepository;
import com.ytudak.malzeme.repository.ZimmetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final KategoriRepository kategoriRepository;
    private final MalzemeRepository malzemeRepository;
    private final ZimmetRepository zimmetRepository;

    @Autowired
    public DatabaseLoader(KategoriRepository kategoriRepository, MalzemeRepository malzemeRepository, ZimmetRepository zimmetRepository) {
        this.kategoriRepository = kategoriRepository;
        this.malzemeRepository = malzemeRepository;
        this.zimmetRepository = zimmetRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.printf("");
        this.kategoriRepository.save(new Kategori("ip"));
        //(Kategori kategori, String tip, String marka, String isim, String numara_boy, String durum_not, Boolean aktiflik)
        this.malzemeRepository.save(new Malzeme(kategoriRepository.findById(Long.valueOf(1)).get(), "String tip", "String marka", "String isim", "String numara_boy", "String durum_not", true));
        this.malzemeRepository.save(new Malzeme(kategoriRepository.findById(Long.valueOf(1)).get(), "String tip", "String marka", "String isim", "String numara_boy", "String durum_not", false));
        //(String alanKisi, String verenMalzemeci, , String verilmeNot)
        this.zimmetRepository.save( new Zimmet(1L,"ufuk","ibo","kazma ucları ile beraber verildi"));
    }
}
