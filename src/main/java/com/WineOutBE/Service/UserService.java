package com.WineOutBE.Service;

import com.WineOutBE.Entity.Role;
import com.WineOutBE.Entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
    Boolean checkUsername(String requestUsername, String credentialUsername);
}
