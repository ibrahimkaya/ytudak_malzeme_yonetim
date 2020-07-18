package com.ytudak.malzeme.controller;

import com.ytudak.malzeme.model.Zimmet;
import com.ytudak.malzeme.repository.MalzemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ZimmerverController {

    @Autowired
    private MalzemeRepository malzemeRepository;

    @GetMapping("/zimmetver")
    public String listele(Model model){

        model.addAttribute("malzemelist",malzemeRepository.findActiveItems());

        return "zimmetver";
    }

    @PostMapping("/zimmetver/zimmetle")
    public String zimmetle(Zimmet zimmet){
        System.out.println(zimmet);
        return "redirect:/anasayfa";
    }
}
