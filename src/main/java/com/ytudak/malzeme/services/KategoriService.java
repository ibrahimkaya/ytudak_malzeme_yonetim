package com.ytudak.malzeme.services;

import com.ytudak.malzeme.entity.Kategori;
import com.ytudak.malzeme.repository.KategoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class KategoriService {

    private KategoriRepository kategoriRepository;

    @Autowired
    public KategoriService(KategoriRepository kategoriRepository){
        this.kategoriRepository = kategoriRepository;
    }

    public void getAll(Model model){
        model.addAttribute("kategoriler",kategoriRepository.findAll());
    }

    public void save(Kategori kategori){
        kategoriRepository.save(kategori);
    }
}
