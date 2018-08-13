package com.example.hegua.androidwork.home.adapter;

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

/**
 * Created by hegua on 2018/7/26.
 */

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.ViewHolder>{

    private Context mContext;
    //private ArrayList<Trade> trades;
    private Trade[] trades;

    /*public GoodsListAdapter(Context mContext, ArrayList<Trade> trades) {
        this.mContext = mContext;
        this.trades =trades;
    }*/

    public GoodsListAdapter(Context mContext,Trade[] trades) {
        this.mContext = mContext;
        this.trades = trades;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.trade,null);

        return new ViewHolder(view,mContext);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        /*if (trades!=null) {
            holder.tradeName.setText(trades.get(position).getName());
            holder.tradeSellingPrice.setText("￥"+trades.get(position).getSelling_price());
            holder.tradeMerchantFabulous.setText("店家:"+trades.get(position).getMerchant()+"" + "   赞:"+trades.get(position).getFabulous());
            holder.tradeId.setText(trades.get(position).getId()+"");
            holder.tradePosition.setText(position+"");

            int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f, mContext.getResources().getDisplayMetrics());
            int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f, mContext.getResources().getDisplayMetrics());

            System.out.println("imageurl------------------->" + trades.get(position).getImageurl()+".jpg");
            Glide.with(mContext)
                    .load(trades.get(position).getImageurl()+".jpg")
                    .placeholder(R.color.lightgray) //占位图
                    .error(R.drawable.getimage_fail)  //出错的占位图
                    .override(width, height) //图片显示的分辨率 ，像素值 可以转化为DP再设置
                    //.animate(R.anim.glide_anim)//动画
                    .centerCrop()
                    .fitCenter()
                    .into(holder.tradeImage);

        }*/

        if (trades!=null) {
            holder.tradeName.setText(trades[position].getName());
            holder.tradeSellingPrice.setText("￥"+trades[position].getSelling_price());
            holder.tradeMerchantFabulous.setText("店家:"+trades[position].getMerchant()+"" + "   赞:"+trades[position].getFabulous());
            holder.tradeId.setText(trades[position].getId()+"");
            holder.tradePosition.setText(position+"");

            int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f, mContext.getResources().getDisplayMetrics());
            int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f, mContext.getResources().getDisplayMetrics());

            Glide.with(mContext)
                    .load(trades[position].getImageurl()+".jpg")
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
        return trades.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private TextView tradeName;
        private TextView tradeMerchantFabulous;
        private ImageView tradeImage;
        private TextView tradeId;
        private TextView tradePosition;
        private TextView tradeSellingPrice;
        private LinearLayout linearLayout;

        public ViewHolder(View itemView ,Context context) {
            super(itemView);
            this.mContext = context;
            this.linearLayout = (LinearLayout) itemView.findViewById(R.id.trade_ll);
            this.tradeName = (TextView) itemView.findViewById(R.id.trade_name);
            this.tradeMerchantFabulous = (TextView) itemView.findViewById(R.id.trade_merchant_fabulous);
            this.tradeSellingPrice = (TextView) itemView.findViewById(R.id.trade_sellingprice);
            this.tradeImage = (ImageView) itemView.findViewById(R.id.trade_image);
            this.tradeId = (TextView) itemView.findViewById(R.id.trade_id);
            this.tradePosition = (TextView) itemView.findViewById(R.id.trade_position);


            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String p = tradePosition.getText().toString();
                    int position = Integer.parseInt(p);
                    Intent intent = new Intent(mContext, ShowGoodsActivtiy.class);
                    //intent.putExtra("trade_data",trades.get(position));
                    intent.putExtra("trade_data",trades[position]);
                    intent.putExtra("where","goodslist");
                    mContext.startActivity(intent);
                }
            });

        }
    }

}
