package com.example.hegua.androidwork.object.shoppingcart_object;

/**
 * Created by hegua on 2018/8/7.
 */

public class ShoppingCart {
    private int id;
    private String username;
    private String name_merchant;
    private String addtime;
    private int number;

    public ShoppingCart(int id, String username, String name_merchant) {
        this.id = id;
        this.username = username;
        this.name_merchant = name_merchant;
    }

    public ShoppingCart(String username, String name_merchant, int number) {
        this.username = username;
        this.name_merchant = name_merchant;
        this.number = number;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {this.username = username;}
    public String getName_merchant() {
        return name_merchant;
    }
    public void setName_merchant(String name_merchant) {
        this.name_merchant = name_merchant;
    }
    public int getNumber() {return number;}
    public void setNumber(int number) {this.number = number;}
    public String getAddtime() {return addtime;}
    public void setAddtime(String addtime) {this.addtime = addtime;}

    @Override
    public String toString() {
        return "{\"id\":" + id + ",\"username\":\"" + username + "\",\"trade_user\":\"" + name_merchant + "\"}";
    }
}
