package com.ytudak.malzeme.repository;

import com.ytudak.malzeme.model.Malzeme;
import com.ytudak.malzeme.model.Zimmet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MalzemeRepository  extends JpaRepository<Malzeme, Long> {

    @Query(value = "select * from Malzeme where Malzeme .aktiflik = true", nativeQuery = true )
    List<Malzeme> findActiveItems();

}
