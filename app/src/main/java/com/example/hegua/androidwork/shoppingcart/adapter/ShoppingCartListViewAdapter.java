package com.example.hegua.androidwork.shoppingcart.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hegua.androidwork.R;
import com.example.hegua.androidwork.View.MyShoppingCartView;
import com.example.hegua.androidwork.object.shoppingcart_object.ShoppingCart;
import com.example.hegua.androidwork.presenter.shoppingcartpresenter.ShoppingCartPresenter;
import com.example.hegua.androidwork.presenter.shoppingcartpresenter.ShoppingCartPresenterImpl;
import com.example.hegua.androidwork.utils.Constant;

import java.util.ArrayList;

/**
 * Created by hegua on 2018/8/7.
 */

public class ShoppingCartListViewAdapter extends BaseAdapter{

    private ArrayList<ShoppingCart> shoppingCarts;
    private Context mContext;
    private MyShoppingCartView myShoppingCartView;

    public ShoppingCartListViewAdapter(Context mContext, ArrayList<ShoppingCart> shoppingCarts, MyShoppingCartView myShoppingCartView) {
        this.shoppingCarts = shoppingCarts;
        this.mContext = mContext;
        this.myShoppingCartView = myShoppingCartView;
    }

    @Override
    public int getCount() {
        return shoppingCarts.size();
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
        View view1 = View.inflate(mContext, R.layout.shoppingcart_itme,null);
        TextView shoppingcart_name_merchant = (TextView) view1.findViewById(R.id.shoppingcart_itme_name_merchant);
        TextView shoppingcart_number = (TextView) view1.findViewById(R.id.shoppingcart_itme_number);
        TextView shoppingcart_addtime = (TextView) view1.findViewById(R.id.shoppingcart_itme_addtime);
        LinearLayout shoppingcart_ll = (LinearLayout) view1.findViewById(R.id.shoppingcart_itme_ll);
        ImageView shoppingcart_goods_image = (ImageView) view1.findViewById(R.id.shoppingcart_itme_goods_image);
        ImageView shoppingcart_delete = (ImageView) view1.findViewById(R.id.shoppingcart_itme_delete);
        Button shoppingcart_confirm_buy = (Button) view1.findViewById(R.id.shoppingcart_itme_confirm_buy);

        String[] s = shoppingCarts.get(i).getName_merchant().split("_");
        String name = s[0];
        String merchant = s[1];
        shoppingcart_name_merchant.setText(name);
        shoppingcart_number.setText("数量: "+shoppingCarts.get(i).getNumber());
        shoppingcart_addtime.setText(shoppingCarts.get(i).getAddtime());

        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f, mContext.getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f, mContext.getResources().getDisplayMetrics());

        //System.out.println("ShoppingCart---------------->"+shoppingCarts.get(i).getId());
        Glide.with(mContext)
                .load(Constant.URL+Constant.LOADIMAGE+name+"."+merchant+".jpg")
                .placeholder(R.color.lightgray) //占位图
                .error(R.drawable.getimage_fail)  //出错的占位图
                .override(width, height) //图片显示的分辨率 ，像素值 可以转化为DP再设置
                //.animate(R.anim.glide_anim)//动画
                .centerCrop()
                .fitCenter()
                .into(shoppingcart_goods_image);

        shoppingcart_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] s2 = shoppingCarts.get(i).getName_merchant().split("_");
                String name = s2[0];
                String merchant = s2[1];
                ShoppingCartPresenter shoppingCartPresenter = new ShoppingCartPresenterImpl();
                shoppingCartPresenter.setShoppingCartTrade(name,merchant,myShoppingCartView,mContext);
            }
        });

        shoppingcart_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "即将删除购物信息", Snackbar.LENGTH_INDEFINITE)
                        .setAction("确认删除", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ShoppingCartPresenter shoppingCartPresenter = new ShoppingCartPresenterImpl();
                                shoppingCartPresenter.deleteShoppingCart(shoppingCarts.get(i).getId(),myShoppingCartView);
                                shoppingCarts.remove(i);
                            }
                        }).show();
            }
        });

        shoppingcart_confirm_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myShoppingCartView.buyShoppingCartTrade(shoppingCarts.get(i));
            }
        });

        return view1;
    }
}
