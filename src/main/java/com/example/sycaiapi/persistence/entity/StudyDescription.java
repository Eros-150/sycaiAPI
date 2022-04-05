package com.example.sycaiapi.persistence.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "study_descriptions")
public class StudyDescription {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String description;

    public StudyDescription() {
    }

    public StudyDescription(String description) {
        this.description = description;
    }

}
