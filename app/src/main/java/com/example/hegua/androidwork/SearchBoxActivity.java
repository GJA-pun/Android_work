package com.example.hegua.androidwork;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hegua.androidwork.View.SearchBoxView;
import com.example.hegua.androidwork.object.trade_object.Trade;
import com.example.hegua.androidwork.presenter.goodsdaopresenter.GoodsDaoPresenter;
import com.example.hegua.androidwork.presenter.goodsdaopresenter.GoodsDaoPresenterImpl;
import com.example.hegua.androidwork.searchbox.listviewadapter.ListViewApater;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hegua on 2018/7/20.
 */

public class SearchBoxActivity extends Activity implements SearchBoxView{

    @Bind(R.id.searchbox_return_button)
    ImageButton searchboxReturnButton;
    @Bind(R.id.searchbox)
    EditText searchbox;
    @Bind(R.id.searching)
    TextView searching;

    private PullToRefreshListView pullToRefreshListView;
    private ListView listView;
    private ArrayList<Trade> trades;
    private ListViewApater listViewApater;
    private GoodsDaoPresenter goodsDaoPresenter = new GoodsDaoPresenterImpl();
    private int end=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchbox);
        ButterKnife.bind(this);

        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.searchbox_pullrefresh_list);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new GetDataTask01().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                new GetDataTask02().execute();
            }
        });

        listView = pullToRefreshListView.getRefreshableView();


    }

    @OnClick({R.id.searchbox_return_button, R.id.searchbox, R.id.searching})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.searchbox_return_button:
                SearchBoxActivity.this.finish();
                break;
            case R.id.searchbox:
                break;
            case R.id.searching:
                end = end +3;
                goodsDaoPresenter.fuzzyquserygoods(searchbox.getText().toString(),this,end);
                break;
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void showFuzzyQuseryGoodsData(String data) {
        if (data!=null){
            Gson gson = new Gson();
            trades = gson.fromJson(data, new TypeToken<ArrayList<Trade>>(){}.getType());
        }

        listViewApater = new ListViewApater(SearchBoxActivity.this,trades,this);
        listView.setAdapter(listViewApater);
    }

    @Override
    public void showFuzzyQuseryGoodsDataNull(String state) {
        Toast.makeText(this, state, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetWorkError() {
        Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
    }


    private class GetDataTask01 extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] result) {
            goodsDaoPresenter.fuzzyquserygoods(searchbox.getText().toString(),SearchBoxActivity.this,end);
            pullToRefreshListView.onRefreshComplete();
            super.onPostExecute(result);
        }
    }

    private class GetDataTask02 extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] result) {
            end = end +3;
            goodsDaoPresenter.fuzzyquserygoods(searchbox.getText().toString(),SearchBoxActivity.this,end);
            pullToRefreshListView.onRefreshComplete();
            super.onPostExecute(result);
        }
    }

}
