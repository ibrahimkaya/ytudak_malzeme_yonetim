package com.ytudak.malzeme.services;

import com.ytudak.malzeme.entity.Teslim;
import com.ytudak.malzeme.entity.Zimmet;
import com.ytudak.malzeme.repository.MalzemeRepository;
import com.ytudak.malzeme.repository.TeslimRepository;
import com.ytudak.malzeme.repository.ZimmetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class RecordsService {

    private ZimmetRepository zimmetRepository;

    private TeslimRepository teslimRepository;

    private MalzemeRepository malzemeRepository;

    @Autowired
    public RecordsService(ZimmetRepository zimmetRepository, TeslimRepository teslimRepository, MalzemeRepository malzemeRepository) {
        this.zimmetRepository = zimmetRepository;
        this.teslimRepository = teslimRepository;
        this.malzemeRepository = malzemeRepository;
    }

    /***
     * this function returns all records
     * lists currently given items and returned items
     * @param model
     * @return model
     */

    public void records(Model model) {

        List<Zimmet> zimmetList = zimmetRepository.findAll();
        List<Teslim> teslimList = teslimRepository.findAll();

        for (Zimmet zimmet : zimmetList) {
            zimmet.setMalzeme(malzemeRepository.findById(zimmet.getMalzemeNo()).get());
        }

        for (Teslim teslim : teslimList) {
            teslim.setMalzeme(malzemeRepository.findById(teslim.getMalzemeNo()).get());
        }

        model.addAttribute("zimmetList", zimmetList);
        model.addAttribute("teslimList", teslimList);
    }
}
