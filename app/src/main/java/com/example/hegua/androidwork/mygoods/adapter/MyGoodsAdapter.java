package com.example.hegua.androidwork.mygoods.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
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
import com.example.hegua.androidwork.UpDateGoodsActivity;
import com.example.hegua.androidwork.View.MyGoodsView;
import com.example.hegua.androidwork.object.trade_object.Trade;
import com.example.hegua.androidwork.presenter.mygoodspresenter.MyGoodsPresenter;
import com.example.hegua.androidwork.presenter.mygoodspresenter.MyGoodsPresenterImpl;

import java.util.ArrayList;

/**
 * Created by hegua on 2018/7/23.
 */

public class MyGoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private ArrayList<Trade> data;
    private MyGoodsView myGoodsView;

    public MyGoodsAdapter(Context context , ArrayList<Trade> data , MyGoodsView myGoodsView) {
        this.mContext = context;
        this.data = data;
        this.myGoodsView = myGoodsView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(mContext,R.layout.trade,null);
        return new TradeViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (data!=null) {
            ((TradeViewHolder) holder).tradeName.setText(data.get(position).getName());
            ((TradeViewHolder) holder).tradeSellingPrice.setText("￥"+data.get(position).getSelling_price());
            ((TradeViewHolder) holder).tradeMerchantFabulous.setText("店家:"+data.get(position).getMerchant()+"" +
                    "   赞:"+data.get(position).getFabulous());
            ((TradeViewHolder) holder).tradeId.setText(data.get(position).getId()+"");
            ((TradeViewHolder) holder).tradeposition.setText(position+"");

            int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f, mContext.getResources().getDisplayMetrics());
            int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f, mContext.getResources().getDisplayMetrics());

            Glide.with(mContext)
                    .load(data.get(position).getImageurl()+".jpg")
                    .placeholder(R.color.lightgray) //占位图
                    .error(R.drawable.getimage_fail)  //出错的占位图
                    .override(width, height) //图片显示的分辨率 ，像素值 可以转化为DP再设置
                    //.animate(R.anim.glide_anim)//动画
                    .centerCrop()
                    .fitCenter()
                    .into(((TradeViewHolder) holder).tradeImage);

        }
    }

    @Override
    public int getItemCount() {
        return data==null? 0 : data.size();
    }



    class TradeViewHolder extends RecyclerView.ViewHolder{
        private TextView tradeName;
        private TextView tradeSellingPrice;
        private TextView tradeMerchantFabulous;
        private ImageView tradeImage;
        private ImageView tradeDelete;
        private ImageView tradeUpdate;
        private TextView tradeId;
        private TextView tradeposition;
        private LinearLayout linearLayout;

        public TradeViewHolder(View itemView) {
            super(itemView);
            tradeName = (TextView) itemView.findViewById(R.id.trade_name);
            tradeSellingPrice = (TextView) itemView.findViewById(R.id.trade_sellingprice);
            tradeMerchantFabulous = (TextView) itemView.findViewById(R.id.trade_merchant_fabulous);
            tradeId = (TextView) itemView.findViewById(R.id.trade_id);
            tradeImage = (ImageView) itemView.findViewById(R.id.trade_image);
            tradeDelete = (ImageView) itemView.findViewById(R.id.trade_delete);
            tradeUpdate = (ImageView) itemView.findViewById(R.id.trade_update);
            tradeposition = (TextView) itemView.findViewById(R.id.trade_position);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.trade_ll);

            tradeDelete.setVisibility(ViewGroup.VISIBLE);
            tradeUpdate.setVisibility(ViewGroup.VISIBLE);
            tradeDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "即将删除商品信息", Snackbar.LENGTH_INDEFINITE)
                            .setAction("确认删除", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int id = Integer.parseInt(tradeId.getText().toString());
                                    int position = Integer.parseInt(tradeposition.getText().toString());
                                    MyGoodsPresenter myGoodsPresenter = new MyGoodsPresenterImpl(myGoodsView,mContext);
                                    myGoodsPresenter.deleteTrade(id);
                                    notifyItemRemoved(position);
                                    data.remove(position);
                                    if(position != getItemCount()){
                                        notifyItemRangeChanged(position, getItemCount() - position);
                                    }
                                }
                            }).show();
                }
            });

            tradeUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = Integer.parseInt(tradeposition.getText().toString());
                    Intent intent = new Intent(mContext, UpDateGoodsActivity.class);
                    intent.putExtra("trade_data", data.get(position));
                    mContext.startActivity(intent);
                }
            });

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = Integer.parseInt(tradeposition.getText().toString());
                    Intent intent = new Intent(mContext, ShowGoodsActivtiy.class);
                    intent.putExtra("trade_data",data.get(position));
                    intent.putExtra("where","goodslist");
                    mContext.startActivity(intent);
                }
            });

        }
    }

}
