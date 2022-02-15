package com.WineOutBE.graphql;

import com.WineOutBE.entity.DiarySettings;
import com.WineOutBE.entity.Role;
import com.WineOutBE.entity.User;
import com.WineOutBE.repo.RoleRepository;
import com.WineOutBE.service.DataFetcherServiceImpl;
import com.WineOutBE.service.UserService;
import com.WineOutBE.graphql.inputEntities.UserInput;
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
