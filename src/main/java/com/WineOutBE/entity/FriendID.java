package com.WineOutBE.entity;

import javax.persistence.*;

@Entity
@Table(name = "FriendID")
public class FriendID {

    @Id
    @Column(name = "FID", unique = true, length = 10)
    private String FID;

    public String getId() {
        return FID;
    }

    public void setId(String FID) {
        this.FID = FID;
    }
}
