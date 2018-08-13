package com.example.hegua.androidwork.searchbox.listviewadapter;

import android.content.Context;
import android.content.Intent;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hegua.androidwork.R;
import com.example.hegua.androidwork.ShowGoodsActivtiy;
import com.example.hegua.androidwork.View.SearchBoxView;
import com.example.hegua.androidwork.object.trade_object.Trade;

import java.util.ArrayList;

/**
 * Created by hegua on 2018/8/6.
 */

public class ListViewApater extends BaseAdapter{

    private Context mContext;
    private ArrayList<Trade> trades;
    private SearchBoxView searchBoxView;

    public ListViewApater(Context mContext, ArrayList<Trade> trades, SearchBoxView searchBoxView) {
        this.mContext = mContext;
        this.trades = trades;
        this.searchBoxView = searchBoxView;
    }

    @Override
    public int getCount() {
        return trades.size();
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
        View item = View.inflate(mContext, R.layout.trade,null);
        TextView tradeName = (TextView) item.findViewById(R.id.trade_name);
        TextView tradeSellingPrice = (TextView) item.findViewById(R.id.trade_sellingprice);
        TextView tradeMerchantFabulous = (TextView) item.findViewById(R.id.trade_merchant_fabulous);
        ImageView tradeImage = (ImageView) item.findViewById(R.id.trade_image);
        TextView tradeposition = (TextView) item.findViewById(R.id.trade_position);
        LinearLayout trade_ll = (LinearLayout) item.findViewById(R.id.trade_ll);

        tradeName.setText(trades.get(i).getName());
        tradeSellingPrice.setText(trades.get(i).getSelling_price());
        tradeMerchantFabulous.setText("店家:"+trades.get(i).getMerchant()+"" +
                "   赞:"+trades.get(i).getFabulous());
        tradeposition.setText(i+"");

        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f, mContext.getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f, mContext.getResources().getDisplayMetrics());

        Glide.with(mContext)
                .load(trades.get(i).getImageurl()+".jpg")
                .placeholder(R.color.lightgray) //占位图
                .error(R.drawable.getimage_fail)  //出错的占位图
                .override(width, height) //图片显示的分辨率 ，像素值 可以转化为DP再设置
                //.animate(R.anim.glide_anim)//动画
                .centerCrop()
                .fitCenter()
                .into(tradeImage);

        trade_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ShowGoodsActivtiy.class);
                intent.putExtra("trade_data",trades.get(i));
                intent.putExtra("where","goodslist");
                mContext.startActivity(intent);
            }
        });

        return item;
    }
}
