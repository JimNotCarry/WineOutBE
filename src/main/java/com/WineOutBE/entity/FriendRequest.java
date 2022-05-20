package com.WineOutBE.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="FriendRequests")
public class FriendRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "requestID")
    private Long id;

    @Column(name = "requester")
    private String requester;

    @Column(name = "reciever")
    private String reciever;

    @Column(name = "status")
    private int status = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
