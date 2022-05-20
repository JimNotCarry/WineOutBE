package com.WineOutBE;

import com.WineOutBE.entity.FriendID;
import com.WineOutBE.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Testing {

    UserService userService;

    FriendID friendID;

    @Test
    void checkCorrectIdValue(){
        friendID = new FriendID();

        assertEquals(1234L, friendID.getId());

    }
}