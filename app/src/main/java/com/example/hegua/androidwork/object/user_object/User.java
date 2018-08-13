package com.example.hegua.androidwork.object.user_object;

/**
 * Created by hegua on 2018/7/16.
 */

public class User {
    private String username;
    private String password;
    private String repassword;
    private String userimage;
    private int id;

    public User (String username,String password){
        this.username = username;
        this.password = password;
    }

    public User (String username,String password,String repassword){
        this.username = username;
        this.password = password;
        this.repassword = repassword;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
        this.userimage = userimage;
    }
}
