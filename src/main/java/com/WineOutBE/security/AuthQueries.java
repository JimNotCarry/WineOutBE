package com.WineOutBE.security;

public interface AuthQueries {
    Boolean CheckQueryValue(String query);
    String ChangeQueryValue(String query, String authName);
}
