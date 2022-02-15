package com.WineOutBE.graphql;

import com.WineOutBE.entity.User;
import com.WineOutBE.repo.UserRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDatafetcher implements DataFetcher<User> {

    @Autowired
    UserRepository userRepository;

    @Override
    public User get(DataFetchingEnvironment environment) {

        String username = environment.getArgument("username");

        return userRepository.findByUsername(username);
    }
}
