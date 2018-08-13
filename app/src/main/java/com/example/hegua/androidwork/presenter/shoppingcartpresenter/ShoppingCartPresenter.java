package com.example.hegua.androidwork.presenter.shoppingcartpresenter;

import android.content.Context;

import com.example.hegua.androidwork.View.MyShoppingCartView;
import com.example.hegua.androidwork.View.ShowGoodsView;
import com.example.hegua.androidwork.object.shoppingcart_object.ShoppingCart;
import com.example.hegua.androidwork.object.trade_object.Trade;

/**
 * Created by hegua on 2018/8/7.
 */

public interface ShoppingCartPresenter {
    void addShoppingCart(ShoppingCart shoppingCart, ShowGoodsView showGoodsView);
    void getShoppingCartData(String username, MyShoppingCartView myShoppingCartView);
    Trade setShoppingCartTrade(String name, String merchant, MyShoppingCartView myShoppingCartView, Context mContext);
    void deleteShoppingCart(int id,MyShoppingCartView myShoppingCartView);
    void buyShoppingCartGoods(ShoppingCart shoppingCart,MyShoppingCartView myShoppingCartView);
    void fabulousShoppingCartGoods(String name_merchant,int fabulous,MyShoppingCartView myShoppingCartView);
}
