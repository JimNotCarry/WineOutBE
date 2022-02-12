package com.WineOutBE.graphql;

import com.WineOutBE.Entity.DiarySettings;
import com.WineOutBE.Entity.Role;
import com.WineOutBE.Entity.User;
import com.WineOutBE.Service.UserService;
import com.WineOutBE.graphql.InputEntities.UserInput;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Component
public class CreateUserDataFetcher implements DataFetcher<User> {

    @Autowired
    UserService userService;
    private Object Role;

    @SneakyThrows
    @Override
    public User get(DataFetchingEnvironment environment) {

        User user = new User();

        ObjectMapper objectMapper = new ObjectMapper();
        String input = objectMapper.writeValueAsString(environment.getArgument("user"));

        UserInput newuser = objectMapper.readValue(input,UserInput.class);

        user.setUsername(newuser.getUsername());
        user.setPassword(newuser.getPassword());
        user.setFirstname(newuser.getFirstname());
        user.setLastname(newuser.getLastname());
        user.setRoles(newuser.getRoles());

        user.setDiarySettings(new DiarySettings());

        return userService.saveUser(user);
    }
}
