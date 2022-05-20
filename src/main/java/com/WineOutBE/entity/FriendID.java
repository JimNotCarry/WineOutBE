package com.WineOutBE.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FriendID")
public class FriendID implements Serializable {

    @Id
    @GenericGenerator(name = "friend_id", strategy = "com.WineOutBE.generator.GenFriendID")
    @GeneratedValue(generator = "friend_id")
    @Column(name = "FriendID", unique = true, length = 6)
    private String friendid;

    public String getId() {
        return friendid;
    }

    public void setId(String FID) {
        this.friendid = FID;
    }
}
