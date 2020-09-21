package com.ytudak.malzeme.controller;

import com.ytudak.malzeme.entity.Kategori;
import com.ytudak.malzeme.services.KategoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class KategoriController {

    private KategoriService kategoriService;

    @Autowired
    public KategoriController(KategoriService kategoriService) {
        this.kategoriService = kategoriService;
    }

    @GetMapping("/kategoriEkle")
    public String getAll(Model model) {
        kategoriService.getAll(model);
        return "kategoriEkle";
    }

    @PostMapping("/kategoriEkle")
    public String save(Kategori kategori) {
        kategoriService.save(kategori);
        return "redirect:/kategoriEkle";
    }
}
