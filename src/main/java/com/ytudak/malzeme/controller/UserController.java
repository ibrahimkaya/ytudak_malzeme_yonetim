package com.ytudak.malzeme.controller;
/*
  User: Ufuk
  Date: 23.09.2020 10:52
*/

import com.ytudak.malzeme.model.UserDTO;
import com.ytudak.malzeme.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    final private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profil")
    public String profil(){
        return "profil";
    }
    @PostMapping("/parolaGuncelle")
    public String updatePassword(UserDTO userDTO, RedirectAttributes redirectAttributes){
        boolean result = userService.changePassword(userDTO);
        if (result) {
            redirectAttributes.addAttribute("success", "");
        } else {
            redirectAttributes.addAttribute("fail", "");
        }
        ;
        return "redirect:/profil";
    }
}
