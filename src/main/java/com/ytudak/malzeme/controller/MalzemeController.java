package com.ytudak.malzeme.controller;

import com.ytudak.malzeme.model.Kategori;
import com.ytudak.malzeme.model.Malzeme;
import com.ytudak.malzeme.model.Status;
import com.ytudak.malzeme.repository.KategoriRepository;
import com.ytudak.malzeme.repository.MalzemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class MalzemeController {
    @Autowired
    MalzemeRepository malzemeRepository;

    @Autowired
    KategoriRepository kategoriRepository;

    @GetMapping("/malzemeekle")
    public String malzemeEkle(Model model){
        // kategori için dropdown list yapicam
        model.addAttribute("kategoriler",kategoriRepository.findAll());
        return "malzemeEkle";
    }

    @PostMapping("/malzemeekle")
    public String malzemeEkle(Malzeme malzeme, RedirectAttributes redirectAttributes){
        String kategori = malzeme.getKategori().getKategori();
        Optional<Kategori> kategori1 = kategoriRepository.findByKategori(kategori);
        if(kategori1.isPresent()){
            malzeme.setKategori(kategori1.get());
            malzeme.setAktiflik(true);
            malzeme.setStatus(Status.ONAY_BEKLIYOR);
            malzemeRepository.save(malzeme);
            redirectAttributes.addAttribute("success","");
        }else{
            redirectAttributes.addAttribute("fail","");
        }
        System.out.println(malzeme.toString());

        return "redirect:/malzemeekle";
    }

    @GetMapping("/malzemeduzenle")
    public String malzemeDuzenle(Model model){
        model.addAttribute("malzemelist",malzemeRepository.findAll());
        return "malzemeDuzenle";
    }

    @GetMapping("/malzeme/duzenle/{id}")
    public String malzemeDuzenleRequest(Malzeme malzeme, RedirectAttributes redirectAttributes){
       // malzeme.setStatus(Status.);
        return "malzemeDuzenle";
    }

    @GetMapping("/malzeme/sil/{id}")
    public String malzemeSilRequest(Malzeme malzeme, RedirectAttributes redirectAttributes){
        Malzeme tempMalzeme = malzemeRepository.findById(malzeme.getId()).get();
        tempMalzeme.setStatus(Status.SILME_BEKLIYOR);
        malzemeRepository.save(tempMalzeme);
        redirectAttributes.addAttribute("silmesuccess","");
        return "redirect:/malzemeduzenle";
    }
}
