package com.example.sycaiapi.persistence.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "contrast_types")
public class ContrastType {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String type;

    public ContrastType() {
    }

    public ContrastType(String type) {
        this.type = type;
    }

}
