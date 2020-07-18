package com.ytudak.malzeme;

import com.ytudak.malzeme.model.Kategori;
import com.ytudak.malzeme.model.Malzeme;
import com.ytudak.malzeme.model.User;
import com.ytudak.malzeme.model.Zimmet;
import com.ytudak.malzeme.repository.KategoriRepository;
import com.ytudak.malzeme.repository.MalzemeRepository;
import com.ytudak.malzeme.repository.UserRepository;
import com.ytudak.malzeme.repository.ZimmetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final KategoriRepository kategoriRepository;
    private final MalzemeRepository malzemeRepository;
    private final ZimmetRepository zimmetRepository;
    private final UserRepository userRepository;

    @Autowired
    public DatabaseLoader(KategoriRepository kategoriRepository, MalzemeRepository malzemeRepository, ZimmetRepository zimmetRepository,UserRepository userRepository) {
        this.kategoriRepository = kategoriRepository;
        this.malzemeRepository = malzemeRepository;
        this.zimmetRepository = zimmetRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.printf("");
        this.kategoriRepository.save(new Kategori("ip"));
        this.kategoriRepository.save(new Kategori("cam"));
        this.kategoriRepository.save(new Kategori("cadir"));
        //(Kategori kategori, String tip, String marka, String isim, String numara_boy, String durum_not, Boolean aktiflik)
        this.malzemeRepository.save(new Malzeme(kategoriRepository.findById(Long.valueOf(1)).get(), "String tip", "String marka", "String isim", "Stri123a_boy", "Stri123_not", true));
        this.malzemeRepository.save(new Malzeme(kategoriRepository.findById(Long.valueOf(1)).get(), "String tip", "String marka", "String isim", "String numara_boy", "S33 durum_not", true));
        this.malzemeRepository.save(new Malzeme(kategoriRepository.findById(Long.valueOf(2)).get(), "ip 1", "String marka", "String isim", "Strss_boy", "String durum_not", true));
        this.malzemeRepository.save(new Malzeme(kategoriRepository.findById(Long.valueOf(2)).get(), "ip2", "String marka", "String isim", "String numara_boy", "String durum_not", false));
        this.malzemeRepository.save(new Malzeme(kategoriRepository.findById(Long.valueOf(3)).get(), " cadir", "String marka", "String isim", "String numara_boy", "String durum_not", false));
        //(String alanKisi, String verenMalzemeci, , String verilmeNot)
        this.zimmetRepository.save( new Zimmet(1L,"ufuk","ibo","kazma ucları ile beraber verildi"));

        this.userRepository.save(new User("admin",new BCryptPasswordEncoder().encode("1"),true,"ROLE_ADMIN"));
        this.userRepository.save(new User("user",new BCryptPasswordEncoder().encode("123"),true,"ROLE_USER"));

    }
}
