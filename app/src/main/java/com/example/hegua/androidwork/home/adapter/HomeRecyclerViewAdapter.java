package com.example.hegua.androidwork.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hegua.androidwork.R;
import com.example.hegua.androidwork.ShowGoodsActivtiy;
import com.example.hegua.androidwork.TypeActivity;
import com.example.hegua.androidwork.object.trade_object.Trade;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by hegua on 2018/7/21.
 */

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final int TYPE = 0;
    private final int SUBSTITUTES = 1;
    private final int GOODSLIST = 2;

    //private LayoutInflater mLayoutInflater;
    private int currentType = TYPE;
    private Context mContext;
    private ArrayList<Trade> trades;
    private Trade[] tradeData;

    public HomeRecyclerViewAdapter(Context mContext,ArrayList<Trade> trades) {
        this.mContext = mContext;
        this.trades = trades;
        tradeData = trades.toArray(new Trade[trades.size()]);
        //mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==TYPE){
            return new TypeViewHolder(View.inflate(mContext,R.layout.fhome_recycler_type,null),mContext);
        }else if (viewType==SUBSTITUTES){
            return new SubstitutesViewHolder(View.inflate(mContext,R.layout.fhome_recycler_substitutes,null),mContext);
        }else if (viewType==GOODSLIST){
            return new GoodslistViewHolder(View.inflate(mContext,R.layout.fhome_recycler_goodslist,null),mContext);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        /*if (getItemViewType(position)==TYPE){
            Toast.makeText(mContext, "TYPE", Toast.LENGTH_SHORT).show();
        }else if (getItemViewType(position)==SUBSTITUTES){
            Toast.makeText(mContext, "SUBSTITUTES", Toast.LENGTH_SHORT).show();
        }else if (getItemViewType(position)==GOODSLIST){
            Toast.makeText(mContext, "TWO", Toast.LENGTH_SHORT).show();
        }*/
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case TYPE:
                currentType = TYPE;
                break;
            case SUBSTITUTES:
                currentType = SUBSTITUTES;
                break;
            case GOODSLIST:
                currentType = GOODSLIST;
                break;
        }
        return currentType;
    }

    class TypeViewHolder extends RecyclerView.ViewHolder{
        private Context context;
        private ImageButton type01;
        private ImageButton type02;
        private ImageButton type03;
        private ImageButton type04;
        private ImageButton type05;
        private ImageButton type06;
        private ImageButton type07;
        private ImageButton type08;

        public TypeViewHolder(View itemView,Context context) {
            super(itemView);
            this.context = context;
            this.type01 = (ImageButton) itemView.findViewById(R.id.fhome_typeImageButton01);
            this.type02 = (ImageButton) itemView.findViewById(R.id.fhome_typeImageButton02);
            this.type03 = (ImageButton) itemView.findViewById(R.id.fhome_typeImageButton03);
            this.type04 = (ImageButton) itemView.findViewById(R.id.fhome_typeImageButton04);
            this.type05 = (ImageButton) itemView.findViewById(R.id.fhome_typeImageButton05);
            this.type06 = (ImageButton) itemView.findViewById(R.id.fhome_typeImageButton06);
            this.type07 = (ImageButton) itemView.findViewById(R.id.fhome_typeImageButton07);
            this.type08 = (ImageButton) itemView.findViewById(R.id.fhome_typeImageButton08);

            type01.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, TypeActivity.class);
                    intent.putExtra("WhatType","生鲜果蔬");
                    intent.putExtra("trade_data",trades);
                    mContext.startActivity(intent);
                }
            });

            type02.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, TypeActivity.class);
                    intent.putExtra("WhatType","网上超市");
                    intent.putExtra("trade_data",trades);
                    mContext.startActivity(intent);
                }
            });

            type03.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, TypeActivity.class);
                    intent.putExtra("WhatType","浪漫鲜花");
                    intent.putExtra("trade_data",trades);
                    mContext.startActivity(intent);
                }
            });

            type04.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, TypeActivity.class);
                    intent.putExtra("WhatType","香茶茶点");
                    intent.putExtra("trade_data",trades);
                    mContext.startActivity(intent);
                }
            });

            type05.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, TypeActivity.class);
                    intent.putExtra("WhatType","甜品饮品");
                    intent.putExtra("trade_data",trades);
                    mContext.startActivity(intent);
                }
            });

            type06.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, TypeActivity.class);
                    intent.putExtra("WhatType","美味三餐");
                    intent.putExtra("trade_data",trades);
                    mContext.startActivity(intent);
                }
            });

            type07.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, TypeActivity.class);
                    intent.putExtra("WhatType","甜蜜蛋糕");
                    intent.putExtra("trade_data",trades);
                    mContext.startActivity(intent);
                }
            });

            type08.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, TypeActivity.class);
                    intent.putExtra("WhatType","炸鸡零食");
                    intent.putExtra("trade_data",trades);
                    mContext.startActivity(intent);
                }
            });

        }
    }

    class SubstitutesViewHolder extends RecyclerView.ViewHolder{
        private Context context;
        private TextView one;
        private TextView two;
        private TextView three;
        private ImageView oneImage;
        private ImageView twoImage;
        private ImageView threeImage;
        private TextView onePostion;
        private TextView twoPostion;
        private TextView threePostion;

        public SubstitutesViewHolder(View itemView,Context context) {
            super(itemView);
            this.context = context;
            one = (TextView) itemView.findViewById(R.id.substitutes_one_tv);
            two = (TextView) itemView.findViewById(R.id.substitutes_two_tv);
            three = (TextView) itemView.findViewById(R.id.substitutes_three_tv);
            oneImage = (ImageView) itemView.findViewById(R.id.substitutes_one);
            twoImage = (ImageView) itemView.findViewById(R.id.substitutes_two);
            threeImage = (ImageView) itemView.findViewById(R.id.substitutes_three);
            onePostion = (TextView) itemView.findViewById(R.id.substitutes_one_postion);
            twoPostion = (TextView) itemView.findViewById(R.id.substitutes_two_postion);
            threePostion = (TextView) itemView.findViewById(R.id.substitutes_three_postion);

            int a ,b,c;
            Random r = new Random();
            do {
                a = r.nextInt(trades.size());
                b = r.nextInt(trades.size());
                c = r.nextInt(trades.size());
            }while (a==b||b==c||a==c||b==c);

            onePostion.setText(a+"");
            twoPostion.setText(b+"");
            threePostion.setText(c+"");

            one.setText(trades.get(a).getName());
            two.setText(trades.get(b).getName());
            three.setText(trades.get(c).getName());

            int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100f, mContext.getResources().getDisplayMetrics());
            int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100f, mContext.getResources().getDisplayMetrics());

            Glide.with(mContext)
                    .load(trades.get(a).getImageurl()+".jpg")
                    .placeholder(R.color.lightgray) //占位图
                    .error(R.drawable.getimage_fail)  //出错的占位图
                    .override(width, height) //图片显示的分辨率 ，像素值 可以转化为DP再设置
                    //.animate(R.anim.glide_anim)//动画
                    .centerCrop()
                    .fitCenter()
                    .into(oneImage);

            Glide.with(mContext)
                    .load(trades.get(b).getImageurl()+".jpg")
                    .placeholder(R.color.lightgray) //占位图
                    .error(R.drawable.getimage_fail)  //出错的占位图
                    .override(width, height) //图片显示的分辨率 ，像素值 可以转化为DP再设置
                    //.animate(R.anim.glide_anim)//动画
                    .centerCrop()
                    .fitCenter()
                    .into(twoImage);

            Glide.with(mContext)
                    .load(trades.get(c).getImageurl()+".jpg")
                    .placeholder(R.color.lightgray) //占位图
                    .error(R.drawable.getimage_fail)  //出错的占位图
                    .override(width, height) //图片显示的分辨率 ，像素值 可以转化为DP再设置
                    //.animate(R.anim.glide_anim)//动画
                    .centerCrop()
                    .fitCenter()
                    .into(threeImage);


            oneImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int postion = Integer.parseInt(onePostion.getText().toString());
                    Intent intent = new Intent(mContext, ShowGoodsActivtiy.class);
                    intent.putExtra("trade_data",trades.get(postion));
                    intent.putExtra("where","Substitutes");
                    mContext.startActivity(intent);
                }
            });

            twoImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int postion = Integer.parseInt(twoPostion.getText().toString());
                    Intent intent = new Intent(mContext, ShowGoodsActivtiy.class);
                    intent.putExtra("trade_data",trades.get(postion));
                    intent.putExtra("where","Substitutes");
                    mContext.startActivity(intent);
                }
            });

            threeImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int postion = Integer.parseInt(threePostion.getText().toString());
                    Intent intent = new Intent(mContext, ShowGoodsActivtiy.class);
                    intent.putExtra("trade_data",trades.get(postion));
                    intent.putExtra("where","Substitutes");
                    mContext.startActivity(intent);
                }
            });


        }
    }

    class GoodslistViewHolder extends RecyclerView.ViewHolder{
        private Context context;
        private RecyclerView recyclerView;
        private RadioGroup radioGroup;
        private Trade[] goodsListTrades = new Trade[tradeData.length];
        private GoodsListAdapter goodsListAdapter;
        private Trade temp;

        public GoodslistViewHolder(View itemView,Context context) {
            super(itemView);
            this.context = context;
            this.recyclerView = (RecyclerView) itemView.findViewById(R.id.goodslist_rv);
            this.radioGroup = (RadioGroup) itemView.findViewById(R.id.goodslist_rg);
            radioGroup.check(R.id.substitutes_time);

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    switch (i){
                        case R.id.substitutes_time:
                            sortTrade(0);
                            goodsListAdapter.notifyDataSetChanged();
                            break;
                        case R.id.substitutes_mostpraise://赞
                            sortTrade(1);
                            goodsListAdapter.notifyDataSetChanged();
                            break;
                        case R.id.substitutes_mostsales://销量
                            sortTrade(2);
                            goodsListAdapter.notifyDataSetChanged();
                            break;
                        default:
                            sortTrade(0);
                            goodsListAdapter.notifyDataSetChanged();
                            break;
                    }
                }
            });

            sortTrade(0);
            goodsListAdapter = new GoodsListAdapter(mContext,goodsListTrades);
            recyclerView.setAdapter(goodsListAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        }

        public void sortTrade(int i){
            int P,x;
            boolean f ;
            Trade temp;

            if (i == 1){
                for (P=goodsListTrades.length-1;P>=0;P--){
                    f=true;
                    for (x=0;x<P;x++){
                        if (goodsListTrades[x].getFabulous()<goodsListTrades[x+1].getFabulous()){
                            temp = goodsListTrades[x];
                            goodsListTrades[x] = goodsListTrades[x+1];
                            goodsListTrades[x+1] = temp;
                            f=false;
                        }
                    }
                    if (f)break;
                }
            }else if (i==2){
                for (P=goodsListTrades.length-1;P>=0;P--){
                    f=true;
                    for (x=0;x<P;x++){
                        if (goodsListTrades[x].getVolume()<goodsListTrades[x+1].getVolume()){
                            temp = goodsListTrades[x];
                            goodsListTrades[x] = goodsListTrades[x+1];
                            goodsListTrades[x+1] = temp;
                            f=false;
                        }
                    }
                    if (f)break;
                }
            }else if (i==0){
                for (int y=0;y<tradeData.length;y++){
                    temp = new Trade(tradeData[y].getId(),tradeData[y].getName(),tradeData[y].getContent(),
                            tradeData[y].getMerchant(),tradeData[y].getUsername(),tradeData[y].getSelling_price(),
                            tradeData[y].getDistribution_price(),tradeData[y].getVolume(),tradeData[y].getFabulous(),
                            tradeData[y].getType(),tradeData[y].getAddtime(),tradeData[y].getImageurl());
                    goodsListTrades[y] = temp;
                }
            }
        }
    }

}
