package com.WineOutBE.service;

import com.WineOutBE.entity.DiaryPost;
import com.WineOutBE.entity.Role;
import com.WineOutBE.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User savePost(String username, DiaryPost post);
    User updateUser(User user);
    List<DiaryPost> posts(String username);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    User getUserByFriendID(String friendId);
    List<User> getUsers();
    Boolean checkUsername(String requestUsername, String credentialUsername);
    List<String> searchUser(String username);
}
