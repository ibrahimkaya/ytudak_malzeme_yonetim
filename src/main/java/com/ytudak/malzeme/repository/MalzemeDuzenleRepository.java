package com.ytudak.malzeme.repository;


import com.ytudak.malzeme.model.MalzemeDuzenle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MalzemeDuzenleRepository extends JpaRepository<MalzemeDuzenle,Long> {
    Optional<MalzemeDuzenle> findByMalzemeNo(Long id);
}
