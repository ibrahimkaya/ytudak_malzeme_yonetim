package com.ytudak.malzeme.model;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class User {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String userName;

    private @JsonIgnore
    String password;

    public void setPassword(String password) {
        this.password = password;
    }

    private boolean active;

    private String role;
}
