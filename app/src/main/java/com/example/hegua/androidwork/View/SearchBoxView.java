package com.example.hegua.androidwork.View;

import com.example.hegua.androidwork.baseview.BaseView;

/**
 * Created by hegua on 2018/8/6.
 */

public interface SearchBoxView extends BaseView{
    void initData();
    void showFuzzyQuseryGoodsData(String data);
    void showFuzzyQuseryGoodsDataNull(String state);
}
