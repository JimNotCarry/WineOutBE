package com.WineOutBE.Security;

public interface AuthQueries {
    Boolean CheckQueryValue(String query);
    String ChangeQueryValue(String query, String authName);
}
