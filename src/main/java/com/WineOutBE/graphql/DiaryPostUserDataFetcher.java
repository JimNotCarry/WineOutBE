package com.WineOutBE.graphql;

import com.WineOutBE.entity.DiaryPost;
import com.WineOutBE.entity.User;
import com.WineOutBE.repo.DiaryRepository;
import com.WineOutBE.service.DataFetcherServiceImpl;
import com.WineOutBE.service.UserService;
import com.WineOutBE.graphql.inputEntities.DiaryInput;
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

        System.out.println(newpost);

        return userService.updateUser(dataFetcherService.setPost(environment, new DiaryPost(), newpost));
    }
}
