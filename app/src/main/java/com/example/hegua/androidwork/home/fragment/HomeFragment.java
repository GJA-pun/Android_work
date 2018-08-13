package com.example.hegua.androidwork.home.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hegua.androidwork.R;
import com.example.hegua.androidwork.SearchBoxActivity;
import com.example.hegua.androidwork.View.HomeFragmentView;
import com.example.hegua.androidwork.basefragment.BaseFragment;
import com.example.hegua.androidwork.home.adapter.HomeRecyclerViewAdapter;
import com.example.hegua.androidwork.object.trade_object.Trade;
import com.example.hegua.androidwork.object.user_object.NowUser;
import com.example.hegua.androidwork.presenter.goodsdaopresenter.GoodsDaoPresenter;
import com.example.hegua.androidwork.presenter.goodsdaopresenter.GoodsDaoPresenterImpl;
import com.example.hegua.androidwork.presenter.setuserimagepresenter.UserDaoPresenter;
import com.example.hegua.androidwork.presenter.setuserimagepresenter.UserDaoPresenterImpl;
import com.example.hegua.androidwork.utils.Constant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by hegua on 2018/7/16.
 */

public class HomeFragment extends BaseFragment implements HomeFragmentView{


    @Bind(R.id.fragmenthome_toolbar_user_img)
    ImageView fragmenthomeToolbarUserImg;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.fragmenthome_toolbar_searchbox)
    TextView fragmenthomeToolbarSearchbox;
    @Bind(R.id.fragmenthome_toolbar_menu)
    ImageButton fragmenthomeToolbarMenu;
    @Bind(R.id.fragmenthome_toolbar)
    Toolbar fragmenthomeToolbar;
    @Bind(R.id.fragmenthome_rv)
    RecyclerView fragmenthomeRv;
    @Bind(R.id.fragmenthome_ib)
    ImageButton fragmenthomeIb;
    @Bind(R.id.fragmenthome_srl)
    SwipeRefreshLayout fragmenthomeSrl;

    private HomeRecyclerViewAdapter rvAdapter;
    private GoodsDaoPresenter goodsDaoPresenter = new GoodsDaoPresenterImpl();
    private UserDaoPresenter userDaoPresenter = new UserDaoPresenterImpl(mContext);

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);

        fragmenthomeSrl = (SwipeRefreshLayout) view.findViewById(R.id.fragmenthome_srl);
        fragmenthomeSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new GetUpUIAsyncTask().execute();
                    }
                }).start();
            }
        });

        fragmenthomeRv = (RecyclerView) view.findViewById(R.id.fragmenthome_rv);
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        goodsDaoPresenter.findAllGoods(this);
        if(NowUser.getUser()!=null) {
            userDaoPresenter.getUserIamgeurl(NowUser.getUser().getUsername(), this);
        }
        Glide.with(mContext)
                .load(R.drawable.user_default_image)
                .bitmapTransform(new CropCircleTransformation(mContext))
                .into(fragmenthomeToolbarUserImg);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.fragmenthome_toolbar_user_img, R.id.fragmenthome_toolbar_searchbox, R.id.fragmenthome_toolbar_menu, R.id.fragmenthome_ib})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragmenthome_toolbar_user_img:
                Toast.makeText(mContext, "用户头像", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fragmenthome_toolbar_searchbox:
                Toast.makeText(mContext, "搜索框", Toast.LENGTH_SHORT).show();
                mContext.startActivity(new Intent(mContext, SearchBoxActivity.class));
                break;
            case R.id.fragmenthome_toolbar_menu:
                Toast.makeText(mContext, "菜单", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fragmenthome_ib:
                fragmenthomeRv.scrollToPosition(0);
                break;
        }
    }

    @Override
    public void showNetWorkError() {
        Toast.makeText(mContext, "网络异常", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setTrades(String data) {
        ArrayList<Trade> alTrades = new ArrayList<>();
        Trade[] trades ;
        Gson gson = new Gson();

        if (data != null&&data.toString().equals("[]")==false) {
            trades = gson.fromJson(data, new TypeToken<Trade[]>(){}.getType());
        } else {
            trades = null;
        }
        for (int i =0;i<trades.length;i++){
            alTrades.add(trades[i]);
        }
        rvAdapter = new HomeRecyclerViewAdapter(mContext,alTrades);
        fragmenthomeRv.setAdapter(rvAdapter);
        GridLayoutManager manager = new GridLayoutManager(mContext, 1);
        fragmenthomeRv.setLayoutManager(manager);
    }

    @Override
    public void showUserNoExis(String state) {
        Toast.makeText(mContext, state, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserNoImageurl() {
    }

    @Override
    public void showUserImageurl() {
        String imageurl = Constant.URL+Constant.LOADIMAGE + "I_"+NowUser.getUser().getUsername()+".jpg";
        Glide.with(mContext)
                .load(imageurl)
                .bitmapTransform(new CropCircleTransformation(mContext))
                .into(fragmenthomeToolbarUserImg);
    }

    public void initData() {super.initData();}

    class GetUpUIAsyncTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            goodsDaoPresenter.findAllGoods(HomeFragment.this);
            if (NowUser.getUser()!=null) {
                userDaoPresenter.getUserIamgeurl(NowUser.getUser().getUsername(), HomeFragment.this);
            }else{
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
            fragmenthomeSrl.setRefreshing(false);
            super.onPostExecute(aVoid);
        }
    }

}
