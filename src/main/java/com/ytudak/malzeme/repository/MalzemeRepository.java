package com.ytudak.malzeme.repository;

import com.ytudak.malzeme.model.Malzeme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MalzemeRepository  extends JpaRepository<Malzeme, Long> {

}
