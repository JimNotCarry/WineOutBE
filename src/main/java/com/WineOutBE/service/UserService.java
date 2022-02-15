package com.WineOutBE.service;

import com.WineOutBE.entity.Role;
import com.WineOutBE.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User updateUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
    Boolean checkUsername(String requestUsername, String credentialUsername);
}
