package com.ytudak.malzeme.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/***
 *  kategorilerinin tutulduğu model
 */

@Entity
@Data
public class Kategori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "kategori", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Malzeme> malzemeList = new ArrayList<>();

    private String kategori;
}
