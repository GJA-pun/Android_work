package com.example.hegua.androidwork;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hegua.androidwork.View.TypeView;
import com.example.hegua.androidwork.object.trade_object.Trade;
import com.example.hegua.androidwork.type.adapter.TypeViewPaperAdapter;
import com.example.hegua.androidwork.type.fragment.TypeFragment;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hegua on 2018/7/21.
 */

public class TypeActivity extends AppCompatActivity implements TypeView {
    @Bind(R.id.type_TabLayout)
    TabLayout typeTabLayout;
    @Bind(R.id.type_ViewPager)
    ViewPager typeViewPager;
    @Bind(R.id.fragmenthome_type_toolbar_searchbox)
    TextView fragmenthomeTypeToolbarSearchbox;
    @Bind(R.id.fragmenthome_type_toolbar_menu)
    ImageButton fragmenthomeTypeToolbarMenu;
    @Bind(R.id.fragmenthome_type_toolbar)
    Toolbar fragmenthomeTypeToolbar;

    private ArrayList<TypeFragment> fragments;
    private TypeViewPaperAdapter typeViewPaperAdapter;
    private int postion = 0;
    private String text;
    private ArrayList<Trade> trades = null;
    private ArrayList<Trade> oneTrades = new ArrayList<>();
    private ArrayList<Trade> twoTrades = new ArrayList<>();
    private ArrayList<Trade> threeTrades = new ArrayList<>();
    private ArrayList<Trade> fourTrades = new ArrayList<>();
    private ArrayList<Trade> fiveTrades = new ArrayList<>();
    private ArrayList<Trade> sixTrades = new ArrayList<>();
    private ArrayList<Trade> sevenTrades = new ArrayList<>();
    private ArrayList<Trade> eightTrades = new ArrayList<>();
    private String ONE = "生鲜果蔬";
    private String TWO = "网上超市";
    private String THREE = "浪漫鲜花";
    private String FOUR = "香茶茶点";
    private String FIVE = "甜品饮品";
    private String SIX = "美味三餐";
    private String SEVEN = "甜蜜蛋糕";
    private String EIGHT = "炸鸡零食";
    private PopupWindow popupWindow;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        text = bundle.getString("WhatType");
        if (getIntent().getSerializableExtra("trade_data") != null) {
            trades = (ArrayList<Trade>) getIntent().getSerializableExtra("trade_data");
        }

        initData();

        typeViewPaperAdapter = new TypeViewPaperAdapter(getSupportFragmentManager(), fragments);
        typeViewPager.setAdapter(typeViewPaperAdapter);

        typeTabLayout.setupWithViewPager(typeViewPager);
        typeTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        typeTabLayout.getTabAt(postion).select();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @OnClick({R.id.fragmenthome_type_toolbar_searchbox, R.id.fragmenthome_type_toolbar_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragmenthome_type_toolbar_searchbox:
                TypeActivity.this.startActivity(new Intent(TypeActivity.this, SearchBoxActivity.class));
                break;
            case R.id.fragmenthome_type_toolbar_menu:
                ListView listView = new ListView(TypeActivity.this);
                ListViewAdapter listViewAdapter = new ListViewAdapter();
                listView.setAdapter(listViewAdapter);

                popupWindow = new PopupWindow(listView);
                popupWindow.setWidth(250);
                popupWindow.setHeight(500);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);

                popupWindow.showAsDropDown(fragmenthomeTypeToolbarMenu);
                break;
        }
    }

    @Override
    public void initData() {

        for (int i = 0; i < trades.size(); i++) {
            if (trades.get(i).getType().equals(ONE)) {
                oneTrades.add(trades.get(i));
                trades.remove(i);
                i--;
            }
        }
        for (int i = 0; i < trades.size(); i++) {
            if (trades.get(i).getType().equals(TWO)) {
                twoTrades.add(trades.get(i));
                trades.remove(i);
                i--;
            }
        }
        for (int i = 0; i < trades.size(); i++) {
            if (trades.get(i).getType().equals(THREE)) {
                threeTrades.add(trades.get(i));
                trades.remove(i);
                i--;
            }
        }
        for (int i = 0; i < trades.size(); i++) {
            if (trades.get(i).getType().equals(FOUR)) {
                fourTrades.add(trades.get(i));
                trades.remove(i);
                i--;
            }
        }
        for (int i = 0; i < trades.size(); i++) {
            if (trades.get(i).getType().equals(FIVE)) {
                fiveTrades.add(trades.get(i));
                trades.remove(i);
                i--;
            }
        }
        for (int i = 0; i < trades.size(); i++) {
            if (trades.get(i).getType().equals(SIX)) {
                sixTrades.add(trades.get(i));
                trades.remove(i);
                i--;
            }
        }
        for (int i = 0; i < trades.size(); i++) {
            if (trades.get(i).getType().equals(SEVEN)) {
                sevenTrades.add(trades.get(i));
                trades.remove(i);
                i--;
            }
        }
        for (int i = 0; i < trades.size(); i++) {
            if (trades.get(i).getType().equals(EIGHT)) {
                eightTrades.add(trades.get(i));
                trades.remove(i);
                i--;
            }
        }


        fragments = new ArrayList<TypeFragment>();
        fragments.add(new TypeFragment(ONE, oneTrades, TypeActivity.this));
        fragments.add(new TypeFragment(TWO, twoTrades, TypeActivity.this));
        fragments.add(new TypeFragment(THREE, threeTrades, TypeActivity.this));
        fragments.add(new TypeFragment(FOUR, fourTrades, TypeActivity.this));
        fragments.add(new TypeFragment(FIVE, fiveTrades, TypeActivity.this));
        fragments.add(new TypeFragment(SIX, sixTrades, TypeActivity.this));
        fragments.add(new TypeFragment(SEVEN, sevenTrades, TypeActivity.this));
        fragments.add(new TypeFragment(EIGHT, eightTrades, TypeActivity.this));

        switch (text) {
            case "生鲜果蔬":
                postion = 0;
                break;
            case "网上超市":
                postion = 1;
                break;
            case "浪漫鲜花":
                postion = 2;
                break;
            case "香茶茶点":
                postion = 3;
                break;
            case "甜品饮品":
                postion = 4;
                break;
            case "美味三餐":
                postion = 5;
                break;
            case "甜蜜蛋糕":
                postion = 6;
                break;
            case "炸鸡零食":
                postion = 7;
                break;
        }
    }

    @Override
    public void showNetWorkError() {
        Toast.makeText(this, "网路异常", Toast.LENGTH_SHORT).show();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Type Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    private class ListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            View view1 = View.inflate(getApplicationContext(), R.layout.typeactivity_popupwindow, null);
            final TextView lightyellow = (TextView) view1.findViewById(R.id.type_popupwindow_lightyellow);
            TextView lightgreen = (TextView) view1.findViewById(R.id.type_popupwindow_lightgreen);
            TextView ghostwhite = (TextView) view1.findViewById(R.id.type_popupwindow_ghostwhite);

            lightyellow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(TypeActivity.this, "lightyellow", Toast.LENGTH_SHORT).show();
                    typeViewPager.setBackgroundColor(Color.parseColor("#ffffe0"));
                    popupWindow.dismiss();
                }
            });

            lightgreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(TypeActivity.this, "lightgreen", Toast.LENGTH_SHORT).show();
                    typeViewPager.setBackgroundColor(Color.parseColor("#90ee90"));
                    popupWindow.dismiss();
                }
            });

            ghostwhite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(TypeActivity.this, "ghostwhite", Toast.LENGTH_SHORT).show();
                    typeViewPager.setBackgroundColor(Color.parseColor("#f8f8ff"));
                    popupWindow.dismiss();
                }
            });

            return view1;
        }
    }

}
