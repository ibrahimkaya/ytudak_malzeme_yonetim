package com.ytudak.malzeme.services;

import com.ytudak.malzeme.entity.Kategori;
import com.ytudak.malzeme.entity.Malzeme;
import com.ytudak.malzeme.entity.MalzemeDuzenle;
import com.ytudak.malzeme.entity.Status;
import com.ytudak.malzeme.model.ZimmetDTO;
import com.ytudak.malzeme.repository.MalzemeDuzenleRepository;
import com.ytudak.malzeme.repository.MalzemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MalzemeDuzenleService {

    private MalzemeRepository malzemeRepository;

    private MalzemeDuzenleRepository malzemeDuzenleRepository;

    @Autowired
    public MalzemeDuzenleService(MalzemeRepository malzemeRepository, MalzemeDuzenleRepository malzemeDuzenleRepository) {
        this.malzemeRepository = malzemeRepository;
        this.malzemeDuzenleRepository = malzemeDuzenleRepository;
    }

    public void getPendingApproval(Model model) {
        model.addAttribute("malzemelist", formatter());
    }

    /***
     * creating items image for update
     *  oldProps -> newProps
     */
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

    /***
     * onaylanacak malzemeleri alır
     * onay bekleyenleri ekler
     * silme bekleyenleri kullanılmaz olarak değiştirir
     * düzenleme bekleyenleri ise yeni değerleri ile değiştirerek kayderder
     * @param zimmetDTO
     * @param model
     */
    public void approve(ZimmetDTO zimmetDTO, Model model) {
        String[] malzemeList = zimmetDTO.getMalzemeNoList().split(",");

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
    }

    /***
     *
     * @param id reject request item id
     */
    public void reject(Long id) {
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
    }
}
