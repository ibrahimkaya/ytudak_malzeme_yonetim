package com.ytudak.malzeme.services;

import com.ytudak.malzeme.entity.Malzeme;
import com.ytudak.malzeme.entity.Teslim;
import com.ytudak.malzeme.entity.Zimmet;
import com.ytudak.malzeme.model.ZimmetDTO;
import com.ytudak.malzeme.repository.MalzemeRepository;
import com.ytudak.malzeme.repository.TeslimRepository;
import com.ytudak.malzeme.repository.ZimmetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeslimService {

    private ZimmetRepository zimmetRepository;

    private MalzemeRepository malzemeRepository;

    private TeslimRepository teslimRepository;

    @Autowired
    public TeslimService(ZimmetRepository zimmetRepository, MalzemeRepository malzemeRepository, TeslimRepository teslimRepository) {
        this.zimmetRepository = zimmetRepository;
        this.malzemeRepository = malzemeRepository;
        this.teslimRepository = teslimRepository;
    }

    /***
     * zimmetlenmiş tüm malzemeleri listeler
     *
     * zimmetlenmiş malzemelerin malzeme no bilgileri ile
     * zimmet entity yapısındaki @transient malzeme değişkenine o bilgileri gerçirmek
     * teslim almak için hem zimmet bilgileri hemde malzemelerin kendi bilgileri gereklidir
     */
    public void getZimmetList(Model model) {
        List<Zimmet> zimmetList = zimmetRepository.findAll();

        for (Zimmet z : zimmetList) {
            z.setMalzeme(malzemeRepository.findById(z.getMalzemeNo()).get());
        }
        model.addAttribute("zimmetList", zimmetList);
    }

    public void zimmetAl(Teslim teslim, Model model) {
        List<Malzeme> secilenMalzemeList = new ArrayList<>();
        List<Zimmet> secilenZimmetList = new ArrayList<>();

        // formdan gelen malzemelerin id lerini aliyorum.
        String[] malzemeList = teslim.getMalzemeNoList().split(",");

        // her id için
        for (String id : malzemeList) {
            // malzemeyi bul
            Optional<Malzeme> malzeme = malzemeRepository.findById(Long.valueOf(id));
            //eger bulduysa
            if (malzeme.isPresent()) {
                // eger bulunan malzeme dışarıda ise
                if (malzeme.get().getAktiflik() == false) {
                    //malzeme listesine bu malzemenin bilgilerini ekliyorum
                    secilenMalzemeList.add(malzeme.get());
                    //malzeme id bilgisinden zimmet bilgilerini getiriyorum
                    Zimmet zimmet = zimmetRepository.findByMalzemeNo(Long.valueOf(id)).get();
                    //gelen malzeme bilgilerini zimmet modelinin, malzeme değişkenine atıyorum
                    zimmet.setMalzeme(malzeme.get());

                    //bu zimmetleri listeye ekliyorum
                    secilenZimmetList.add(zimmet);
                }
            }
        }

        model.addAttribute("secilenZimmetList", secilenZimmetList);
        model.addAttribute("teslim", teslim);
    }

    public void zimmetAlOnay(ZimmetDTO zimmetDTO, Teslim teslim, Model model) {
        List<Malzeme> hataList = new ArrayList<>();
        List<Zimmet> successList = new ArrayList<>();

        // malzemeNoList' ten id leri al
        String[] malzemeList = zimmetDTO.getMalzemeNoList().split(",");
        // iterate id

        for (String id : malzemeList) {

            Optional<Malzeme> tempMalzeme = malzemeRepository.findById(Long.valueOf(id));
            //malzeme var ve dışarıda ise ( ek kontrol)
            if (tempMalzeme.isPresent() && tempMalzeme.get().getAktiflik().equals(false)) {
                //aktiflik durumunu true yap
                tempMalzeme.get().setAktiflik(true);
                malzemeRepository.save(tempMalzeme.get());
                Zimmet zimmetTemp = zimmetRepository.findByMalzemeNo(Long.valueOf(id)).get();
                zimmetTemp.setMalzeme(tempMalzeme.get());
                successList.add(zimmetTemp);

                //gelen teslim bilgilerinden yeni bir teslim objesi oluştur
                Teslim teslimEdilenMalzeme = new Teslim();
                teslimEdilenMalzeme.setMalzemeNo(Long.valueOf(id));
                teslimEdilenMalzeme.setAlanKisi(zimmetTemp.getAlanKisi());
                teslimEdilenMalzeme.setVerenMalzemeci(zimmetTemp.getVerenMalzemeci());
                teslimEdilenMalzeme.setVerilmeTarihi(zimmetTemp.getVerilmeTarihi());
                teslimEdilenMalzeme.setVerilmeNot(zimmetTemp.getVerilmeNot());
                teslimEdilenMalzeme.setGetirenKisi(teslim.getGetirenKisi());
                teslimEdilenMalzeme.setTeslimAlanMalzemeci(teslim.getTeslimAlanMalzemeci());
                teslimEdilenMalzeme.setTeslimNot(teslim.getTeslimNot());
                teslimRepository.save(teslimEdilenMalzeme);

                //artık bu malzeme zimmetli değil
                zimmetRepository.deleteById(zimmetTemp.getId());

            } else {
                //malzeme zimmetlenemedi ise
                hataList.add(tempMalzeme.get());
            }

            model.addAttribute("hataList", hataList);
            model.addAttribute("successList", successList);
            model.addAttribute("teslim", teslim);
        }
    }
}
