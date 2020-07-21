package com.ytudak.malzeme.controller;

import com.ytudak.malzeme.model.Kategori;
import com.ytudak.malzeme.model.Malzeme;
import com.ytudak.malzeme.model.MalzemeDuzenle;
import com.ytudak.malzeme.model.Status;
import com.ytudak.malzeme.repository.KategoriRepository;
import com.ytudak.malzeme.repository.MalzemeDuzenleRepository;
import com.ytudak.malzeme.repository.MalzemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class MalzemeController {
    @Autowired
    MalzemeRepository malzemeRepository;

    @Autowired
    KategoriRepository kategoriRepository;

    @Autowired
    MalzemeDuzenleRepository malzemeDuzenleRepository;

    // malzeme ekleme sayfası
    @GetMapping("/malzemeekle")
    public String malzemeEkle(Model model) {
        // kategori için dropdown list yapicam
        model.addAttribute("kategoriler", kategoriRepository.findAll());
        return "malzemeEkle";
    }

    // malzeme ekleme formu buraya request atıyor
    @PostMapping("/malzemeekle")
    public String malzemeEkle(Malzeme malzeme, RedirectAttributes redirectAttributes) {
        // kategoriyi al
        String kategori = malzeme.getKategori().getKategori();
        //kategori yi dbden al ve objede setle
        Optional<Kategori> kategori1 = kategoriRepository.findByKategori(kategori);
        // kategori varsa
        if (kategori1.isPresent()) {
            // malzemeti setle ve kaydet
            malzeme.setKategori(kategori1.get());
            malzeme.setAktiflik(true);
            // başkan onayına sun
            malzeme.setStatus(Status.ONAY_BEKLIYOR);
            malzemeRepository.save(malzeme);
            redirectAttributes.addAttribute("success", "");
        } else { // yoksa faille
            redirectAttributes.addAttribute("fail", "");
        }
        // duzenleme sayfasına geri gönder
        return "redirect:/malzemeekle";
    }

    // malzeme duzenleme sayfasi
    @GetMapping("/malzemeduzenle")
    public String malzemeDuzenle(Model model) {
        // malzeme listesini yollla
        model.addAttribute("malzemelist", malzemeRepository.findEditableItems());
        return "malzemeDuzenle";
    }

    //
    @GetMapping("/malzeme/duzenle/{id}")
    public String malzemeDuzenleRequest(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        // malzeme icin duzenleme istegi zaten var mi?
        Optional<MalzemeDuzenle> malzemeKontrol = malzemeDuzenleRepository.findById(id);
        if(malzemeKontrol.isPresent()){
            redirectAttributes.addAttribute("istekvar","");
            return "redirect:/malzemeduzenle";
        }

        // yoksa devam et
        Optional<Malzeme> malzeme = malzemeRepository.findById(id);
        if (malzeme.isPresent()) {
            // duzenleme sayfasina secilen malzeme bilgilerini gönder
            model.addAttribute("kategoriler", kategoriRepository.findAll());
            model.addAttribute("secilenMalzeme", malzeme.get());
            return "malzemeDuzenleForm";
        }
        // bu id ye sahip malzeme yoksa geri gonder
        return "redirect:/malzemeduzenle";
    }

    // malzeme silme için request al
    @GetMapping("/malzeme/sil/{id}")
    public String malzemeSilRequest(Malzeme malzeme, RedirectAttributes redirectAttributes) {
        //gelen malzemeyi al
        Optional<Malzeme> tempMalzeme = malzemeRepository.findById(malzeme.getId());
        if (tempMalzeme.isPresent()) {
            // zaten silme isteği varsa
            if(tempMalzeme.get().getStatus() == Status.SILME_BEKLIYOR){
                redirectAttributes.addAttribute("silmeistegivar", "");
                return "redirect:/malzemeduzenle";
            }
            // malzeme durumunu degistir ve kaydet
            tempMalzeme.get().setStatus(Status.SILME_BEKLIYOR);
            malzemeRepository.save(tempMalzeme.get());
            redirectAttributes.addAttribute("silmesuccess", "");
        } else {
            redirectAttributes.addAttribute("silmefail", "");
        }

        return "redirect:/malzemeduzenle";
    }

    @PostMapping("/malzeme/duzenle")
    public String malzemeDuzenleRequest(Malzeme malzeme, RedirectAttributes redirectAttributes) {
        // gelen malzemeyi al
        Optional<Malzeme> gelenMalzeme = malzemeRepository.findById(malzeme.getId());
        // eger boyle bir malzem yoksa hata donsun
        if(!gelenMalzeme.isPresent()){
            redirectAttributes.addAttribute("duzenlemefail","");
            return "redirect:/malzemeduzenle";
        }

        // statü setledi
        gelenMalzeme.get().setKategori(gelenMalzeme.get().getKategori());
        gelenMalzeme.get().setStatus(Status.DUZENLEME_BEKLIYOR);
        // update et
        malzemeRepository.save(gelenMalzeme.get());

        // duzenlecekler tablosuna eklenecek olan malzeme
        MalzemeDuzenle duzenlenecek = new MalzemeDuzenle();

        duzenlenecek.setMalzemeNo(malzeme.getId());
        duzenlenecek.setKategori(kategoriRepository.findByKategori(malzeme.getKategori().getKategori()).get());
        duzenlenecek.setTip(malzeme.getTip());
        duzenlenecek.setModel(malzeme.getModel());
        duzenlenecek.setIsim(malzeme.getIsim());
        duzenlenecek.setNumara_boy(malzeme.getNumara_boy());
        duzenlenecek.setDurum_not(malzeme.getDurum_not());
        duzenlenecek.setAktiflik(malzeme.getAktiflik());

        // duzenlenecek tablosuna ekle
        malzemeDuzenleRepository.save(duzenlenecek);
        redirectAttributes.addAttribute("duzenlemeSuccess","");
        return "redirect:/malzemeduzenle";
    }
}
