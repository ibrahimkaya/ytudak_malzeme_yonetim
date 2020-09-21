package com.ytudak.malzeme.controller;

import com.ytudak.malzeme.model.MalzemeDTO;
import com.ytudak.malzeme.services.MalzemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MalzemeController {

    private MalzemeService malzemeService;

    @Autowired
    public MalzemeController(MalzemeService malzemeService) {
        this.malzemeService = malzemeService;
    }

    @GetMapping("/malzemeekle")
    public String findAll(Model model) {
        malzemeService.findAll(model);
        return "malzemeEkle";
    }

    @PostMapping("/malzemeekle")
    public String save(MalzemeDTO malzemeDTO, RedirectAttributes redirectAttributes) {
        malzemeService.save(malzemeDTO, redirectAttributes);
        return "redirect:/malzemeekle";
    }

    @GetMapping("/malzemeduzenle")
    public String getEditableItems(Model model) {
        malzemeService.getEditableItems(model);
        return "malzemeDuzenle";
    }

    @GetMapping("/malzeme/duzenle/{id}")
    public String getUpdate(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        return malzemeService.getUpdateInfo(id, model, redirectAttributes);
    }

    @GetMapping("/malzeme/sil/{id}")
    public String delete(MalzemeDTO malzemeDTO, RedirectAttributes redirectAttributes) {
        return malzemeService.delete(malzemeDTO, redirectAttributes);
    }

    @PostMapping("/malzeme/duzenle")
    public String update(MalzemeDTO malzemeDTO, RedirectAttributes redirectAttributes) {

        return malzemeService.update(malzemeDTO, redirectAttributes);
    }
}
