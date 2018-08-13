package com.example.hegua.androidwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hegua.androidwork.View.SetUpUserView;
import com.example.hegua.androidwork.presenter.setuserimagepresenter.UserDaoPresenter;
import com.example.hegua.androidwork.presenter.setuserimagepresenter.UserDaoPresenterImpl;
import com.example.hegua.androidwork.utils.GetLocalImagePath;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by hegua on 2018/8/8.
 */

public class SetUpUserActivity extends Activity implements SetUpUserView {
    @Bind(R.id.setupuser_return_button)
    ImageButton setupuserReturnButton;
    @Bind(R.id.user_image)
    ImageView userImage;
    @Bind(R.id.setupuser_image_rl)
    RelativeLayout setupuserImageRl;
    @Bind(R.id.setupuser_image_tv)
    TextView setupuserImageTv;
    @Bind(R.id.setupuser_confirmbutton)
    Button setupuserConfirmbutton;

    private String localImagePath;
    private UserDaoPresenter userDaoPresenter;
    private GetLocalImagePath getLocalImagePath = new GetLocalImagePath(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setupuser);
        ButterKnife.bind(this);
        setupuserConfirmbutton.setText("确认修改");
        setupuserConfirmbutton.setTextSize(20f);

    }

    @OnClick({R.id.setupuser_return_button, R.id.setupuser_image_rl,R.id.setupuser_confirmbutton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setupuser_return_button:
                //Glide.get(SetUpUserActivity.this).clearDiskCache();
                //Glide.get(SetUpUserActivity.this).clearMemory();
                SetUpUserActivity.this.finish();
                break;
            case R.id.setupuser_image_rl:
                Intent intent = new Intent();
                intent.setType("image:/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
                break;
            case R.id.setupuser_confirmbutton:
                //Toast.makeText(this, "点击了确认", Toast.LENGTH_SHORT).show();
                userDaoPresenter = new UserDaoPresenterImpl(SetUpUserActivity.this);
                userDaoPresenter.upLoadUserImage(localImagePath,this);
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            String flag = data.getData().toString().substring(0, 7);
            if (requestCode == 1) {
                if (resultCode == RESULT_OK) {
                    if (flag.equals("content")) {
                        localImagePath = getLocalImagePath.getImagePath(data);
                        if (localImagePath != null) {
                            Glide.with(SetUpUserActivity.this)
                                    .load(localImagePath)
                                    .bitmapTransform(new CropCircleTransformation(SetUpUserActivity.this))
                                    .into(userImage);
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
    public void showNetWorkError() {
        Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showlocalImagePathNull(String state) {
        Toast.makeText(this, state, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUpImageCancelled() {
        Toast.makeText(this, "取消图片上传", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUpLocalImageSuccess(String state) {
        Toast.makeText(this, state, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNowUserNull(String state) {
        Toast.makeText(this, state, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSetUserImageurlNoSuccess(String state) {
        Toast.makeText(this, state, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSetUserImageurlSuccess(String state) {
        //System.out.println("SetUserImageUrl------------------------->"+state);
    }
}
