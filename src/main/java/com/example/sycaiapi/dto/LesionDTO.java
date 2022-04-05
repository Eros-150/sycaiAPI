package com.example.sycaiapi.dto;

public class LesionDTO {
    String lesionName;
    int count;

    public LesionDTO(String lesionName, int count) {
        this.lesionName = lesionName;
        this.count = count;
    }

    public String getLesionName() {
        return lesionName;
    }

    public void setLesionName(String lesionName) {
        this.lesionName = lesionName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
