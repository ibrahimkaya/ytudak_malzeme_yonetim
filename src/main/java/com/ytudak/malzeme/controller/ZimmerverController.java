package com.ytudak.malzeme.controller;

import com.ytudak.malzeme.model.Malzeme;
import com.ytudak.malzeme.model.Zimmet;
import com.ytudak.malzeme.repository.MalzemeRepository;
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

    @GetMapping("/zimmetver")
    public String listele(Model model) {

        model.addAttribute("malzemelist", malzemeRepository.findActiveItems());

        return "zimmetver";
    }

    @PostMapping("zimmetver/zimmetle/onay")
    public String zimmetOnay(Zimmet zimmet){
        System.out.println(zimmet.toString());
        /*
        gelen zimmet objesinden
        alanKisi,verenMalzemeci,verilmeNot ve malzemeNoList alinacak
        aktiflikler false yapilacak ve zimmet tablosuna eklenecek.
        */

        // malzemeNoList' ten id leri al
        String[] malzemeList = zimmet.getMalzemeNoList().split(",");
        // iterate id
        for (String id : malzemeList) {

        }
        // return degisecek
        return "anasayfa";
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
        model.addAttribute("kisi",zimmet);
        // secilenleri yolla
        model.addAttribute("secilenler",secilenMalzemeList);

        // onay sayfasina yolla
        return "zimmetveronay";
    }
}
