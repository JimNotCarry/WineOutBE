package com.WineOutBE.graphql.InputEntities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class DiaryInput {

    private String winename;
    private String vintage;
    private String producer;
    private String percentage;
    private String region;
    private String district;
    private String grape;
}
