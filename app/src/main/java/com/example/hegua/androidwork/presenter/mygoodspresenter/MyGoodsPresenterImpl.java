package com.example.hegua.androidwork.presenter.mygoodspresenter;

import android.content.Context;

import com.example.hegua.androidwork.View.MyGoodsView;
import com.example.hegua.androidwork.object.user_object.NowUser;
import com.example.hegua.androidwork.presenter.asynctask.DeleteGoodsAsyncTask;
import com.example.hegua.androidwork.presenter.asynctask.MyGoodsAsyncTask;
import com.example.hegua.androidwork.utils.Constant;

/**
 * Created by hegua on 2018/7/29.
 */

public class MyGoodsPresenterImpl implements MyGoodsPresenter{

    private MyGoodsView myGoodsView;
    private Context mContext;
    //private int i = 0;

    public MyGoodsPresenterImpl(MyGoodsView myGoodsView ,Context mContext) {
        this.myGoodsView = myGoodsView;
        this.mContext = mContext;
    }

    @Override
    public void getMyGoodsData() {
        if (NowUser.getUser()!=null) {
            String username = NowUser.getUser().getUsername();
            String url = Constant.URL + Constant.FINDALLUSERTRADE + "?username=" + username;
            //System.out.println("url-------------->"+ url);

            MyGoodsAsyncTask myGoodsAsyncTask = new MyGoodsAsyncTask(myGoodsView,mContext);
            myGoodsAsyncTask.execute(url);
        }else{
            myGoodsView.showGetDataFall("未登录");
        }
    }

    @Override
    public void deleteTrade(int id) {
        String url = Constant.URL+Constant.DELETETRADE+"?id="+id;
        //System.out.println("deleteTradeURL------------->"+url);
        DeleteGoodsAsyncTask deleteGoodsAsyncTask = new DeleteGoodsAsyncTask(url,myGoodsView);
        deleteGoodsAsyncTask.execute();
    }
}
