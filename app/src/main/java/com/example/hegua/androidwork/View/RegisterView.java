package com.example.hegua.androidwork.View;

import com.example.hegua.androidwork.baseview.BaseView;
import com.example.hegua.androidwork.object.hint_object.RegisterHint;

/**
 * Created by hegua on 2018/7/17.
 */

public interface RegisterView extends BaseView {

    String getUsername();
    String getPassword();
    String getRepassword();

    void showRegistering();//显示注册中

    void showRegisterSuccess(String state);//显示注册成功

    void showRegisterFall(RegisterHint registerHint);//显示注册失败，并说明服务器注册错误的原因

    void showRegisterNoSuccess(String state);//显示注册失败，用户名已存在
}
