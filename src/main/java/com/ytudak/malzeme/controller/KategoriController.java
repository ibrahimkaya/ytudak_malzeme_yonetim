package com.ytudak.malzeme.controller;

import com.ytudak.malzeme.model.Kategori;
import com.ytudak.malzeme.repository.KategoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class KategoriController {

    @Autowired
    KategoriRepository kategoriRepository;

    @GetMapping("/kategoriekle")
    public String kategoriEkle(Model model){
        model.addAttribute("kategoriler",kategoriRepository.findAll());
        return "kategoriEkle";
    }
    @PostMapping("/kategoriekle")
    public String kategoriEklePost(Kategori kategori){
        kategoriRepository.save(kategori);
        return "redirect:/kategoriekle";
    }
}
