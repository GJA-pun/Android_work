package com.example.hegua.androidwork.shoppingcart.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hegua.androidwork.R;
import com.example.hegua.androidwork.ShowGoodsActivtiy;
import com.example.hegua.androidwork.View.MyShoppingCartView;
import com.example.hegua.androidwork.basefragment.BaseFragment;
import com.example.hegua.androidwork.object.shoppingcart_object.ShoppingCart;
import com.example.hegua.androidwork.object.trade_object.Trade;
import com.example.hegua.androidwork.object.user_object.NowUser;
import com.example.hegua.androidwork.presenter.shoppingcartpresenter.ShoppingCartPresenter;
import com.example.hegua.androidwork.presenter.shoppingcartpresenter.ShoppingCartPresenterImpl;
import com.example.hegua.androidwork.shoppingcart.adapter.ShoppingCartListViewAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

/**
 * Created by hegua on 2018/7/16.
 */

public class ShoppingCartFragment extends BaseFragment implements MyShoppingCartView{

    private PullToRefreshListView pullToRefreshListView;
    private ListView listView;
    private ArrayList<ShoppingCart> shoppingCarts;
    private ShoppingCartPresenter shoppingCartPresenter = new ShoppingCartPresenterImpl();
    private ShoppingCartListViewAdapter adapter;
    private View view ;

    @Override
    public View initView() {
        view = View.inflate(mContext, R.layout.fragment_shoppingcart,null);
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.myshoppingcart_pullrefresh_list);
        listView = pullToRefreshListView.getRefreshableView();
        return view;
    }

    public void initData(){
        super.initData();
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new GetUpUIAsyncTask().execute();
                    }
                }).start();
            }
        });

        pullToRefreshListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                Toast.makeText(mContext, "ㄟ( ▔, ▔ )ㄏ 没了~~", Toast.LENGTH_SHORT).show();
            }
        });
        if(NowUser.getUser()!=null) {
            shoppingCartPresenter.getShoppingCartData(NowUser.getUser().getUsername(), this);
        }else {
            Toast.makeText(mContext, "未登录", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getShoppingCartData(String data) {
        Gson gson = new Gson();
        shoppingCarts = gson.fromJson(data, new TypeToken<ArrayList<ShoppingCart>>(){}.getType());
        adapter = new ShoppingCartListViewAdapter(mContext,shoppingCarts,this);
        listView.setAdapter(adapter);
    }

    @Override
    public void showShoppingCartGetTradeSuccess(String data) {
        Gson gson = new Gson();
        Trade trade = gson.fromJson(data, new TypeToken<Trade>(){}.getType());
        Intent intent = new Intent(mContext, ShowGoodsActivtiy.class);
        intent.putExtra("trade_data",trade);
        intent.putExtra("where","ShoppingCart");
        mContext.startActivity(intent);
    }

    @Override
    public void showShoppingCartGetTradeNoSuccess(String state) {
        Toast.makeText(mContext, state, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showShoppingCartGetNull() {
        Toast.makeText(mContext, "没有订单", Toast.LENGTH_SHORT).show();
        pullToRefreshListView.onRefreshComplete();
    }

    @Override
    public void showDeleteShoppingCartSuccess(String state) {
        adapter.notifyDataSetChanged();
        Toast.makeText(mContext, state, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDeleteShoppingCartNoSuceess(String state) {
        Toast.makeText(mContext, state, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void buyShoppingCartTrade(ShoppingCart shoppingCart) {
        shoppingCartPresenter.buyShoppingCartGoods(shoppingCart,this);
    }

    @Override
    public void buyShoppingCartTradeSuccess(final String name_merchant) {
        Snackbar.make(view, "谢谢光临", Snackbar.LENGTH_INDEFINITE)
                .setAction("点赞", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shoppingCartPresenter.fabulousShoppingCartGoods(name_merchant,1,ShoppingCartFragment.this);
                    }
                }).show();
    }

    @Override
    public void buyShoppingCartTradeNoSuccess(String state) {
        Toast.makeText(mContext, state, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fabulousShoppingCartTradeSuccess() {
        Toast.makeText(mContext, "谢谢", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fabulousShoppingCartTradeNoSuccess(String state) {
        Toast.makeText(mContext, state, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetWorkError() {
        Toast.makeText(mContext, "网络异常", Toast.LENGTH_SHORT).show();
        pullToRefreshListView.onRefreshComplete();
    }

    class GetUpUIAsyncTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            if (NowUser.getUser()!=null) {
                shoppingCartPresenter.getShoppingCartData(NowUser.getUser().getUsername(), ShoppingCartFragment.this);
            }else {
                publishProgress();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Toast.makeText(mContext, "未登录", Toast.LENGTH_SHORT).show();
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            pullToRefreshListView.onRefreshComplete();
            super.onPostExecute(aVoid);
        }
    }

}
