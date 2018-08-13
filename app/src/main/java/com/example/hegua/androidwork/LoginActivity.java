package com.example.hegua.androidwork;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hegua.androidwork.View.LoginView;
import com.example.hegua.androidwork.object.hint_object.LoginHint;
import com.example.hegua.androidwork.object.user_object.NowUser;
import com.example.hegua.androidwork.presenter.getuserinfopresenter.UserInfoPresenter;
import com.example.hegua.androidwork.presenter.getuserinfopresenter.UserInfoPresenterImpl;
import com.example.hegua.androidwork.presenter.loginpresenter.LoginPresenterDao;
import com.example.hegua.androidwork.presenter.loginpresenter.LoginPresenterDaoImpl;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hegua on 2018/7/16.
 */

public class LoginActivity extends Activity implements LoginView {

    @Bind(R.id.userlogin_tv)
    TextView userloginTv;
    @Bind(R.id.userlogin_username)
    EditText userloginUsername;
    @Bind(R.id.userlogin_password)
    EditText userloginPassword;
    @Bind(R.id.userlogin_register)
    TextView userloginRegister;
    @Bind(R.id.userlogin_gobutton)
    Button userloginGobutton;

    LoginPresenterDao loginPresenter;
    UserInfoPresenter userInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenterDaoImpl(this);
        userInfoPresenter = new UserInfoPresenterImpl();
        if (NowUser.getUser() != null) {
            loginPresenter.getNowUser();
        }
    }

    @OnClick({R.id.userlogin_gobutton, R.id.userlogin_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.userlogin_gobutton:
                loginPresenter.login();
                break;
            case R.id.userlogin_register:
                LoginActivity.this.startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }

    @Override
    public String getUsername() {
        return userloginUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return userloginPassword.getText().toString();
    }

    @Override
    public void showNetWorkError() {
        Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLogining() {

    }

    @Override
    public void showLoginSuccess(String state) {
        Toast.makeText(this, state, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        intent.putExtra("where","user");
        LoginActivity.this.startActivity(intent);
    }

    @Override
    public void showLoginFall(LoginHint loginHint) {
        userloginUsername.setHint(loginHint.getUsernameHint());
        userloginUsername.setHintTextColor(Color.parseColor("#ff0000"));
        userloginPassword.setHint(loginHint.getPasswordHint());
        userloginPassword.setHintTextColor(Color.parseColor("#ff0000"));
    }

    @Override
    public void showLoginNoSuccess(String state) {
        userloginUsername.setText(null);
        userloginPassword.setText(null);
        Toast.makeText(this, state, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void askSaveUserInfo(final String username, final String password) {
        final String u = username;
        final String p = password;
        AlertDialog.Builder normalDialog = new AlertDialog.Builder(LoginActivity.this);
        normalDialog.setTitle("提示");
        normalDialog.setMessage("是否记住用户名和密码");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userInfoPresenter.saveUserInfo(u, p);
                        LoginActivity.this.showLoginSuccess("登录成功" + "以记住用户名和密码");
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userInfoPresenter.saveUserInfo("","");
                        LoginActivity.this.showLoginSuccess("登录成功");
                    }
                });
        // 显示
        normalDialog.show();

    }

    @Override
    public void showNowUser(String username, String password) {
        userloginUsername.setText(username);
        userloginPassword.setText(password);
    }


}
