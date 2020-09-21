package com.ytudak.malzeme;

import com.ytudak.malzeme.entity.*;
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
    public DatabaseLoader(KategoriRepository kategoriRepository, MalzemeRepository malzemeRepository, ZimmetRepository zimmetRepository, UserRepository userRepository) {
        this.kategoriRepository = kategoriRepository;
        this.malzemeRepository = malzemeRepository;
        this.zimmetRepository = zimmetRepository;
        this.userRepository = userRepository;
    }

    /*
     populate DB for testing
     */
    @Override
    public void run(String... args) throws Exception {
        Kategori kategoritemp1 = new Kategori();
        kategoritemp1.setKategori("ip");
        kategoriRepository.save(kategoritemp1);

        Kategori kategoritemp2 = new Kategori();
        kategoritemp2.setKategori("geleneksel");
        kategoriRepository.save(kategoritemp2);

        Kategori kategoritemp3 = new Kategori();
        kategoritemp3.setKategori("cadir");
        kategoriRepository.save(kategoritemp3);

        Kategori kategoritemp4 = new Kategori();
        kategoritemp4.setKategori("takoz");
        kategoriRepository.save(kategoritemp4);


        //(Kategori kategori, String tip, String model, String isim, String numara_boy, String durum_not, Boolean aktiflik)

        Malzeme malzeme = new Malzeme();
        malzeme.setKategori(kategoriRepository.findById(1L).get());
        malzeme.setTip("yarım ip");
        malzeme.setModel("beal");
        malzeme.setIsim("pembe");
        malzeme.setNumara_boy("60m");
        malzeme.setDurum_not("temiz");
        malzeme.setAktiflik(true);
        malzeme.setStatus(Status.KULLANILABILIR);
        malzemeRepository.save(malzeme);

        Malzeme malzeme1 = new Malzeme();
        malzeme1.setKategori(kategoriRepository.findById(1L).get());
        malzeme1.setTip("yarım ip");
        malzeme1.setModel("beal");
        malzeme1.setIsim("mavi");
        malzeme1.setNumara_boy("60m");
        malzeme1.setDurum_not("temiz");
        malzeme1.setAktiflik(true);
        malzeme1.setStatus(Status.KULLANILABILIR);
        malzemeRepository.save(malzeme1);

        Malzeme malzeme2 = new Malzeme();
        malzeme2.setKategori(kategoriRepository.findById(1L).get());
        malzeme2.setTip("yarım ip");
        malzeme2.setModel("beal");
        malzeme2.setIsim("sarı");
        malzeme2.setNumara_boy("50");
        malzeme2.setDurum_not("performans");
        malzeme2.setAktiflik(true);
        malzeme2.setStatus(Status.KULLANILABILIR);
        malzemeRepository.save(malzeme2);

        Malzeme malzeme3 = new Malzeme();
        malzeme3.setKategori(kategoriRepository.findById(1L).get());
        malzeme3.setTip("yarım ip");
        malzeme3.setModel("beal");
        malzeme3.setIsim("kırmızı");
        malzeme3.setNumara_boy("50");
        malzeme3.setDurum_not("performans");
        malzeme3.setAktiflik(true);
        malzeme3.setStatus(Status.KULLANILABILIR);
        malzemeRepository.save(malzeme3);


        Malzeme malzeme4 = new Malzeme();
        malzeme4.setKategori(kategoriRepository.findById(2L).get());
        malzeme4.setTip("");
        malzeme4.setModel("bd");
        malzeme4.setIsim("mikro");
        malzeme4.setNumara_boy("0.1");
        malzeme4.setDurum_not("");
        malzeme4.setAktiflik(true);
        malzeme4.setStatus(Status.KULLANILABILIR);
        malzemeRepository.save(malzeme4);


        Malzeme malzeme5 = new Malzeme();
        malzeme5.setKategori(kategoriRepository.findById(2L).get());
        malzeme5.setTip("");
        malzeme5.setModel("bd");
        malzeme5.setIsim("mikro");
        malzeme5.setNumara_boy("0.2");
        malzeme5.setDurum_not("");
        malzeme5.setStatus(Status.KULLANILAMAZ);
        malzeme4.setAktiflik(false);
        malzemeRepository.save(malzeme5);


        Malzeme malzeme6 = new Malzeme();
        malzeme6.setKategori(kategoriRepository.findById(2L).get());
        malzeme6.setTip("");
        malzeme6.setModel("bd");
        malzeme6.setIsim("mikro");
        malzeme6.setNumara_boy("1");
        malzeme6.setDurum_not("");
        malzeme6.setStatus(Status.SILME_BEKLIYOR);
        malzemeRepository.save(malzeme6);


        Malzeme malzeme7 = new Malzeme();
        malzeme7.setKategori(kategoriRepository.findById(3L).get());
        malzeme7.setTip("5 mevsim");
        malzeme7.setModel("husky");
        malzeme7.setIsim("figther");
        malzeme7.setNumara_boy("F3");
        malzeme7.setDurum_not("kapı fermuar has");
        malzeme7.setAktiflik(true);
        malzeme7.setStatus(Status.KULLANILABILIR);
        malzemeRepository.save(malzeme7);

        User admin = new User();
        admin.setUserName("admin");
        admin.setPassword(new BCryptPasswordEncoder().encode("1"));
        admin.setActive(true);
        admin.setRole("ROLE_ADMIN");
        userRepository.save(admin);

        User user = new User();
        user.setUserName("user");
        user.setPassword(new BCryptPasswordEncoder().encode("1"));
        user.setActive(true);
        user.setRole("ROLE_USER");
        userRepository.save(user);

        User baskan = new User();
        baskan.setUserName("baskan");
        baskan.setPassword(new BCryptPasswordEncoder().encode("1"));
        baskan.setActive(true);
        baskan.setRole("ROLE_BASKAN");
        userRepository.save(baskan);

    }
}
