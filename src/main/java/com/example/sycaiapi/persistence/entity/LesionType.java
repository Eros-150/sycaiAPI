package com.example.sycaiapi.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "lesion_types")
public class LesionType {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String type;

    public LesionType() {
    }

    public LesionType(String type) {
        this.type = type;
    }

}
