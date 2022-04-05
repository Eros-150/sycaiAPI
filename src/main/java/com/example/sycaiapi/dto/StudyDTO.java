package com.example.sycaiapi.dto;

import com.example.sycaiapi.persistence.entity.Study;
import lombok.Data;

@Data
public class StudyDTO {
    private String sycaiId;

    private String patientId;

    private String modality;

    private String year;

    private String sex;

    private String birthDate;

    private String studyDescription;

    private String contrastType;

    private String radiologist1;

    private String radiologist2;

    private String radiologist3;

    private String labeler1;

    private String labeler2;

    private String labeler3;

    private String isLesion;

    private String isPancreas;

    private String diagnosis1;

    private String diagnosis2;

    private String diagnosis3;

    private String finalDiagnosis;

    private String paafResult;

    private String isTestset;

    private String comment;

    private String otherPathologies;

    private Double majorAxis;

    private Double minorAxis;

    private String location;

    private String isMriTest;

    private String hasCalcification;

    private String hasAirball;

    private String isExitus;

    private String isTrain;

    public StudyDTO(){

    }

    public StudyDTO(Study study) {
        this.patientId = study.getPatientId();
        this.sycaiId = study.getSycaiId();
        this.modality = study.getModality() == null ? null : study.getModalityString();
        this.year = study.getYear() == null ? null : study.getYear().toString();
        this.sex = study.getSex() == null ? null : study.getSexString();
        this.birthDate = study.getBirthDate() == null ? null : study.getBirthDate().toString();
        this.isLesion = study.getIsLesion() == null ? null : study.getIsLesion() ? "Yes" : "No";
        this.isPancreas = study.getIsPancreas() == null ? null : study.getIsPancreas() ? "Yes" : "No";
        this.isTestset = study.getIsTestset() == null ? null :study.getIsTestset() ? "Yes" : "No";
        this.comment = study.getComment();
        this.isMriTest = study.getIsMriTest() == null ? null :study.getIsMriTest() ? "Yes" : "No";
        this.hasCalcification = study.getHasCalcification() == null ? null :study.getHasCalcification() ? "Yes" : "No";
        this.hasAirball = study.getHasAirball() == null ? null :study.getHasAirball() ? "Yes" : "No";
        this.otherPathologies = study.getOtherPathologies();
        this.majorAxis = study.getMajorAxis();
        this.minorAxis = study.getMinorAxis();
        this.location = study.getLocation() == null ? null : study.getLocationString();
        this.isExitus = study.getIsExitus() == null ? null : study.getIsExitus() ? "Yes" : "No";;
    }

}
