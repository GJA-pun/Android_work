package com.example.hegua.androidwork.type.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hegua.androidwork.R;
import com.example.hegua.androidwork.ShowGoodsActivtiy;
import com.example.hegua.androidwork.object.trade_object.Trade;

import java.util.ArrayList;

/**
 * Created by hegua on 2018/8/2.
 */

public class TFRecyclerViewApader extends RecyclerView.Adapter<TFRecyclerViewApader.ViewHolder>{

    private Context mContext;
    private ArrayList<Trade> trades;

    public TFRecyclerViewApader(ArrayList<Trade> trades, Context mContext) {
        this.trades = trades;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.trade,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (trades!=null) {
                holder.tradeName.setText(trades.get(position).getName());
                holder.tradeSellingPrice.setText("￥"+trades.get(position).getSelling_price());
                holder.tradeMerchantFabulous.setText("店家:"+trades.get(position).getMerchant()+"" + "   赞:"+trades.get(position).getFabulous());
                holder.tradeId.setText(trades.get(position).getId()+"");
                holder.tradePosition.setText(position+"");

                int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f, mContext.getResources().getDisplayMetrics());
                int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f, mContext.getResources().getDisplayMetrics());

                Glide.with(mContext)
                        .load(trades.get(position).getImageurl()+".jpg")
                        .placeholder(R.color.lightgray) //占位图
                        .error(R.drawable.getimage_fail)  //出错的占位图
                        .override(width, height) //图片显示的分辨率 ，像素值 可以转化为DP再设置
                        //.animate(R.anim.glide_anim)//动画
                        .centerCrop()
                        .fitCenter()
                        .into(holder.tradeImage);
        }
    }

    @Override
    public int getItemCount() {
        return trades.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tradeName;
        private TextView tradeSellingPrice;
        private TextView tradeMerchantFabulous;
        private ImageView tradeImage;
        private TextView tradeId;
        private TextView tradePosition;
        private LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            tradeName = (TextView) itemView.findViewById(R.id.trade_name);
            tradeSellingPrice = (TextView) itemView.findViewById(R.id.trade_sellingprice);
            tradeMerchantFabulous = (TextView) itemView.findViewById(R.id.trade_merchant_fabulous);
            tradeId = (TextView) itemView.findViewById(R.id.trade_id);
            tradeImage = (ImageView) itemView.findViewById(R.id.trade_image);
            tradePosition = (TextView) itemView.findViewById(R.id.trade_position);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.trade_ll);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String i = tradePosition.getText().toString();
                    int position = Integer.parseInt(i);
                    Intent intent = new Intent(mContext, ShowGoodsActivtiy.class);
                    intent.putExtra("trade_data",trades.get(position));
                    intent.putExtra("where","type");
                    mContext.startActivity(intent);
                }
            });

        }
    }

}
