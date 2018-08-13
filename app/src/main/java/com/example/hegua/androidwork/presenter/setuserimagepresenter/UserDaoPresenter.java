package com.example.hegua.androidwork.presenter.setuserimagepresenter;

import com.example.hegua.androidwork.View.HomeFragmentView;
import com.example.hegua.androidwork.View.SetUpUserView;

/**
 * Created by hegua on 2018/8/8.
 */

public interface UserDaoPresenter {
    void upLoadUserImage(String localImagePath,SetUpUserView setUpUserView);
    void getUserIamgeurl(String username, HomeFragmentView homeFragmentView);
}
