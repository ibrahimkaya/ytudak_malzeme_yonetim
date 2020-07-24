package com.ytudak.malzeme.repository;


import com.ytudak.malzeme.model.MalzemeDuzenle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MalzemeDuzenleRepository extends JpaRepository<MalzemeDuzenle,Long> {
    Optional<MalzemeDuzenle> findByMalzemeNo(Long id);

    void deleteByMalzemeNo(Long id);
}
