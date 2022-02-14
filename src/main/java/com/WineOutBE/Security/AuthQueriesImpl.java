package com.WineOutBE.Security;

import org.springframework.stereotype.Service;

@Service
public class AuthQueriesImpl implements AuthQueries {

    @Override
    public Boolean CheckQueryValue(String query) {
        return query.contains("DEFAULTUSERNAMEVALUE");
    }

    public String ChangeQueryValue(String query, String authName) {
        return query.replace("DEFAULTUSERNAMEVALUE", authName);
    }
}
