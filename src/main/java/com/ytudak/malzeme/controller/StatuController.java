package com.ytudak.malzeme.controller;

import com.ytudak.malzeme.model.*;
import com.ytudak.malzeme.repository.MalzemeDuzenleRepository;
import com.ytudak.malzeme.repository.MalzemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class StatuController {

    @Autowired
    private MalzemeRepository malzemeRepository;

    @Autowired
    private MalzemeDuzenleRepository malzemeDuzenleRepository;

    @GetMapping("/statuonay")
    public String onayBekleyenler(Model model) {

        model.addAttribute("malzemelist", formatter());
        return "statuonay";
    }

    @PostMapping("/statuonay/onay")
    public String onayla(Zimmet zimmet, Model model) {

        String[] malzemeList = zimmet.getMalzemeNoList().split(",");

        List<Malzeme> successList = new ArrayList<>();
        List<Malzeme> failList = new ArrayList<>();

        for (String id : malzemeList) {

            Optional<Malzeme> tempMalzeme = malzemeRepository.findById(Long.valueOf(id));

            if (tempMalzeme.isPresent()) {

                if (tempMalzeme.get().getStatus() == Status.ONAY_BEKLIYOR) {
                    tempMalzeme.get().setStatus(Status.KULLANILABILIR);
                } else if (tempMalzeme.get().getStatus() == Status.SILME_BEKLIYOR) {
                    tempMalzeme.get().setStatus(Status.KULLANILAMAZ);
                } else if (tempMalzeme.get().getStatus() == Status.DUZENLEME_BEKLIYOR) {
                    // malzemenin yeni halini aldım
                    Optional<MalzemeDuzenle> malzemeDuzenle = malzemeDuzenleRepository.findByMalzemeNo(tempMalzeme.get().getId());
                    if (malzemeDuzenle.isPresent()) {
                        tempMalzeme.get().setKategori(malzemeDuzenle.get().getKategori());
                        tempMalzeme.get().setTip(malzemeDuzenle.get().getTip());
                        tempMalzeme.get().setModel(malzemeDuzenle.get().getModel());
                        tempMalzeme.get().setIsim(malzemeDuzenle.get().getIsim());
                        tempMalzeme.get().setNumara_boy(malzemeDuzenle.get().getNumara_boy());
                        tempMalzeme.get().setDurum_not(malzemeDuzenle.get().getDurum_not());
                        tempMalzeme.get().setAktiflik(malzemeDuzenle.get().getAktiflik());
                        tempMalzeme.get().setStatus(Status.KULLANILABILIR);
                        // malzemyi update et
                        malzemeRepository.save(tempMalzeme.get());
                        // duzenleme tablosundan malzemeyi sil
                        malzemeDuzenleRepository.deleteById(malzemeDuzenle.get().getId());
                    }
                }

                malzemeRepository.save(tempMalzeme.get());
                successList.add(tempMalzeme.get());

            } else {
                failList.add(tempMalzeme.get());
            }

            model.addAttribute("failList", failList);
            model.addAttribute("successList", successList);
        }

        return "statuonaysonuc";
    }

    private List<Malzeme> formatter() {
        List<Malzeme> malzemeList = malzemeRepository.findWaitingItems();

        MalzemeDuzenle tempMalzeme;

        for (Malzeme malzeme : malzemeList) {

            if (malzeme.getStatus().equals(Status.DUZENLEME_BEKLIYOR)) {

                tempMalzeme = malzemeDuzenleRepository.findByMalzemeNo(malzeme.getId()).get();

                Kategori kategori = new Kategori();
                kategori.setKategori(malzeme.getKategori().getKategori() + " -> " + tempMalzeme.getKategori().getKategori());
                malzeme.setTip(malzeme.getTip() + " -> " + tempMalzeme.getTip());
                malzeme.setModel(malzeme.getModel() + " -> " + tempMalzeme.getModel());
                malzeme.setIsim(malzeme.getIsim() + " -> " + tempMalzeme.getIsim());
                malzeme.setNumara_boy(malzeme.getNumara_boy() + " -> " + tempMalzeme.getNumara_boy());
                malzeme.setDurum_not(malzeme.getDurum_not() + " -> " + tempMalzeme.getDurum_not());
            }
        }
        return malzemeList;
    }

    @Transactional
    @GetMapping("/statuonay/reddet/{id}")
    public String reddet(@PathVariable("id") Long id) {
        Optional<Malzeme> malzeme = malzemeRepository.findById(id);
        if (malzeme.isPresent()) {
            // eklenme onaylanmazsa tamamen sil
            if (malzeme.get().getStatus().equals(Status.ONAY_BEKLIYOR)) {
                malzemeRepository.deleteById(id);
                // duzenleme onaylamazsa statu guncelle ve duzenleme tablosundan sil
            } else if (malzeme.get().getStatus().equals(Status.DUZENLEME_BEKLIYOR)) {
                malzeme.get().setStatus(Status.KULLANILABILIR);
                malzemeRepository.save(malzeme.get());
                // duzenleme tablosundan sil
                malzemeDuzenleRepository.deleteByMalzemeNo(id);
            } else { // silme isteğini reddet
                malzeme.get().setStatus(Status.KULLANILABILIR);
                malzemeRepository.save(malzeme.get());
            }
        }
        return "redirect:/statuonay";
    }

}
