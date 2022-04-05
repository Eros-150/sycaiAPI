package com.example.sycaiapi.persistence.entity;

import com.example.sycaiapi.dto.StudyDTO;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

enum Modality {
    CT,
    MR
}

enum Sex {
    F,
    M,
    O
}

enum Location {
    Head,
    Body,
    Tail
}

@Data
@Entity
@Table(name="Studies")
public class Study {

    @Id
    @Column
    private String sycaiId;

    @Column
    private String patientId;

    @Column(columnDefinition = "ENUM('CT', 'MR')")
    @Enumerated(EnumType.STRING)
    private Modality modality;

    @Column
    private Date year;

    @Column(columnDefinition = "ENUM('F', 'M', 'O')")
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column
    private Date birthDate;

    @Column
    private Integer studyDescriptionId;

    @Column
    private Integer contrastTypeId;

    @Column(name = "radiologist_1_id")
    private Integer radiologist1Id;

    @Column(name = "radiologist_2_id")
    private Integer radiologist2Id;

    @Column(name = "radiologist_3_id")
    private Integer radiologist3Id;

    @Column(name = "labeler_1_id")
    private Integer labeler1Id;

    @Column(name = "labeler_2_id")
    private Integer labeler2Id;

    @Column(name = "labeler_3_id")
    private Integer labeler3Id;

    @Column
    private Boolean isLesion;

    @Column
    private Boolean isPancreas;

    @Column(name = "diagnosis_1_id")
    private Integer diagnosis1Id;

    @Column(name = "diagnosis_2_id")
    private Integer diagnosis2Id;

    @Column(name = "diagnosis_3_id")
    private Integer diagnosis3Id;

    @Column(name = "final_diagnosis_id")
    private Integer finalDiagnosisId;

    @Column
    private Integer paafResultId;

    @Column
    private Boolean isTestset;

    @Column
    private String comment;

    @Column
    private String otherPathologies;

    @Column
    private Double majorAxis;

    @Column
    private Double minorAxis;

    @Column(columnDefinition = "ENUM('Head', 'Body', 'Tail')")
    @Enumerated(EnumType.STRING)
    private Location location;

    @Column
    private Boolean isMriTest;

    @Column
    private Boolean hasCalcification;

    @Column
    private Boolean hasAirball;

    @Column
    private Boolean isExitus;

    public Study() {

    }

    public String getModalityString(){
        return this.modality.toString();
    }
    public String getSexString(){
        return this.sex.toString();
    }
    public String getLocationString(){
        return this.location.toString();
    }

    public Study(StudyDTO study){
        this.patientId = study.getPatientId();
        this.sycaiId = study.getSycaiId();
        this.modality = Objects.equals(study.getModality(), "0") || study.getModality() == null ? null : Modality.valueOf(study.getModality());
        this.year = study.getYear() == null || Objects.equals(study.getYear(), "") ? null : Date.valueOf(study.getYear());
        this.sex = Objects.equals(study.getSex(), "0") || study.getSex() == null ? null : Sex.valueOf(study.getSex());
        this.birthDate = study.getBirthDate() == null || Objects.equals(study.getBirthDate(), "")  ? null : Date.valueOf(study.getBirthDate());
        this.isLesion = study.getIsLesion() == null ? null : study.getIsLesion().equals("Yes");
        this.isPancreas = study.getIsPancreas() == null ? null : study.getIsPancreas().equals("Yes");
        this.isTestset = study.getIsTestset() == null ? null : study.getIsTestset().equals("Yes");
        this.comment = study.getComment();
        this.isMriTest = study.getIsMriTest() == null ? null : study.getIsMriTest().equals("Yes");
        this.hasCalcification = study.getHasCalcification() == null ? null : study.getHasCalcification().equals("Yes");
        this.hasAirball = study.getHasAirball() == null ? null : study.getHasAirball().equals("Yes");
        this.otherPathologies = study.getOtherPathologies();
        this.majorAxis = study.getMajorAxis();
        this.minorAxis = study.getMinorAxis();
        this.location = Objects.equals(study.getLocation(), "0") || study.getLocation() == null ? null : Location.valueOf(study.getLocation());
        this.isExitus = study.getIsExitus() == null ? null : true;
    }
}
