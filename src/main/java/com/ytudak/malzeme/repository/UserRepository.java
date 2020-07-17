package com.ytudak.malzeme.repository;


import com.ytudak.malzeme.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface UserRepository extends JpaRepository <User, Long> {

    Optional<User> findByUserName(String userName);
}
