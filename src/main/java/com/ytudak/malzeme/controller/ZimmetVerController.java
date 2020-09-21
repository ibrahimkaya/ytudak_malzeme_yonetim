package com.ytudak.malzeme.controller;

import com.ytudak.malzeme.model.ZimmetDTO;
import com.ytudak.malzeme.services.ZimmetVerSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ZimmetVerController {

    private ZimmetVerSevice zimmetverSevice;

    @Autowired
    public ZimmetVerController(ZimmetVerSevice zimmetverSevice) {
        this.zimmetverSevice = zimmetverSevice;
    }

    @GetMapping("/zimmetVer")
    public String getAll(Model model) {

        zimmetverSevice.getAll(model);
        return "zimmetver";
    }

    @PostMapping("/zimmetVer/zimmetle/onay")
    public String zimmetOnay(ZimmetDTO zimmetDTO, Model model) {

        zimmetverSevice.zimmetOnay(zimmetDTO, model);
        return "sonuc";
    }

    @PostMapping("/zimmetVer/zimmetle")
    public String zimmetle(ZimmetDTO zimmetDTO, Model model) {

        zimmetverSevice.zimmetle(zimmetDTO, model);
        return "zimmetveronay";
    }
}
