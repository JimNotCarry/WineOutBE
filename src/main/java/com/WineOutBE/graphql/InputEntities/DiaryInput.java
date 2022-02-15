package com.WineOutBE.graphql.inputEntities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
public class DiaryInput {

    private String winename;
    private String vintage;
    private String producer;
    private String percentage;
    private String region;
    private String district;
    private String grape;
    private LocalDate occasionDate;
}
