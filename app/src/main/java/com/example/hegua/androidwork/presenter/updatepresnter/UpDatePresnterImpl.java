package com.example.hegua.androidwork.presenter.updatepresnter;

import android.content.Context;

import com.example.hegua.androidwork.View.UpDateGoodsView;
import com.example.hegua.androidwork.object.hint_object.GoodsDaoHint;
import com.example.hegua.androidwork.presenter.asynctask.UpDateGoodsAsyncTask;
import com.example.hegua.androidwork.utils.Constant;

/**
 * Created by hegua on 2018/7/31.
 */

public class UpDatePresnterImpl implements UpDatePresnter {
    private Context mContext;
    private UpDateGoodsView upDateGoodsView;

    public UpDatePresnterImpl(Context mContext, UpDateGoodsView upDateGoodsView) {
        this.mContext = mContext;
        this.upDateGoodsView = upDateGoodsView;
    }

    @Override
    public void upDate() {
        GoodsDaoHint goodsDaoHint = new GoodsDaoHint();
        boolean flag = false;

        if(upDateGoodsView.getTradeContent()==null|| upDateGoodsView.getTradeContent().equals("")){
            flag = true;
            goodsDaoHint.setContentHint("简介不能为空");
        }
        if(upDateGoodsView.getTradeSellingPrice()==null|| upDateGoodsView.getTradeSellingPrice().equals("")){
            flag = true;
            goodsDaoHint.setSellingPriceHint("销售价格不能为空");
        }
        if(upDateGoodsView.getTradeDistributionPrice()==null|| upDateGoodsView.getTradeDistributionPrice().equals("")){
            flag = true;
            goodsDaoHint.setDistributionPriceHint("配送价格不能为空");
        }

        if (flag){
            upDateGoodsView.showUpDateFall(goodsDaoHint);
            return;
        }

        String data = "?content="+upDateGoodsView.getTradeContent()+"&selling_price="+upDateGoodsView.getTradeSellingPrice()
                +"&distribution_price="+upDateGoodsView.getTradeDistributionPrice()
                +"&id="+upDateGoodsView.getTradeId();

        String url = Constant.URL+Constant.UPDATETRADE+data;
        //System.out.println("update url------------------------------------>"+url);

        UpDateGoodsAsyncTask upDateGoodsAsyncTask = new UpDateGoodsAsyncTask(url,upDateGoodsView);
        upDateGoodsAsyncTask.execute();

    }
}
