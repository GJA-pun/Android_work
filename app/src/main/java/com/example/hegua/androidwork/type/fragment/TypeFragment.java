package com.example.hegua.androidwork.type.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hegua.androidwork.R;
import com.example.hegua.androidwork.basefragment.BaseFragment;
import com.example.hegua.androidwork.object.trade_object.Trade;
import com.example.hegua.androidwork.type.adapter.TFRecyclerViewApader;

import java.util.ArrayList;

/**
 * Created by hegua on 2018/8/2.
 */

public class TypeFragment extends BaseFragment{

    private String title;
    private RecyclerView recyclerView;
    private Context mContext;
    private ArrayList<Trade> trades;

    public TypeFragment(String title, ArrayList<Trade> trades, Context mContext) {
        this.title = title;
        this.mContext = mContext;
        this.trades = trades;
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.typeframge_recyclerview,null);
        recyclerView = (RecyclerView) view.findViewById(R.id.typefragment_rv);

        return view;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setAdapter(new TFRecyclerViewApader(trades,mContext));
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        super.initData();
    }

    public String getTitle() {
        return this.title;
    }

    public ArrayList<Trade> getContent(){return this.trades;}
}
