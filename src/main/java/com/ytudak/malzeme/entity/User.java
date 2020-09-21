package com.ytudak.malzeme.entity;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class User implements Serializable {

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

    private static final long serialVersionUID = 6529685098267757690L;

}
