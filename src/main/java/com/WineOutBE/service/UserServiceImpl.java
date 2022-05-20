package com.WineOutBE.service;

import com.WineOutBE.entity.DiaryPost;
import com.WineOutBE.entity.Role;
import com.WineOutBE.entity.User;
import com.WineOutBE.repo.RoleRepository;
import com.WineOutBE.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            System.out.println("User found");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority(role.getName()));
                }
        );
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User savePost(String username, DiaryPost post) {
        User user = userRepository.findByUsername(username);
        post.setPostDate(ZonedDateTime.now());
        user.getDiaryPost().add(post);

        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<DiaryPost> posts(String username) {
        User user = userRepository.findByUsername(username);

        return (List<DiaryPost>) user.getDiaryPost();
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        //user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserByFriendID(String friendId) {
        return userRepository.findByFriendId(friendId);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Boolean checkUsername(String requestUsername, String credentialUsername) {
        return requestUsername.equals(credentialUsername);
    }

    @Override
    public List<String> searchUser(String username) {
        return userRepository.search(username);
    }

}
