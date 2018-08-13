package com.example.hegua.androidwork.View;

import android.support.v7.widget.RecyclerView;

import com.example.hegua.androidwork.baseview.BaseView;
import com.example.hegua.androidwork.object.trade_object.Trade;

import java.util.ArrayList;

/**
 * Created by hegua on 2018/7/29.
 */

public interface MyGoodsView extends BaseView{
    RecyclerView getRecyclerView();
    void showGetDataFall(String state);
    void showDeleteSuccess(String state);
}
