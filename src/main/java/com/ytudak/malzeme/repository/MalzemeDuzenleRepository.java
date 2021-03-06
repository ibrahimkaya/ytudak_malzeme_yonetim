package com.ytudak.malzeme.repository;


import com.ytudak.malzeme.entity.MalzemeDuzenle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MalzemeDuzenleRepository extends JpaRepository<MalzemeDuzenle,Long> {
    Optional<MalzemeDuzenle> findByMalzemeNo(Long id);

    void deleteByMalzemeNo(Long id);
}
