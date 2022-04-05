package com.example.sycaiapi.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "labelers")
public class Labeler {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    public Labeler() {
    }

    public Labeler(String name) {
        this.name = name;
    }

}
