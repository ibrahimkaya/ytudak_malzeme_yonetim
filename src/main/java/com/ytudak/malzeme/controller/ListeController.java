package com.ytudak.malzeme.controller;

import com.ytudak.malzeme.repository.MalzemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
