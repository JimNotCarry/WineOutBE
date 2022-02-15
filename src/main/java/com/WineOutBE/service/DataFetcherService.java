package com.WineOutBE.service;

import com.WineOutBE.entity.DiaryPost;
import com.WineOutBE.entity.User;
import com.WineOutBE.graphql.inputEntities.DiaryInput;
import com.fasterxml.jackson.core.JsonProcessingException;
import graphql.schema.DataFetchingEnvironment;

public interface DataFetcherService {
    User setPost(DataFetchingEnvironment env, DiaryPost post, DiaryInput newPost);
    <T> T objectMapper(DataFetchingEnvironment env, String arg, Class<T> reqClass) throws JsonProcessingException;
}
