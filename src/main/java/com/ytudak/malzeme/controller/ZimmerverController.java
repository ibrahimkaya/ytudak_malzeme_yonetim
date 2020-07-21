package com.ytudak.malzeme.controller;

import com.ytudak.malzeme.model.Malzeme;
import com.ytudak.malzeme.model.Zimmet;
import com.ytudak.malzeme.repository.MalzemeRepository;
import com.ytudak.malzeme.repository.ZimmetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ZimmerverController {

    @Autowired
    private MalzemeRepository malzemeRepository;

    @Autowired
    private ZimmetRepository zimmetRepository;

    @GetMapping("/zimmetver")
    public String listele(Model model) {

        model.addAttribute("malzemelist", malzemeRepository.findActiveItems());

        return "zimmetver";
    }

    @PostMapping("/zimmetver/zimmetle/onay")
    public String zimmetOnay(Zimmet zimmet, Model model) {
        System.out.println(zimmet.toString());

        List<Malzeme> hataList = new ArrayList<>();
        List<Malzeme> successList = new ArrayList<>();

        // malzemeNoList' ten id leri al
        String[] malzemeList = zimmet.getMalzemeNoList().split(",");
        // iterate id
        for (String id : malzemeList) {

            Optional<Malzeme> tempMalzeme = malzemeRepository.findById(Long.valueOf(id));
            //malzeme var ve aktiflik alınabilir ise
            if (tempMalzeme.isPresent() && tempMalzeme.get().getAktiflik().equals(true)) {
                //aktiflik durumunu false yap
                tempMalzeme.get().setAktiflik(false);
                malzemeRepository.save(tempMalzeme.get());

                successList.add(tempMalzeme.get());

                //gelen zimmet bilgilerinden yeni bir zimmet objesi oluştur
                zimmetRepository.save(new Zimmet(Long.valueOf(id), zimmet.getAlanKisi(), zimmet.getVerenMalzemeci(), zimmet.getVerilmeNot()));

            } else {
                //malzeme zimmetlenemedi ise
                hataList.add(tempMalzeme.get());
            }

            model.addAttribute("hataList",hataList);
            model.addAttribute("successList", successList);
            model.addAttribute("zimmet",zimmet);
        }
        // return degisecek
        return "sonuc";
    }

    @PostMapping("/zimmetver/zimmetle")
    public String zimmetle(Zimmet zimmet, Model model) {
        // secilenleri buna eklicem
        List<Malzeme> secilenMalzemeList = new ArrayList<>();

        // formdan gelen malzemelerin id lerini aliyorum.
        String[] malzemeList = zimmet.getMalzemeNoList().split(",");

        // id leri tektek alicam ve bunlari yollicam.
        for (String id : malzemeList) {
            // malzemeyi bul
            Optional<Malzeme> malzeme = malzemeRepository.findById(Long.valueOf(id));
            //eger bulduysa
            if (malzeme.isPresent()) {
                // eger bulunan malzeme aktifse
                if (malzeme.get().getAktiflik() == true) {
                    secilenMalzemeList.add(malzeme.get());
                }
            } // boyle bir malzeme bulunamadiysa
            else {

            }
        }
        // alan kisi bilgilerini bundan alicam
        model.addAttribute("kisi", zimmet);
        // secilenleri yolla
        model.addAttribute("secilenler", secilenMalzemeList);

        // onay sayfasina yolla
        return "zimmetveronay";
    }
}
