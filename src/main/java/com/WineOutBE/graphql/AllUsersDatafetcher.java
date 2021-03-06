package com.WineOutBE.graphql;

import com.WineOutBE.entity.User;
import com.WineOutBE.service.UserService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllUsersDatafetcher implements DataFetcher<List<User>> {

    @Autowired
    UserService userService;

    @Override
    public List<User> get(DataFetchingEnvironment environment) {
        return userService.getUsers();
    }
}
