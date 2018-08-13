package com.example.hegua.androidwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.hegua.androidwork.View.MyGoodsView;
import com.example.hegua.androidwork.presenter.mygoodspresenter.MyGoodsPresenter;
import com.example.hegua.androidwork.presenter.mygoodspresenter.MyGoodsPresenterImpl;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hegua on 2018/7/21.
 */

public class MyGoodsActivity extends Activity implements MyGoodsView {
    @Bind(R.id.mygoods_return_button)
    ImageButton mygoodsReturnButton;
    @Bind(R.id.mygoods_rv)
    RecyclerView mygoodsRv;
    @Bind(R.id.addgoods_button)
    ImageButton addgoodsButton;
    @Bind(R.id.mygoods_srl)
    SwipeRefreshLayout mygoodsSrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygoods);
        ButterKnife.bind(this);

        mygoodsSrl = (SwipeRefreshLayout) findViewById(R.id.mygoods_srl);
        mygoodsSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        MyGoodsPresenter myGoodsPresenter = new MyGoodsPresenterImpl(MyGoodsActivity.this, MyGoodsActivity.this);
                        myGoodsPresenter.getMyGoodsData();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mygoodsSrl.setRefreshing(false);
                            }
                        });

                    }
                }).start();

            }
        });

        MyGoodsPresenter myGoodsPresenter = new MyGoodsPresenterImpl(this, MyGoodsActivity.this);
        myGoodsPresenter.getMyGoodsData();

    }

    @OnClick({R.id.mygoods_return_button, R.id.addgoods_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mygoods_return_button:
                //Glide.get(MyGoodsActivity.this).clearDiskCache();
                //Glide.get(MyGoodsActivity.this).clearMemory();
                MyGoodsActivity.this.finish();
                break;
            case R.id.addgoods_button:
                MyGoodsActivity.this.startActivity(new Intent(MyGoodsActivity.this, AddGoodsActivity.class));
                break;
        }
    }

    @Override
    public void showNetWorkError() {
        Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mygoodsRv;
    }

    @Override
    public void showGetDataFall(String state) {
        Toast.makeText(this, state, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDeleteSuccess(String state) {
        Toast.makeText(this, state, Toast.LENGTH_SHORT).show();
    }

}
