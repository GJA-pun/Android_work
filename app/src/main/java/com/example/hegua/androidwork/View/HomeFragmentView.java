package com.example.hegua.androidwork.View;

import com.example.hegua.androidwork.baseview.BaseView;

/**
 * Created by hegua on 2018/8/5.
 */

public interface HomeFragmentView extends BaseView{
    void setTrades(String data);
    void showUserNoExis (String state);
    void showUserNoImageurl ();
    void showUserImageurl ();
}
