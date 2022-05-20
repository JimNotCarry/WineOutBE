package com.WineOutBE.generator;

import com.WineOutBE.service.UserService;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Serializable;

public class GenFriendID implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

        StringBuilder id = new StringBuilder();

        for(int index = 0; index <= 5; index++) {
            int randomType = (int) (Math.random() * 2);
            char character = (randomType == 0) ? generateCharacter() : generateNumber();
            id.append(character);
        }

        return id.toString();
    }

    public char generateCharacter() {
        int rnd = (int) (Math.random() * 26); // or use Random or whatever
        char base = 'A';
        return (char) (base + rnd % 26);
    }

    public char generateNumber() {
        int rnd = (int) (Math.random() * 10); // or use Random or whatever
        char base = '0';
        return (char) (base + rnd % 10);
    }


}
