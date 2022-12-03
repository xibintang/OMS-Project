package com.nyit.oms.views;

public class UserObject {
    private Integer userID;
    private String username;
    private String password;
    private Boolean isAdmin;

    public UserObject(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUserID(Integer userID){
        this.userID = userID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setIsAdmin(Boolean isAdmin){
        this.isAdmin = isAdmin;
    }

    public Boolean isAdmin() {
        return isAdmin;
    }

    @Override
    public String toString() {
        return "UserObject{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
