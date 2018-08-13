package com.example.hegua.androidwork;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hegua.androidwork.View.ShowGoodsView;
import com.example.hegua.androidwork.object.shoppingcart_object.ShoppingCart;
import com.example.hegua.androidwork.object.trade_object.Trade;
import com.example.hegua.androidwork.object.user_object.NowUser;
import com.example.hegua.androidwork.presenter.shoppingcartpresenter.ShoppingCartPresenter;
import com.example.hegua.androidwork.presenter.shoppingcartpresenter.ShoppingCartPresenterImpl;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hegua on 2018/8/6.
 */

public class ShowGoodsActivtiy extends Activity implements ShowGoodsView{

    @Bind(R.id.showgoods_image)
    ImageView showgoodsImage;
    @Bind(R.id.showgoods_tradename)
    TextView showgoodsTradename;
    @Bind(R.id.showgoods_username_merchant)
    TextView showgoodsUsernameMerchant;
    @Bind(R.id.showgoods_volume_fabulous)
    TextView showgoodsVolumeFabulous;
    @Bind(R.id.showgoods_content_addtime)
    TextView showgoodsContentAddtime;
    @Bind(R.id.showgoods_sellingprice_distributionprice)
    TextView showgoodsSellingpriceDistributionprice;
    @Bind(R.id.showgoods_add_shoppingcart_number)
    TextView showgoodsAddShoppingcartNumber;
    @Bind(R.id.showgoods_reduce_shoppingcart)
    ImageView showgoodsReduceShoppingcart;
    @Bind(R.id.showgoods_add_shoppingcart)
    ImageView showgoodsAddShoppingcart;
    @Bind(R.id.showgoods_confirmbutton)
    ImageView showgoodsConfirmbutton;

    private Trade trade;
    private int number;
    private String where;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showgoods);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        trade = (Trade) getIntent().getSerializableExtra("trade_data");
        where = (String) bundle.get("where");

        System.out.println("ShowGoodsActivity--------------->"+where);

        initData();

    }


    @OnClick({R.id.showgoods_reduce_shoppingcart, R.id.showgoods_add_shoppingcart, R.id.showgoods_confirmbutton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.showgoods_reduce_shoppingcart:
                number = number -1;
                if (number>0){
                    showgoodsAddShoppingcartNumber.setText(number+"");
                }else{
                    number = 0;
                    showgoodsAddShoppingcartNumber.setVisibility(View.INVISIBLE);
                    showgoodsReduceShoppingcart.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.showgoods_add_shoppingcart:
                number = number +1;
                if (number>0){
                    showgoodsReduceShoppingcart.setVisibility(View.VISIBLE);
                    showgoodsAddShoppingcartNumber.setVisibility(View.VISIBLE);
                    showgoodsAddShoppingcartNumber.setText(number+"");
                }
                break;
            case R.id.showgoods_confirmbutton:
                if (number!=0) {
                    if (NowUser.getUser()!=null) {
                        String username = NowUser.getUser().getUsername();
                        String name_merchant = trade.getName() + "_" + trade.getMerchant();
                        ShoppingCart shoppingCart = new ShoppingCart(username, name_merchant, number);
                        ShoppingCartPresenter shoppingCartPresenter = new ShoppingCartPresenterImpl();
                        shoppingCartPresenter.addShoppingCart(shoppingCart, this);
                    }else{
                        Toast.makeText(this, "未登录", Toast.LENGTH_SHORT).show();
                    }
                }

                //Glide.get(ShowGoodsActivtiy.this).clearDiskCache();
                //Glide.get(ShowGoodsActivtiy.this).clearMemory();
                ShowGoodsActivtiy.this.finish();
                break;
        }
    }

    @Override
    public void initData() {
        String addtime = trade.getAddtime().substring(0, 9);
        showgoodsTradename.setText(trade.getName());
        showgoodsUsernameMerchant.setText(trade.getUsername() +"   "+ trade.getMerchant());
        showgoodsContentAddtime.setText(trade.getContent() +"\r\n" + addtime);
        showgoodsVolumeFabulous.setText("销量:" +trade.getVolume()+"  赞:"+trade.getFabulous());
        showgoodsSellingpriceDistributionprice.setText(" ￥"+trade.getSelling_price()+" + 配送价:"+trade.getDistribution_price());
        System.out.println("getImageurl------------------------->"+trade.getImageurl()+".jpg");
        Glide.with(this).load(trade.getImageurl()+".jpg").into(showgoodsImage);

        showgoodsReduceShoppingcart.setVisibility(View.INVISIBLE);
        showgoodsAddShoppingcartNumber.setVisibility(View.INVISIBLE);

        if (where.equals("ShoppingCart")){
            showgoodsAddShoppingcart.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void showAddShoppingCartSuccess(String state) {
        Toast.makeText(this, state, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAddShoppingCartNoSuccess(String state) {
        Toast.makeText(this, state, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetWorkError() {
        Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
    }
}
