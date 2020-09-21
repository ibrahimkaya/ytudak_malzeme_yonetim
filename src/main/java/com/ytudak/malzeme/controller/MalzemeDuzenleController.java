package com.ytudak.malzeme.controller;

import com.ytudak.malzeme.model.ZimmetDTO;
import com.ytudak.malzeme.services.MalzemeDuzenleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/onay")
public class MalzemeDuzenleController {

    private MalzemeDuzenleService malzemeDuzenleService;

    @Autowired
    public MalzemeDuzenleController(MalzemeDuzenleService malzemeDuzenleService) {
        this.malzemeDuzenleService = malzemeDuzenleService;
    }

    @GetMapping("/malzemeDuzenle")
    public String onayBekleyenler(Model model) {
        malzemeDuzenleService.getPendingApproval(model);
        return "statuonay";
    }

    @PostMapping("/malzemeDuzenle/onay")
    public String approve(ZimmetDTO zimmetDTO, Model model) {
        malzemeDuzenleService.approve(zimmetDTO, model);
        return "statuonaysonuc";
    }

    @GetMapping("/malzemeDuzenle/reddet/{id}")
    public String reject(@PathVariable("id") Long id) {
        malzemeDuzenleService.reject(id);
        return "redirect:/onay/malzemeDuzenle ";
    }
}
