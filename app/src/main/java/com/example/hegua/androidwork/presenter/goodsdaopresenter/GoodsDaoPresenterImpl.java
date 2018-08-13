package com.example.hegua.androidwork.presenter.goodsdaopresenter;

import com.example.hegua.androidwork.View.AddGoodsView;
import com.example.hegua.androidwork.View.HomeFragmentView;
import com.example.hegua.androidwork.View.SearchBoxView;
import com.example.hegua.androidwork.object.hint_object.GoodsDaoHint;
import com.example.hegua.androidwork.object.trade_object.Trade;
import com.example.hegua.androidwork.object.user_object.NowUser;
import com.example.hegua.androidwork.presenter.asynctask.AddGoodsAsyncTask;
import com.example.hegua.androidwork.presenter.asynctask.FindAllGoodsAsyncTask;
import com.example.hegua.androidwork.presenter.asynctask.FuzzyQuseryGoodsAsyncTask;
import com.example.hegua.androidwork.presenter.asynctask.UploadGoodsIMageAsyncTask;
import com.example.hegua.androidwork.utils.Constant;

/**
 * Created by hegua on 2018/7/24.
 */

public class GoodsDaoPresenterImpl implements GoodsDaoPresenter {
    private int start = 0;
    private int end = start+10;
    private Trade trade;

    @Override
    public void addgoods(String loaclPath,AddGoodsView addGoodsView) {

        GoodsDaoHint goodsDaoHint = new GoodsDaoHint();
        boolean flag = false;

        if(addGoodsView.getTradeName()==null|| addGoodsView.getTradeName().equals("")){
            flag = true;
            goodsDaoHint.setNameHint("商品名不能为空");
        }
        if(addGoodsView.getTradeMerchant()==null|| addGoodsView.getTradeMerchant().equals("")){
            flag = true;
            goodsDaoHint.setMerchantHint("源头不能为空");
        }
        if(addGoodsView.getTradeContent()==null|| addGoodsView.getTradeContent().equals("")){
            flag = true;
            goodsDaoHint.setContentHint("简介不能为空");
        }
        if(addGoodsView.getTradeSellingPrice()==null|| addGoodsView.getTradeSellingPrice().equals("")){
            flag = true;
            goodsDaoHint.setSellingPriceHint("销售价格不能为空");
        }
        if(addGoodsView.getTradeDistributionPrice()==null|| addGoodsView.getTradeDistributionPrice().equals("")){
            flag = true;
            goodsDaoHint.setDistributionPriceHint("配送价格不能为空");
        }
        if(addGoodsView.getTradeType()==null|| addGoodsView.getTradeType().equals("")){
            flag = true;
            goodsDaoHint.setTypeHint("商品种类不能为空");
        }
        if (loaclPath==null||loaclPath.equals("")){
            flag = true;
            goodsDaoHint.setLocalPath("未获取图片");
        }

        if(flag){
            addGoodsView.showAddGoodsFall(goodsDaoHint);
            return;
        }

        String imagename = addGoodsView.getTradeName()+"."+addGoodsView.getTradeMerchant();
        String imageurl = Constant.URL+Constant.LOADIMAGE+imagename;

        trade = new Trade(addGoodsView.getTradeName(), addGoodsView.getTradeMerchant(),addGoodsView.getTradeContent()
                , addGoodsView.getTradeSellingPrice(), addGoodsView.getTradeDistributionPrice()
                , addGoodsView.getTradeType(),0,0,NowUser.getUser().getUsername(),imageurl);

        String data = "?name="+trade.getName()+"&merchant="+trade.getMerchant()+"&content="+trade.getContent()+"&selling_price="+trade.getSelling_price()
                +"&distribution_price="+trade.getDistribution_price()+"&type="+trade.getType()+"&volume="+trade.getVolume()+"&fabulous="+trade.getFabulous()+
                "&username="+trade.getUsername()+"&imageurl="+trade.getImageurl();

        String url = Constant.URL+Constant.ADDTRADE+data;

        String[] strings = {loaclPath,imagename};
        UploadGoodsIMageAsyncTask uploadGoodsIMageAsyncTask = new UploadGoodsIMageAsyncTask(addGoodsView,"addGoodView");
        uploadGoodsIMageAsyncTask.execute(strings);

        AddGoodsAsyncTask addGoodsAsyncTask = new AddGoodsAsyncTask(addGoodsView,url);
        addGoodsAsyncTask.execute(trade);
    }

    @Override
    public void findAllGoods(HomeFragmentView homeFragmentView) {
        String url = Constant.URL+Constant.FINDALLTRADE;

        FindAllGoodsAsyncTask findAllGoodsAsyncTask = new FindAllGoodsAsyncTask(url,homeFragmentView);
        findAllGoodsAsyncTask.execute();

    }

    @Override
    public int fuzzyquserygoods(String text, SearchBoxView searchBoxView,int end) {
        if (text!=null&&text.equals("")==false) {
            String data = "?text=" + text+"&start="+0+"&end="+end;
            String url = Constant.URL + Constant.FUZZYQUSERYTRADE + data;

            FuzzyQuseryGoodsAsyncTask fuzzyQuseryGoodsAsyncTask = new FuzzyQuseryGoodsAsyncTask(url,searchBoxView);
            fuzzyQuseryGoodsAsyncTask.execute();
        }else {
            searchBoxView.showFuzzyQuseryGoodsDataNull("没有找到相应的商品");
        }

        return (end+3);
    }

}
