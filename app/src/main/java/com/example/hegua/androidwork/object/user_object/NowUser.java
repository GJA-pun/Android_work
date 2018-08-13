package com.example.hegua.androidwork.object.user_object;

/**
 * Created by hegua on 2018/7/16.
 */
public class NowUser {
    public static User user = null;
    public static void setUser(User nowuser){
        user = nowuser;
    }
    public static User getUser(){
        return user;
    }
}
