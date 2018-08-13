package com.example.hegua.androidwork.View;

import com.example.hegua.androidwork.baseview.BaseView;
import com.example.hegua.androidwork.object.hint_object.GoodsDaoHint;

/**
 * Created by hegua on 2018/7/23.
 */
public interface AddGoodsView extends BaseView{
    String getTradeName();
    String getTradeMerchant();
    String getTradeContent();
    String getTradeSellingPrice();
    String getTradeDistributionPrice();
    String getTradeType();

    void initData();

    void showAddGoodsSuccess(String state);
    void showAddGoodsFall(GoodsDaoHint goodsDaoHint);
    void showAddGoodsNoSuccess(String state);
    void showUpImageCancelled();
    void showUnselectedPicture(String state);

}
