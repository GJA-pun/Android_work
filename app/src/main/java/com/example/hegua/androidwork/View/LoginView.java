package com.example.hegua.androidwork.View;

import com.example.hegua.androidwork.baseview.BaseView;
import com.example.hegua.androidwork.object.hint_object.LoginHint;

/**
 * Created by hegua on 2018/7/17.
 */

public interface LoginView extends BaseView {

    String getUsername();
    String getPassword();

    void showLogining();//显示登录中

    void showLoginSuccess(String state);//显示登录成功

    void showLoginFall(LoginHint loginHint);//显示登录失败

    void showLoginNoSuccess(String state);//显示登录失败，密码和用户名不一致

    void askSaveUserInfo (String username,String password);//询问是否记住用户名和密码

    void showNowUser(String username,String password);//在输入框中填入已经登录的用户名和密码
}
