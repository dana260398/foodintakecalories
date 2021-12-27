package com.javacodegeeks.foodcalorieintake;

public class LoginModel {

    private String id,username,password;

    //contructors


    public LoginModel(String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public LoginModel() {
    }

    //toString


    @Override
    public String toString() {
        return "LoginModel{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
