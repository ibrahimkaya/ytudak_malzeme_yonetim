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

    @GetMapping("/liste")
    public String listele(Model model){

        System.out.println("++++++++++++++++" + malzemeRepository.findAll().get(1).toString());
        model.addAttribute("malzemelist",malzemeRepository.findAll());

        return "liste";
    }
}
