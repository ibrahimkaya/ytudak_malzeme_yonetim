package com.ytudak.malzeme.services;

import com.ytudak.malzeme.entity.Kategori;
import com.ytudak.malzeme.entity.Malzeme;
import com.ytudak.malzeme.entity.MalzemeDuzenle;
import com.ytudak.malzeme.entity.Status;
import com.ytudak.malzeme.model.MalzemeDTO;
import com.ytudak.malzeme.repository.KategoriRepository;
import com.ytudak.malzeme.repository.MalzemeDuzenleRepository;
import com.ytudak.malzeme.repository.MalzemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Service
public class MalzemeService {

    private MalzemeRepository malzemeRepository;
    private KategoriRepository kategoriRepository;
    private MalzemeDuzenleRepository malzemeDuzenleRepository;

    @Autowired
    public MalzemeService(MalzemeRepository malzemeRepository, KategoriRepository kategoriRepository, MalzemeDuzenleRepository malzemeDuzenleRepository) {
        this.malzemeRepository = malzemeRepository;
        this.kategoriRepository = kategoriRepository;
        this.malzemeDuzenleRepository = malzemeDuzenleRepository;
    }

    public void findAll(Model model) {
        model.addAttribute("kategoriler", kategoriRepository.findAll());
    }

    public void save(MalzemeDTO malzemeDTO, RedirectAttributes redirectAttributes) {
        String kategoriName = malzemeDTO.getKategori();
        //kategori yi dbden al ve objede setle
        Optional<Kategori> kategori = kategoriRepository.findByKategori(kategoriName);
        // kategori varsa
        if (kategori.isPresent()) {
            Malzeme malzeme = new Malzeme();

            malzeme.setTip(malzemeDTO.getTip());
            malzeme.setModel(malzemeDTO.getModel());
            malzeme.setIsim(malzemeDTO.getIsim());
            malzeme.setNumara_boy(malzemeDTO.getNumara_boy());
            malzeme.setDurum_not(malzemeDTO.getDurum_not());

            malzeme.setKategori(kategori.get());
            malzeme.setAktiflik(true);

            // başkan onayına sun
            malzeme.setStatus(Status.ONAY_BEKLIYOR);
            malzemeRepository.save(malzeme);
            redirectAttributes.addAttribute("success", "");
        } else { // yoksa faille
            redirectAttributes.addAttribute("fail", "");
        }
    }

    public void getEditableItems(Model model) {
        model.addAttribute("malzemelist", malzemeRepository.findEditableItems());
    }

    /***
     * malzseme düzenle isteği için gerekli sayfaya model üstünden bilgilerin aktarılmasını sağlar
     * @param id
     * @param model
     * @param redirectAttributes
     * @return
     */
    public String getUpdateInfo(Long id, Model model, RedirectAttributes redirectAttributes) {
        // malzeme icin duzenleme istegi zaten var mi?
        Optional<MalzemeDuzenle> malzemeKontrol = malzemeDuzenleRepository.findById(id);
        if (malzemeKontrol.isPresent()) {
            redirectAttributes.addAttribute("istekvar", "");
            return "redirect:/malzemeDuzenle";
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
        return "redirect:/malzemeDuzenle";
    }

    /***
     * malzemenin var olduğu ve daha önce bir silme işlemi gelmediğine emin olur
     * statu durumunu güncelleyerek
     * @param malzemeDTO
     * @param redirectAttributes
     * @return mazemedyzenle page
     */
    public String delete(MalzemeDTO malzemeDTO, RedirectAttributes redirectAttributes) {

        Optional<Malzeme> malzeme = malzemeRepository.findById(malzemeDTO.getId());
        if (malzeme.isPresent()) {
            // zaten silme isteği varsa
            if (malzeme.get().getStatus() == Status.SILME_BEKLIYOR) {
                redirectAttributes.addAttribute("silmeistegivar", "");
                return "redirect:/malzemeDuzenle";
            }
            // malzeme durumunu degistir ve kaydet
            malzeme.get().setStatus(Status.SILME_BEKLIYOR);
            malzemeRepository.save(malzeme.get());
            redirectAttributes.addAttribute("silmesuccess", "");
        } else {
            redirectAttributes.addAttribute("silmefail", "");
        }
        return "redirect:/malzemeDuzenle";
    }

    /**
     * Düzenleme isteği gelen malzemenin statüsünü DUZENLEME_BEKLIYOR yapar
     * Onay veya red için yeni bilgiler ile MalzemeDuzenle tablosunda bir görüntü oluşturur
     *
     * @param malzemeDTO
     * @param redirectAttributes
     * @return redirect/malzemeDüzenle page
     * @see MalzemeDuzenle
     */
    public String update(MalzemeDTO malzemeDTO, RedirectAttributes redirectAttributes) {

        Optional<Malzeme> gelenMalzeme = malzemeRepository.findById(malzemeDTO.getId());

        if (!gelenMalzeme.isPresent()) {
            redirectAttributes.addAttribute("duzenlemefail", "");
            return "redirect:/malzemeDuzenle";
        }

        // güncelleme isteği olan malzemeyi düzenleme bekliyora çek
        gelenMalzeme.get().setStatus(Status.DUZENLEME_BEKLIYOR);
        malzemeRepository.save(gelenMalzeme.get());

        // onay veya red durumları için bilgileri sakla
        MalzemeDuzenle posibleNewData = new MalzemeDuzenle();

        posibleNewData.setMalzemeNo(malzemeDTO.getId());
        posibleNewData.setKategori(kategoriRepository.findByKategori(malzemeDTO.getKategori()).get());
        posibleNewData.setTip(malzemeDTO.getTip());
        posibleNewData.setModel(malzemeDTO.getModel());
        posibleNewData.setIsim(malzemeDTO.getIsim());
        posibleNewData.setNumara_boy(malzemeDTO.getNumara_boy());
        posibleNewData.setDurum_not(malzemeDTO.getDurum_not());
        posibleNewData.setAktiflik(malzemeDTO.getAktiflik());
        posibleNewData.setStatus(Status.DUZENLEME_BEKLIYOR);

        malzemeDuzenleRepository.save(posibleNewData);

        redirectAttributes.addAttribute("duzenlemeSuccess", "");
        return "redirect:/malzemeDuzenle";
    }

}
