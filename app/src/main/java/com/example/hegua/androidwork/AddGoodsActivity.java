package com.example.hegua.androidwork;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hegua.androidwork.View.AddGoodsView;
import com.example.hegua.androidwork.object.hint_object.GoodsDaoHint;
import com.example.hegua.androidwork.object.user_object.NowUser;
import com.example.hegua.androidwork.presenter.goodsdaopresenter.GoodsDaoPresenter;
import com.example.hegua.androidwork.presenter.goodsdaopresenter.GoodsDaoPresenterImpl;
import com.example.hegua.androidwork.utils.GetLocalImagePath;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hegua on 2018/7/23.
 */

public class AddGoodsActivity extends Activity implements AddGoodsView {

    @Bind(R.id.goodsdao_return_button)
    ImageButton goodsdaoReturnButton;
    @Bind(R.id.rv_top)
    LinearLayout rvTop;
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
    @Bind(R.id.settrade_type_button)
    ImageButton settradeTypeButton;

    private GoodsDaoPresenter goodsDaoPresenter = new GoodsDaoPresenterImpl();
    private GetLocalImagePath getLocalImagePath = new GetLocalImagePath(this);
    private String localImagePath;
    ArrayList<String> typedata = new ArrayList<String>();
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodsdao);
        ButterKnife.bind(this);

        initData();

    }


    @Override
    public void initData() {
        typedata.add("生鲜果蔬");
        typedata.add("网上超市");
        typedata.add("浪漫鲜花");
        typedata.add("香茶茶点");
        typedata.add("甜点饮品");
        typedata.add("美味三餐");
        typedata.add("甜蜜蛋糕");
        typedata.add("炸鸡零食");

        settradeType.setEnabled(false);

    }

    @OnClick({R.id.goodsdao_return_button, R.id.settrade_image, R.id.goodsdao_confirmbutton, R.id.settrade_type_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.goodsdao_return_button:
                AddGoodsActivity.this.startActivity(new Intent(AddGoodsActivity.this, MyGoodsActivity.class));
                break;
            case R.id.settrade_image:
                Intent intent = new Intent();
                intent.setType("image:/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
                break;
            case R.id.goodsdao_confirmbutton:
                if (NowUser.getUser() != null) {
                    goodsDaoPresenter.addgoods(localImagePath,this);
                } else {
                    Toast.makeText(this, "未登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.settrade_type_button:
                ListView listView = new ListView(getApplicationContext());
                ListViewAdapter listViewAdapter = new ListViewAdapter();
                listView.setAdapter(listViewAdapter);

                popupWindow = new PopupWindow(listView);
                popupWindow.setWidth(settradeType.getWidth());
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);

                popupWindow.showAsDropDown(settradeType);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            String flag = data.getData().toString().substring(0, 7);
            if (requestCode == 1) {
                if (resultCode == RESULT_OK) {
                    if (flag.equals("content")) {
                        localImagePath = getLocalImagePath.getImagePath(data);
                        if (localImagePath != null) {
                            Glide.with(this).load(localImagePath).into(settradeImage);
                        }
                    } else {
                        Toast.makeText(this, "请选择图片文件", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        } else {
            Toast.makeText(this, "未选取图片", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public String getTradeName() {
        return settradeName.getText().toString();
    }

    @Override
    public String getTradeMerchant() {
        return settradeMerchant.getText().toString();
    }

    @Override
    public String getTradeContent() {
        return settradeContent.getText().toString();
    }

    @Override
    public String getTradeSellingPrice() {
        return settradeSellingprice.getText().toString();
    }

    @Override
    public String getTradeDistributionPrice() {
        return settradeDistributionprice.getText().toString();
    }

    @Override
    public String getTradeType() {
        return settradeType.getText().toString();
    }

    @Override
    public void showAddGoodsSuccess(String state) {
        Toast.makeText(this, state, Toast.LENGTH_SHORT).show();
        AddGoodsActivity.this.startActivity(new Intent(AddGoodsActivity.this, MyGoodsActivity.class));
    }

    @Override
    public void showAddGoodsFall(GoodsDaoHint goodsDaoHint) {
        settradeName.setHint(goodsDaoHint.getNameHint());
        settradeName.setHintTextColor(Color.parseColor("#ff0000"));
        settradeContent.setHint(goodsDaoHint.getContentHint());
        settradeContent.setHintTextColor(Color.parseColor("#ff0000"));
        settradeDistributionprice.setHint(goodsDaoHint.getDistributionPriceHint());
        settradeDistributionprice.setHintTextColor(Color.parseColor("#ff0000"));
        settradeMerchant.setHint(goodsDaoHint.getMerchantHint());
        settradeMerchant.setHintTextColor(Color.parseColor("#ff0000"));
        settradeType.setHint(goodsDaoHint.getTypeHint());
        settradeType.setHintTextColor(Color.parseColor("#ff0000"));
        settradeSellingprice.setHint(goodsDaoHint.getSellingPriceHint());
        settradeSellingprice.setHintTextColor(Color.parseColor("#ff0000"));

        if (goodsDaoHint.getLocalPath().equals("") == false) {
            this.showUnselectedPicture(goodsDaoHint.getLocalPath());
        }
    }

    @Override
    public void showAddGoodsNoSuccess(String state) {
        Toast.makeText(this, state, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUpImageCancelled() {
        Toast.makeText(this, "取消图片上传", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUnselectedPicture(String state) {
        Toast.makeText(this, state, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetWorkError() {
        Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
    }


    private class ListViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return typedata.size();
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
            View view1 = View.inflate(getApplicationContext(),R.layout.popupwindow_type,null);
            TextView textView = (TextView) view1.findViewById(R.id.settrade_type_lvtv);
            LinearLayout linearLayout = (LinearLayout) view1.findViewById(R.id.settrade_type_ll);
            textView.setText(typedata.get(i));

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    settradeType.setText(typedata.get(i));
                    popupWindow.dismiss();
                    popupWindow=null;
                }
            });

            return view1;
        }
    }


}
