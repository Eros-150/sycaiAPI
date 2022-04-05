package com.example.sycaiapi.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

/** Data Transfer Object for Label count */
@Data
@AllArgsConstructor
public class LabelsDTO {
    String lesionName;
    int countLabel1;
    int countLabel2;
    int countLabel3;
    //int countLabel4;
    //int countLabel5;
}
