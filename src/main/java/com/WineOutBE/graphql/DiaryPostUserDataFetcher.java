package com.WineOutBE.graphql;

import com.WineOutBE.Entity.DiaryPost;
import com.WineOutBE.Entity.User;
import com.WineOutBE.Repo.DiaryRepository;
import com.WineOutBE.Service.DataFetcherServiceImpl;
import com.WineOutBE.Service.UserService;
import com.WineOutBE.graphql.InputEntities.DiaryInput;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiaryPostUserDataFetcher implements DataFetcher<User> {

    @Autowired
    UserService userService;

    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    DataFetcherServiceImpl dataFetcherService;

    @SneakyThrows
    @Override
    public User get(DataFetchingEnvironment environment) {

        DiaryInput newpost = dataFetcherService.objectMapper(environment,"diary", DiaryInput.class);

        return userService.updateUser(dataFetcherService.setPost(environment, new DiaryPost(), newpost));
    }
}
