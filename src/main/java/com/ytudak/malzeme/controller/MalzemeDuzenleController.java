package com.ytudak.malzeme.controller;

import com.ytudak.malzeme.model.ZimmetDTO;
import com.ytudak.malzeme.services.MalzemeDuzenleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MalzemeDuzenleController {

    private MalzemeDuzenleService malzemeDuzenleService;

    @Autowired
    public MalzemeDuzenleController(MalzemeDuzenleService malzemeDuzenleService) {
        this.malzemeDuzenleService = malzemeDuzenleService;
    }

    @GetMapping("/statuonay")
    public String onayBekleyenler(Model model) {
        malzemeDuzenleService.getPendingApproval(model);
        return "statuonay";
    }

    @PostMapping("/statuonay/onay")
    public String approve(ZimmetDTO zimmetDTO, Model model) {
        malzemeDuzenleService.approve(zimmetDTO, model);
        return "statuonaysonuc";
    }

    @GetMapping("/statuonay/reddet/{id}")
    public String reject(@PathVariable("id") Long id) {
        malzemeDuzenleService.reject(id);
        return "redirect:/statuonay";
    }
}
