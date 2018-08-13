package com.example.hegua.androidwork.object.trade_object;

import java.io.Serializable;

/**
 * Created by hegua on 2018/7/24.
 */

public class Trade implements Serializable{
    private int id;
    private String name;//商品名
    private String content;//商品的简介
    private String merchant;//商品的源头
    private String username;//提交商品的用户
    private String selling_price;//商品价格
    private String distribution_price;//商品配送价格
    private int volume;//销售量
    private int fabulous;//赞
    private String type;//种类
    private String addtime;//添加时间
    private String imageurl;//图片链接地址


    public Trade(String name, String content, String merchant, String username, String selling_price,
                 String distribution_price,String type) {
        super();
        this.name = name;
        this.content = content;
        this.merchant = merchant;
        this.username = username;
        this.selling_price = selling_price;
        this.distribution_price = distribution_price;
        this.type = type;
    }

    public Trade(String name,String merchant,String content, String selling_price, String distribution_price, String type,
                 int volume, int fabulous ,String username,String imageurl) {
        super();
        this.name = name;
        this.content = content;
        this.merchant = merchant;
        this.username = username;
        this.selling_price = selling_price;
        this.distribution_price = distribution_price;
        this.volume = volume;
        this.fabulous = fabulous;
        this.type = type;
        this.imageurl = imageurl;
    }
    public Trade(){

    }

    public Trade(int id, String name, String content, String merchant, String username, String selling_price, String distribution_price, int volume, int fabulous, String type, String addtime, String imageurl) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.merchant = merchant;
        this.username = username;
        this.selling_price = selling_price;
        this.distribution_price = distribution_price;
        this.volume = volume;
        this.fabulous = fabulous;
        this.type = type;
        this.addtime = addtime;
        this.imageurl = imageurl;
    }

    public String getImageurl() {return imageurl;}
    public void setImageurl(String imageurl) {this.imageurl = imageurl;}
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
    public String getMerchant() {
        return merchant;
    }
    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }
    public String getDistribution_price() {return distribution_price;}
    public void setDistribution_price(String distribution_price) {this.distribution_price = distribution_price;}
    public int getFabulous() {
        return fabulous;
    }
    public void setFabulous(int fabulous) {
        this.fabulous = fabulous;
    }
    public int getVolume() {
        return volume;
    }
    public void setVolume(int volume) {
        this.volume = volume;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getSelling_price() {
        return selling_price;
    }
    public void setSelling_price(String selling_price) {
        this.selling_price = selling_price;
    }
    public String getAddtime() {return addtime;}
    public void setAddtime(String addtime) {this.addtime = addtime;}

    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", merchant='" + merchant + '\'' +
                ", username='" + username + '\'' +
                ", selling_price='" + selling_price + '\'' +
                ", distribution_price='" + distribution_price + '\'' +
                ", volume=" + volume +
                ", fabulous=" + fabulous +
                ", type='" + type + '\'' +
                ", addtime='" + addtime + '\'' +
                ", imageurl='" + imageurl + '\'' +
                '}';
    }
}
