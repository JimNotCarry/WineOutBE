package com.WineOutBE.Entity;

import net.bytebuddy.utility.dispatcher.JavaDispatcher;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "FriendList")
public class FriendList {

    @ManyToMany(targetEntity = FriendID.class)
    private Set friendID;

    @Id
    @Column(name = "UserFID")
    private String UserFID;

    @Column(name = "FID")
    private String FID;
}
