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

    /* zimmet için malzemenin kulüpte olması ve statüsü kullanılabilir olması gerekli
        status = 1 değeri enum Status clasının 'KULLANILABİLİR' indexinden gelir
    */
    @Query(value = "select * from Malzeme where Malzeme .aktiflik = true AND Malzeme.status = 1 ", nativeQuery = true )
    List<Malzeme> findActiveItems();

}
