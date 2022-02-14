package com.WineOutBE.api;

import com.WineOutBE.Entity.DiarySettings;
import com.WineOutBE.Entity.User;
import com.WineOutBE.Security.AuthQueries;
import com.WineOutBE.Service.UserService;
import com.WineOutBE.graphql.GraphQLService;
import graphql.ExecutionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {

    private final GraphQLService graphQLService;
    private final AuthQueries authQueries;
    private final UserService userService;

    @PostMapping("/checkAuth")
    public ResponseEntity<HttpStatus> checkAuth() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/userdata")
    public ResponseEntity<Object> getData(@RequestBody String query, HttpServletRequest req, Authentication auth) {

        boolean check = authQueries.CheckQueryValue(query);

        System.out.println(query);

        if (check) {
            query = authQueries.ChangeQueryValue(query, auth.getName());
            ExecutionResult execute = graphQLService.getGraphQL().execute(query);
            return new ResponseEntity<>(execute, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/allusersdata")
    public ResponseEntity<Object> allusers(@RequestBody String query) {
        try {
            ExecutionResult execute = graphQLService.getGraphQL().execute(query);
            return new ResponseEntity<>(execute,HttpStatus.OK);
        } catch(Exception e) {
            System.out.print(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/impluser")
    public ResponseEntity<Object> implUser(@RequestBody String query) {

        try {
            ExecutionResult execute = graphQLService.getGraphQL().execute(query);
            return new ResponseEntity<>(execute, HttpStatus.OK);

        } catch(Exception e) {
            System.out.println(e);

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
   }

    @PostMapping("/diarypost")
    public ResponseEntity<Object> diaryPost(@RequestBody String query, Authentication auth) {
        try {
            if (authQueries.CheckQueryValue(query)) {
                query = authQueries.ChangeQueryValue(query, auth.getName());
                ExecutionResult execute = graphQLService.getGraphQL().execute(query);
                return new ResponseEntity<>(execute, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

        } catch(Exception e) {
            System.out.println(e);

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/testuser")
    public User createUser() {
        User user = new User();

        user.setUsername("lhberg");
        user.setPassword("Pass123");
        user.setFirstname("Linus");
        user.setLastname("Hellberg");
        user.setDiarySettings(new DiarySettings());

        userService.saveUser(user);

        return user;
    }
}
