package com.WineOutBE.api;

import com.WineOutBE.Security.AuthQueries;
import com.WineOutBE.graphql.GraphQLService;
import graphql.ExecutionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {

    private final GraphQLService graphQLService;
    private final AuthQueries authQueries;

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
}
