package com.example.hegua.androidwork.View;

import com.example.hegua.androidwork.baseview.BaseView;

/**
 * Created by hegua on 2018/8/7.
 */

public interface ShowGoodsView extends BaseView{
    void initData();
    void showAddShoppingCartSuccess(String state);
    void showAddShoppingCartNoSuccess(String state);
}
