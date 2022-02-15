package com.WineOutBE.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id")
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name= "username", unique = true)
    private String username;

    @Column(name= "password")
    private String password;

    @ManyToMany(fetch = EAGER)
    private Collection<Role> roles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "settingID", referencedColumnName = "settingID")
    private DiarySettings diarySettings;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<DiaryPost> diaryPost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public DiarySettings getDiarySettings() {
        return diarySettings;
    }

    public void setDiarySettings(DiarySettings diarySettings) {
        this.diarySettings = diarySettings;
    }

    public Collection<DiaryPost> getDiaryPost() {
        return diaryPost;
    }

    public void setDiaryPost(Collection<DiaryPost> diaryPost) {
        this.diaryPost = diaryPost;
    }
}
