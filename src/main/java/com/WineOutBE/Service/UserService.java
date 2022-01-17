package com.WineOutBE.Service;

import com.WineOutBE.Entity.FriendID;
import com.WineOutBE.Entity.User;
import com.WineOutBE.Repo.FriendIDRepository;
import com.WineOutBE.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FriendIDRepository friendIDRepository;


    public void createUser(String username, String password, FriendID FID) {

        friendIDRepository.save(FID);

        User user = new User();

        user.setFID(FID);

        user.setUsername(username);
        user.setPassword(password);


        userRepository.save(user);

    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
