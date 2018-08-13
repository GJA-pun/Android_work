package com.example.hegua.androidwork;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hegua.androidwork.View.UpDateGoodsView;
import com.example.hegua.androidwork.object.hint_object.GoodsDaoHint;
import com.example.hegua.androidwork.object.trade_object.Trade;
import com.example.hegua.androidwork.presenter.updatepresnter.UpDatePresnter;
import com.example.hegua.androidwork.presenter.updatepresnter.UpDatePresnterImpl;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hegua on 2018/7/31.
 */

public class UpDateGoodsActivity extends Activity implements UpDateGoodsView {

    @Bind(R.id.goodsdao_return_button)
    ImageButton goodsdaoReturnButton;
    @Bind(R.id.settrade_image)
    ImageButton settradeImage;
    @Bind(R.id.settrade_name)
    EditText settradeName;
    @Bind(R.id.settrade_merchant)
    EditText settradeMerchant;
    @Bind(R.id.settrade_content)
    EditText settradeContent;
    @Bind(R.id.settrade_sellingprice)
    EditText settradeSellingprice;
    @Bind(R.id.settrade_distributionprice)
    EditText settradeDistributionprice;
    @Bind(R.id.settrade_type)
    EditText settradeType;
    @Bind(R.id.goodsdao_confirmbutton)
    Button goodsdaoConfirmbutton;


    @Bind(R.id.rv_top)
    LinearLayout rvTop;
    @Bind(R.id.settrade_name_tv)
    TextView settradeNameTv;
    @Bind(R.id.settrade_merchant_tv)
    TextView settradeMerchantTv;
    @Bind(R.id.settrade_content_tv)
    TextView settradeContentTv;
    @Bind(R.id.settrade_sellingprice_tv)
    TextView settradeSellingpriceTv;
    @Bind(R.id.settrade_distributionprice_tv)
    TextView settradeDistributionpriceTv;
    @Bind(R.id.settrade_type_tv)
    TextView settradeTypeTv;


    private Trade trade = null;
    private UpDatePresnter upDatePresnter = new UpDatePresnterImpl(UpDateGoodsActivity.this,this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodsdao);
        ButterKnife.bind(this);
        trade = (Trade) getIntent().getSerializableExtra("trade_data");

        initData();

    }

    @Override
    public void initData() {
        if (trade.getImageurl() != null) {
            Glide.with(this).load(trade.getImageurl() + ".jpg").into(settradeImage);
        }

        settradeName.setText(trade.getName());
        settradeName.setEnabled(false);
        settradeType.setText(trade.getType());
        settradeType.setEnabled(false);
        settradeMerchant.setText(trade.getMerchant());
        settradeMerchant.setEnabled(false);
        settradeDistributionprice.setText(trade.getDistribution_price());
        settradeSellingprice.setText(trade.getSelling_price());
        settradeContent.setText(trade.getContent());


        settradeNameTv.setVisibility(View.VISIBLE);
        settradeMerchantTv.setVisibility(View.VISIBLE);
        settradeContentTv.setVisibility(View.VISIBLE);
        settradeSellingpriceTv.setVisibility(View.VISIBLE);
        settradeDistributionpriceTv.setVisibility(View.VISIBLE);
        settradeTypeTv.setVisibility(View.VISIBLE);

    }

    @OnClick({R.id.goodsdao_return_button, R.id.goodsdao_confirmbutton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.goodsdao_return_button:
                UpDateGoodsActivity.this.startActivity(new Intent(UpDateGoodsActivity.this, MyGoodsActivity.class));
                break;
            case R.id.goodsdao_confirmbutton:
                Toast.makeText(this, "确认", Toast.LENGTH_SHORT).show();
                upDatePresnter.upDate();
                break;
        }
    }

    @Override
    public void showUpDateFall(GoodsDaoHint goodsDaoHint) {
        settradeContent.setHint(goodsDaoHint.getContentHint());
        settradeContent.setHintTextColor(Color.parseColor("#ff0000"));
        settradeDistributionprice.setHint(goodsDaoHint.getDistributionPriceHint());
        settradeDistributionprice.setHintTextColor(Color.parseColor("#ff0000"));
        settradeSellingprice.setHint(goodsDaoHint.getSellingPriceHint());
        settradeSellingprice.setHintTextColor(Color.parseColor("#ff0000"));

    }

    @Override
    public void showUpDateSuccess(String state) {
        Toast.makeText(this, state, Toast.LENGTH_SHORT).show();
        UpDateGoodsActivity.this.startActivity(new Intent(UpDateGoodsActivity.this, MyGoodsActivity.class));
    }

    @Override
    public void showNetWorkError() {
        Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getTradeId() {
        return trade.getId();
    }

    @Override
    public String getTradeDistributionPrice() {return settradeDistributionprice.getText().toString();}

    @Override
    public String getTradeSellingPrice() {return settradeSellingprice.getText().toString();}

    @Override
    public String getTradeContent() {
        return settradeContent.getText().toString();
    }


}
