package com.ytudak.malzeme.repository;

import com.ytudak.malzeme.entity.Malzeme;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MalzemeRepository  extends JpaRepository<Malzeme, Long> {

    /* zimmet için malzemenin kulüpte olması ve statüsü kullanılabilir olması gerekli
        status = 1 değeri enum Status clasının 'KULLANILABİLİR' indexinden gelir
    */
    @Query(value = "select * from Malzeme where Malzeme .aktiflik = true AND Malzeme.status = 1 ", nativeQuery = true )
    List<Malzeme> findActiveItems();

    @Query(value = "select * from Malzeme where Malzeme.status = 2 or Malzeme.status = 3 or Malzeme.status = 4",nativeQuery = true)
    List<Malzeme> findWaitingItems();

    @Query(value = "select * from Malzeme where Malzeme.status = 1 or Malzeme.status = 3 or Malzeme.status = 4 or Malzeme.status = 5",nativeQuery = true)
    List<Malzeme> findEditableItems();


}
