package com.WineOutBE.Entity;


import javax.persistence.*;

@Entity
@Table(name="Users")
public class User {

    @OneToOne
    private FriendID FID;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name= "id")
    private int id;

    @Column(name= "username")
    private String username;

    @Column(name= "password")
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public FriendID getFriendID() {
        return FID;
    }

    public void setFID(FriendID FID) {
        this.FID = FID;
    }
}
