package com.example.hegua.androidwork.View;

import com.example.hegua.androidwork.baseview.BaseView;

/**
 * Created by hegua on 2018/8/8.
 */

public interface SetUpUserView extends BaseView{
    void showlocalImagePathNull(String state);
    void showUpImageCancelled();
    void showUpLocalImageSuccess(String state);
    void showNowUserNull(String state);
    void showSetUserImageurlNoSuccess(String state);
    void showSetUserImageurlSuccess(String state);
}
