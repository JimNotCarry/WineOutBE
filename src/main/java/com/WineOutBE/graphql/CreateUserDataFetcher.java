package com.WineOutBE.graphql;

import com.WineOutBE.Entity.DiarySettings;
import com.WineOutBE.Entity.Role;
import com.WineOutBE.Entity.User;
import com.WineOutBE.Repo.RoleRepository;
import com.WineOutBE.Service.DataFetcherServiceImpl;
import com.WineOutBE.Service.UserService;
import com.WineOutBE.graphql.InputEntities.UserInput;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class CreateUserDataFetcher implements DataFetcher<User> {

    @Autowired
    UserService userService;

    @Autowired
    DataFetcherServiceImpl dataFetcherService;

    @Autowired
    RoleRepository roleRepository;

    @SneakyThrows
    @Override
    public User get(DataFetchingEnvironment environment) {

        User user = new User();

        UserInput newuser = dataFetcherService.objectMapper(environment,"user", UserInput.class);

        Collection<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("User"));

        user.setUsername(newuser.getUsername());
        user.setPassword(newuser.getPassword());
        user.setFirstname(newuser.getFirstname());
        user.setLastname(newuser.getLastname());
        user.setRoles(roles);
        user.setDiarySettings(new DiarySettings());
        user.setDiaryPost(new ArrayList<>());

        return userService.saveUser(user);
    }
}
