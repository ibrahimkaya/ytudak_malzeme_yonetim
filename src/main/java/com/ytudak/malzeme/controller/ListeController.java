package com.ytudak.malzeme.controller;

import com.ytudak.malzeme.model.Malzeme;
import com.ytudak.malzeme.model.Status;
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
public class ListeController {

    @Autowired
    private MalzemeRepository malzemeRepository;


    /*
     GET "/liste" isteği geldiği zaman, mvc controller bu isteği geldiğinde  controller bu isteği yakalyıp alttaki fonksiyonu çağırır
     Bu istek model arayüzü ile malzemerepository üstünden gelen tüm malzemeleri ekliyoruz
     malzemelist  thymeleaf template ile erişeceğimiz değişken ismi ile oluşturuyoruz
     */
    @GetMapping("/liste")
    public String listele(Model model){

        model.addAttribute("malzemelist",malzemeRepository.findAll());

        return "liste";
    }

    @GetMapping("/statuonay")
    public  String onayBekleyenler(Model model){

        model.addAttribute("malzemelist", malzemeRepository.findWaitingItems());
        return "statuonay";
    }

    @PostMapping("/statuonay/onay")
    public String onayla(Zimmet zimmet, Model model){

        String[] malzemeList = zimmet.getMalzemeNoList().split(",");

        List<Malzeme> successList = new ArrayList<>();
        List<Malzeme> failList = new ArrayList<>();

        for (String id : malzemeList) {

            Optional<Malzeme> tempMalzeme = malzemeRepository.findById(Long.valueOf(id));

            if (tempMalzeme.isPresent() ) {

                if(tempMalzeme.get().getStatus() == Status.ONAY_BEKLIYOR){
                    tempMalzeme.get().setStatus(Status.KULLANILABILIR);

                }else if( tempMalzeme.get().getStatus() == Status.SILME_BEKLIYOR){
                    tempMalzeme.get().setStatus(Status.KULLANILAMAZ);
                }

                malzemeRepository.save(tempMalzeme.get());
                successList.add(tempMalzeme.get());

            } else {
                failList.add(tempMalzeme.get());
            }

            model.addAttribute("failList",failList);
            model.addAttribute("successList", successList);
        }

        return "statuonaysonuc";
    }
}
