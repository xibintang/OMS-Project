package com.nyit.oms.views;

public class UserStatus {
    private static String username;
    private static String password;
    private static Boolean isAdmin;

    // privatize the constructor:
    private UserStatus(){}

    public static void setUsername(String input){
        username = input;
    }

    public static void setPassword(String input){
        password = input;
    }

    public static String getUsername(){
        return username;
    }

    public static String getPassword(){
        return password;
    }
    public static void setIsAdmin(Boolean input){
        isAdmin = input;
    }
    public static Boolean getIsAdmin(){
        return isAdmin;
    }

}
