package com.WineOutBE.api;

import com.WineOutBE.entity.*;
import com.WineOutBE.repo.FriendListRepository;
import com.WineOutBE.repo.FriendRequestRepository;
import com.WineOutBE.security.AuthQueries;
import com.WineOutBE.service.UserService;
import com.WineOutBE.graphql.GraphQLService;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.ParseAndValidate;
import graphql.execution.Execution;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.json.Json;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Parser;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {

    private final GraphQLService graphQLService;
    private final AuthQueries authQueries;
    private final UserService userService;
    private final FriendRequestRepository friendRequestRepository;
    private final FriendListRepository friendListRepository;


    @PostMapping("/checkAuth")
    public ResponseEntity<HttpStatus> checkAuth() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/postdiary")
    public void postDiary(@RequestBody DiaryPost post, Authentication auth) {
    try {
        String username = auth.getName();

        userService.savePost(username, post);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

//    @GetMapping("/randomnumber")
//    public String getrandomnumber() {
//        StringBuilder id = new StringBuilder();
//
//        for(int index = 0; index <= 5; index++) {
//
//            int randomType = (int) (Math.random() * 2);
//
//            System.out.println(randomType);
//
//            char character = (randomType == 0) ? userService.generateCharacter() : userService.generateNumber();
//
//            id.append(character);
//        }
//
//        return id.toString();
//    }

    @GetMapping("/getallusersdata")
    public List<User> getAllUserData() {

        return userService.getUsers();
    }

    @GetMapping("/testpreauth")
    @PreAuthorize("#username == authentication.name")
    public List<User> testauth(@RequestParam String username) {

        return userService.getUsers();
    }

    @GetMapping("/posts")
    public ResponseEntity<Object> getPosts(Authentication auth) {
        try {
            String username = auth.getName();

            System.out.println(auth.getName());

            return new ResponseEntity<>(userService.posts(username), HttpStatus.OK);
        } catch(Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/searchUser")
    public ResponseEntity<Object> searchUser(@RequestParam String username, Authentication auth) {

        try {
            List<String> users = userService.searchUser(username).stream().filter(user -> !user.equals(auth.getName())).collect(Collectors.toList());

            return new ResponseEntity<>(users, HttpStatus.OK);

        } catch(Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getFriendId")
    public String getFriendId(Authentication auth) {

        User user = userService.getUser(auth.getName());

        return user.getFriendID().getId();
    }

    @GetMapping("/sendfriendrequest")
    public void friendRequest(@RequestParam (required = true) String friendid, Authentication auth) {
        FriendRequest friendRequest = new FriendRequest();

        try {
            User requester = userService.getUser(auth.getName());
            User reciever = userService.getUserByFriendID(friendid);

            List<FriendList> userlist = reciever.getFriendList().stream().filter(data -> data.getFriendId().equals(requester.getFriendID().getId())).collect(Collectors.toList());
            FriendRequest friendRequestList = friendRequestRepository.checkFriendRequestSingle(reciever.getFriendID().getId(), requester.getFriendID().getId());

            if(userlist.size() > 0 || friendRequestList != null) {
                System.out.println("Already exists");
            } else {
                System.out.println("Doesn't exists");
                friendRequest.setRequester(requester.getFriendID().getId());
                friendRequest.setReciever(reciever.getFriendID().getId());

                friendRequestRepository.save(friendRequest);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @GetMapping("/checkFriendRequests")
    public List<Object> checkFriendRequests(Authentication auth) {

        User reciever = userService.getUser(auth.getName());

        List<FriendRequest> friendRequests = friendRequestRepository.checkFriendRequest(reciever.getFriendID().getId());

        List<User> users = friendRequests.stream().map(req -> userService.getUserByFriendID(req.getRequester())).collect(Collectors.toList());

        return users.stream().map(user -> Stream.of(user.getUsername(), user.getFirstname(), user.getLastname())).collect(Collectors.toList());
    }

    @GetMapping("/getFriend")
    public List<Object> getFriends(Authentication auth) {

        User reciever = userService.getUser(auth.getName());

        //List<FriendList> friendLists = new ArrayList<>(reciever.getFriendList());

        List<User> friendList = reciever.getFriendList().stream().map(friend -> userService.getUserByFriendID(friend.getFriendId())).collect(Collectors.toList());

        return friendList.stream().map(user -> Stream.of(user.getUsername(), user.getFirstname(), user.getLastname())).collect(Collectors.toList());
    }

    @GetMapping("/acceptfriendrequest")
    public void acceptFriendRequest(@RequestParam String username, Authentication auth) {

        User reciever = userService.getUser(auth.getName());
        User requester = userService.getUser(username);

        List<FriendList> userlist = reciever.getFriendList().stream().filter(data -> data.getFriendId().equals(requester.getFriendID().getId())).collect(Collectors.toList());

        if(userlist.size() > 0) {
            System.out.println("Already exists");
        } else {
            FriendList friendList = new FriendList();
            friendList.setFriendId(requester.getFriendID().getId());
            reciever.getFriendList().add(friendList);
            friendListRepository.save(friendList);
            FriendRequest friendRequest = friendRequestRepository.checkFriendRequestSingle(reciever.getFriendID().getId(), requester.getFriendID().getId());
            friendRequest.setStatus(1);
            friendRequestRepository.save(friendRequest);
        }
    }

    @PostMapping("/userdata")
    //@PreAuthorize("#defaultString == 'DEFAULTVALUE'")
    public ResponseEntity<Object> getData(@RequestBody String query, String defaultString, HttpServletRequest req, Authentication auth) {

        boolean check = authQueries.CheckQueryValue(query);
        System.out.println(query);

        if(check) {
            query = authQueries.ChangeQueryValue(query, auth.getName());

            ExecutionInput input = ExecutionInput.newExecutionInput().query(query).build();

            ExecutionResult execute = graphQLService.getGraphQL().execute(input.getQuery());
            return new ResponseEntity<>(execute, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/allusersdata")
    public ResponseEntity<Object> allusers(@RequestBody String query) {
        try {
            ExecutionResult execute = graphQLService.getGraphQL().execute(query);
            System.out.println(query);
            return new ResponseEntity<>(execute,HttpStatus.OK);
        } catch(Exception e) {
            System.out.print(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/firstname")
    public String getFirstname(Authentication authentication) {
        User user = userService.getUser(authentication.getName());
        System.out.println(user.getFirstname());
        return user.getFirstname();
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
        System.out.println(query);
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
