package com.ytudak.malzeme.repository;

import com.ytudak.malzeme.model.Zimmet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZimmetRepository extends JpaRepository<Zimmet, Long> {
}
