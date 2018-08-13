package com.example.hegua.androidwork.View;

import com.example.hegua.androidwork.baseview.BaseView;
import com.example.hegua.androidwork.object.hint_object.GoodsDaoHint;

/**
 * Created by hegua on 2018/7/31.
 */

/**
 * settradeDistributionprice.setText(trade.getDistribution_price());
 settradeSellingprice.setText(trade.getSelling_price());
 settradeContent.setText(trade.getContent());
 settradeMerchant.setText(trade.getMerchant());
 */
public interface UpDateGoodsView extends BaseView{
    void initData();
    void showUpDateFall(GoodsDaoHint goodsDaoHint);
    void showUpDateSuccess(String state);
    int getTradeId();
    String getTradeDistributionPrice();
    String getTradeSellingPrice();
    String getTradeContent();
}
