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
        this.kategoriRepository.save(new Kategori("geleneksel"));
        this.kategoriRepository.save(new Kategori("cadir"));
        this.kategoriRepository.save(new Kategori("takoz"));

        //(Kategori kategori, String tip, String marka, String isim, String numara_boy, String durum_not, Boolean aktiflik)
        this.malzemeRepository.save(new Malzeme(kategoriRepository.findById(Long.valueOf(1)).get(), "yarım ip", "beal", "pembe", "60m", "temiz", true));
        this.malzemeRepository.save(new Malzeme(kategoriRepository.findById(Long.valueOf(1)).get(), "yarım ip", "beal", "mavi", "60m", "temiz", true));

        this.malzemeRepository.save(new Malzeme(kategoriRepository.findById(Long.valueOf(1)).get(), "yarım ip", "beal", "sarı", "50m", "performans", true));
        this.malzemeRepository.save(new Malzeme(kategoriRepository.findById(Long.valueOf(1)).get(), "yarım ip", "beal", "kırmızı", "50m", "performans", true));

        this.malzemeRepository.save(new Malzeme(kategoriRepository.findById(Long.valueOf(2)).get(), "", "bd", "mikro", "0.1", " ", true));
        this.malzemeRepository.save(new Malzeme(kategoriRepository.findById(Long.valueOf(2)).get(), "cam", "bd", "mikro", "0.2", " ", true));
        this.malzemeRepository.save(new Malzeme(kategoriRepository.findById(Long.valueOf(2)).get(), "cam", "bd", "", "3", " ", true));
        this.malzemeRepository.save(new Malzeme(kategoriRepository.findById(Long.valueOf(2)).get(), "cam", "dmm", "", "2", " yeni", true));

        this.malzemeRepository.save(new Malzeme(kategoriRepository.findById(Long.valueOf(3)).get(), "5 mevsim", "husky", "figther", "F3", "kapı fermuar has.", true));

        //(String alanKisi, String verenMalzemeci, , String verilmeNot)

        this.userRepository.save(new User("admin",new BCryptPasswordEncoder().encode("1"),true,"ROLE_ADMIN"));
        this.userRepository.save(new User("user",new BCryptPasswordEncoder().encode("123"),true,"ROLE_USER"));

    }
}
