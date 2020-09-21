package com.ytudak.malzeme.repository;

import com.ytudak.malzeme.entity.Teslim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeslimRepository extends JpaRepository<Teslim, Long> {

}
