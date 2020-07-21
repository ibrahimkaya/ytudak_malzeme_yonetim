package com.ytudak.malzeme.controller;

import com.ytudak.malzeme.model.Teslim;
import com.ytudak.malzeme.model.Zimmet;
import com.ytudak.malzeme.repository.MalzemeRepository;
import com.ytudak.malzeme.repository.TeslimRepository;
import com.ytudak.malzeme.repository.ZimmetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class KayitController {

    @Autowired
    private ZimmetRepository zimmetRepository;

    @Autowired
    private TeslimRepository teslimRepository;

    @Autowired
    private MalzemeRepository malzemeRepository;

    @GetMapping("/kayit")
    public String kayit(Model model){
        //iki tablo olacak hem eski kayıtlar hem şuan zimmettekiler

        List<Zimmet> zimmetList = zimmetRepository.findAll();
        List<Teslim> teslimList = teslimRepository.findAll();

        //zimmet ve teslim bilgilerine malzeme bilgilerini aktarıyorum

        for(Zimmet zimmet: zimmetList ){

            zimmet.setMalzeme( malzemeRepository.findById(zimmet.getMalzemeNo()).get());
        }

        for(Teslim teslim: teslimList){
            teslim.setMalzeme( malzemeRepository.findById(teslim.getMalzemeNo()).get());
        }

        model.addAttribute("zimmetList",zimmetList);
        model.addAttribute("teslimList",teslimList);

        return "kayit";
    }

}
