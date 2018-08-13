package com.example.hegua.androidwork.user.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hegua.androidwork.LoginActivity;
import com.example.hegua.androidwork.MyGoodsActivity;
import com.example.hegua.androidwork.R;
import com.example.hegua.androidwork.SetUpUserActivity;
import com.example.hegua.androidwork.basefragment.BaseFragment;
import com.example.hegua.androidwork.object.user_object.NowUser;
import com.example.hegua.androidwork.object.user_object.User;

import butterknife.ButterKnife;

/**
 * Created by hegua on 2018/7/16.
 */

public class UserFragment extends BaseFragment {

    private LinearLayout mygoods_ll;
    private LinearLayout setup_ll;
    private LinearLayout exitlogon_ll;
    private TextView username;
    User nowuser = NowUser.getUser();

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_user, null);
        mygoods_ll = (LinearLayout) view.findViewById(R.id.fragmentuser_mygoods_ll);
        setup_ll = (LinearLayout) view.findViewById(R.id.fragmentuser_setup_ll);
        exitlogon_ll = (LinearLayout) view.findViewById(R.id.fragmentuser_exitlogon_ll);
        username = (TextView) view.findViewById(R.id.fragmentuser_username);
        if (nowuser == null) {
            System.out.println("nowuser----------------------------->null");
            exitlogon_ll.setVisibility(View.GONE);
        }else {
            System.out.println("nowuser----------------------------->!null");
            exitlogon_ll.setVisibility(View.VISIBLE);
        }
        if (nowuser != null) {
            username.setText(nowuser.getUsername());
        }else{
            username.setText("登录");
        }
        initListenet();
        return view;
    }

    private void initListenet() {

        mygoods_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nowuser == null) {
                    mContext.startActivity(new Intent(mContext, LoginActivity.class));
                } else {
                    //Toast.makeText(mContext, "我的商品", Toast.LENGTH_SHORT).show();
                    mContext.startActivity(new Intent(mContext, MyGoodsActivity.class));
                }
            }
        });

        setup_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nowuser == null) {
                    mContext.startActivity(new Intent(mContext, LoginActivity.class));
                } else {
                    mContext.startActivity(new Intent(mContext, SetUpUserActivity.class));
                }
            }
        });

        exitlogon_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NowUser.setUser(null);
                Toast.makeText(mContext, "以退出登录", Toast.LENGTH_SHORT).show();
                mContext.startActivity(new Intent(mContext, LoginActivity.class));
            }
        });

        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nowuser == null) {
                    mContext.startActivity(new Intent(mContext, LoginActivity.class));
                }
            }
        });
    }

    @Override
    public void onResume() {
        nowuser = NowUser.getUser();
        System.out.println("onResume------------------------>");
        initView();
        super.onResume();
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            nowuser = NowUser.getUser();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
