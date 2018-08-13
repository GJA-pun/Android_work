package com.example.hegua.androidwork.presenter.goodsdaopresenter;

import com.example.hegua.androidwork.View.AddGoodsView;
import com.example.hegua.androidwork.View.HomeFragmentView;
import com.example.hegua.androidwork.View.SearchBoxView;

/**
 * Created by hegua on 2018/7/24.
 */

public interface GoodsDaoPresenter {
    void addgoods(String localPath, AddGoodsView addGoodsView);
    void findAllGoods(HomeFragmentView homeFragmentView);
    int fuzzyquserygoods(String text, SearchBoxView searchBoxView,int end);
}
