package com.ytudak.malzeme.services;

import com.ytudak.malzeme.entity.Malzeme;
import com.ytudak.malzeme.entity.Zimmet;
import com.ytudak.malzeme.model.ZimmetDTO;
import com.ytudak.malzeme.repository.MalzemeRepository;
import com.ytudak.malzeme.repository.ZimmetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ZimmetVerSevice {

    private MalzemeRepository malzemeRepository;
    private ZimmetRepository zimmetRepository;

    @Autowired
    public ZimmetVerSevice(MalzemeRepository malzemeRepository, ZimmetRepository zimmetRepository) {
        this.malzemeRepository = malzemeRepository;
        this.zimmetRepository = zimmetRepository;
    }

    public void getAll(Model model) {
        model.addAttribute("malzemelist", malzemeRepository.findActiveItems());
    }

    public void zimmetOnay(ZimmetDTO zimmetDTO, Model model) {
        List<Malzeme> hataList = new ArrayList<>();
        List<Malzeme> successList = new ArrayList<>();

        // malzemeNoList' ten id leri al
        String[] malzemeList = zimmetDTO.getMalzemeNoList().split(",");

        for (String id : malzemeList) {

            Optional<Malzeme> tempMalzeme = malzemeRepository.findById(Long.valueOf(id));
            //malzeme var ve aktiflik alınabilir ise
            if (tempMalzeme.isPresent() && tempMalzeme.get().getAktiflik().equals(true)) {
                //aktiflik durumunu false yap
                tempMalzeme.get().setAktiflik(false);
                malzemeRepository.save(tempMalzeme.get());

                successList.add(tempMalzeme.get());

                //gelen zimmet bilgilerinden yeni bir zimmet objesi oluştur
                Zimmet tempZimmet = new Zimmet();
                tempZimmet.setMalzemeNo(Long.valueOf(id));
                tempZimmet.setAlanKisi(zimmetDTO.getAlanKisi());
                tempZimmet.setVerenMalzemeci(zimmetDTO.getVerenMalzemeci());
                tempZimmet.setVerilmeNot(zimmetDTO.getVerilmeNot());
                zimmetRepository.save(tempZimmet);

            } else {
                //malzeme zimmetlenemedi ise
                hataList.add(tempMalzeme.get());
            }

            model.addAttribute("hataList", hataList);
            model.addAttribute("successList", successList);
            model.addAttribute("zimmet", zimmetDTO);
        }
    }

    public void zimmetle(ZimmetDTO zimmetDTO, Model model) {

        List<Malzeme> secilenMalzemeList = new ArrayList<>();

        // formdan gelen malzemelerin id lerini aliyorum.
        String[] malzemeList = zimmetDTO.getMalzemeNoList().split(",");

        // id üstünden eriştiğim her bir malzeme için
        for (String id : malzemeList) {

            // malzemeyi bul
            Optional<Malzeme> malzeme = malzemeRepository.findById(Long.valueOf(id));
            //malzeme mevcut ise
            if (malzeme.isPresent()) {
                // ve aktifse
                if (malzeme.get().getAktiflik() == true) {
                    secilenMalzemeList.add(malzeme.get());
                }
            }

        }
        // alan kisi bilgilerini bundan alicam
        model.addAttribute("kisi", zimmetDTO);
        // secilenleri yolla
        model.addAttribute("secilenler", secilenMalzemeList);
    }

}
