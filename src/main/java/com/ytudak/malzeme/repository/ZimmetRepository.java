package com.ytudak.malzeme.repository;

import com.ytudak.malzeme.model.Zimmet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@PreAuthorize("hasRole('ROLE_ADMIN')")
public interface ZimmetRepository extends JpaRepository<Zimmet, Long> {

    Optional<Zimmet> findByMalzemeNo(Long id);

}
