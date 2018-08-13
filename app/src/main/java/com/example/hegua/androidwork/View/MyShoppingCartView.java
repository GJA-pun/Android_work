package com.example.hegua.androidwork.View;

import com.example.hegua.androidwork.baseview.BaseView;
import com.example.hegua.androidwork.object.shoppingcart_object.ShoppingCart;

/**
 * Created by hegua on 2018/8/7.
 */

public interface MyShoppingCartView extends BaseView{
    void getShoppingCartData(String data);
    void showShoppingCartGetTradeSuccess(String state);
    void showShoppingCartGetTradeNoSuccess(String data);
    void showShoppingCartGetNull();
    void showDeleteShoppingCartSuccess(String state);
    void showDeleteShoppingCartNoSuceess(String state);
    void buyShoppingCartTrade(ShoppingCart shoppingCart);
    void buyShoppingCartTradeSuccess(String name_merchant);
    void buyShoppingCartTradeNoSuccess(String state);
    void fabulousShoppingCartTradeSuccess();
    void fabulousShoppingCartTradeNoSuccess(String state);
}
