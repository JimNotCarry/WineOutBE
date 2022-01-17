package com.WineOutBE.Service;

import com.WineOutBE.Entity.User;
import com.WineOutBE.Repo.UserRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDataFetcher implements DataFetcher<Optional<User>> {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> get(DataFetchingEnvironment environment) throws Exception {

        int username = environment.getArgument("id");

        return userRepository.findById(username);
    }
}
