package com.example.sycaiapi.dto;

import lombok.Data;
@Data
public class StatsDTO {
    private String sycaiId;
    private String lesionName;
    private boolean[] labeler= new boolean[3];
}