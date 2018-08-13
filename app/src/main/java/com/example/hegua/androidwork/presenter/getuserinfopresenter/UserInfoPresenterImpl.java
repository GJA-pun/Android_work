package com.example.hegua.androidwork.presenter.getuserinfopresenter;

import com.example.hegua.androidwork.object.user_object.NowUser;
import com.example.hegua.androidwork.object.user_object.User;
import com.example.hegua.androidwork.utils.ReUserInfo;

/**
 * Created by hegua on 2018/7/19.
 */

public class UserInfoPresenterImpl implements UserInfoPresenter {

    public UserInfoPresenterImpl() {
    }

    @Override
    public void getUserInfo() {
        User user = ReUserInfo.getUserInfo();
        NowUser.setUser(user);
    }

    @Override
    public void saveUserInfo(String username,String password) {
        ReUserInfo.saveUserInfo(username, password);
    }
}
