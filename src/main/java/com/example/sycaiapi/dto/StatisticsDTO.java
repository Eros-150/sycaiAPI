package com.example.sycaiapi.dto;

import com.example.sycaiapi.persistence.entity.Labeler;
import lombok.Data;

import java.util.*;

@Data
public class StatisticsDTO {
    private List<StatsDTO> statsDTOList = new ArrayList<>();
    private List<LabelsDTO> labelers = new ArrayList<>();
    private Map<String, Integer[]> lesions = new HashMap<>();
}
