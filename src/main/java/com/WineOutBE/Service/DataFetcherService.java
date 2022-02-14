package com.WineOutBE.Service;

import com.WineOutBE.Entity.DiaryPost;
import com.WineOutBE.Entity.User;
import com.WineOutBE.graphql.InputEntities.DiaryInput;
import com.fasterxml.jackson.core.JsonProcessingException;
import graphql.schema.DataFetchingEnvironment;

public interface DataFetcherService {
    User setPost(DataFetchingEnvironment env, DiaryPost post, DiaryInput newPost);
    <T> T objectMapper(DataFetchingEnvironment env, String arg, Class<T> reqClass) throws JsonProcessingException;
}
