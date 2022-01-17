package com.WineOutBE.Controller;

import com.WineOutBE.Entity.FriendID;
import com.WineOutBE.Entity.User;
import com.WineOutBE.Service.GraphQLService;
import com.WineOutBE.Service.UserService;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    GraphQLService graphQLService;

    @GetMapping("/addUsers")
    public List<User> GetUser() {

        FriendID FID = new FriendID();
        FriendID FID2 = new FriendID();
        FriendID FID3 = new FriendID();
        FriendID FID4 = new FriendID();


        FID.setId("Q5XKL");
        FID2.setId("PO8KL");
        FID3.setId("1NE3M");
        FID4.setId("R8TA3");

        userService.createUser("Lhberg","pass123", FID);
        userService.createUser("Linda7","Lindz0r1337", FID2);
        userService.createUser("KimpanB","Kimp1447", FID3);
        userService.createUser("Bellabus","Bellz0r603", FID4);

        return userService.getAllUsers();
    };

    @PostMapping("/getData")
    public ResponseEntity<Object> getData(@RequestBody String query) {
       ExecutionResult execute = graphQLService.getGraphQL().execute((query));

       return new ResponseEntity<>(execute, HttpStatus.OK);
    };

}
