package com.ytudak.malzeme.controller;

import com.ytudak.malzeme.entity.Teslim;
import com.ytudak.malzeme.model.ZimmetDTO;
import com.ytudak.malzeme.services.TeslimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/***
 * Teslim controllar bir malzemeyi üye kulübe geri getirdiğinde kullanılan yapıdır
 * yaygın olarak zimmet almak diye kullanılabilir
 *
 * bir malzemenin teslim edilmesi durumu "zimmet alınması" durumudur
 */

@Controller
public class TeslimController {

    private TeslimService teslimService;

    @Autowired
    public TeslimController(TeslimService teslimService) {
        this.teslimService = teslimService;
    }

    @GetMapping("/zimmetAl")
    public String getZimmetList(Model model) {

        teslimService.getZimmetList(model);
        return "zimmetal";
    }

    @PostMapping("/zimmetAl/teslimAl")
    public String zimmetAl(Teslim teslim, Model model) {

        teslimService.zimmetAl(teslim, model);
        // onay sayfasina yolla
        return "zimmetalonay";
    }

    @PostMapping("/teslim/teslimAl/onay")
    public String zimmetAlOnay(ZimmetDTO zimmetDTO, Teslim teslim, Model model) {

        teslimService.zimmetAlOnay(zimmetDTO, teslim, model);
        return "sonucteslim";
    }


}
