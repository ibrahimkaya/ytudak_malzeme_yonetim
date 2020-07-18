package com.ytudak.malzeme.controller;

import com.ytudak.malzeme.repository.MalzemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ZimmerverController {

    @Autowired
    private MalzemeRepository malzemeRepository;

    @GetMapping("/zimmetver")
    public String listele(Model model){

        model.addAttribute("malzemelist",malzemeRepository.findActiveItems());

        return "zimmetver";
    }

}
