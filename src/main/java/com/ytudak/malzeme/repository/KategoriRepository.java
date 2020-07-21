package com.ytudak.malzeme.repository;

import com.ytudak.malzeme.model.Kategori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KategoriRepository  extends JpaRepository<Kategori,Long> {
    Optional<Kategori> findByKategori(String kategori);
}
