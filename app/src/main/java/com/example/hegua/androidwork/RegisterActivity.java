package com.example.hegua.androidwork;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hegua.androidwork.View.RegisterView;
import com.example.hegua.androidwork.object.hint_object.RegisterHint;
import com.example.hegua.androidwork.presenter.registerpresenter.RegisterPresenterDao;
import com.example.hegua.androidwork.presenter.registerpresenter.RegisterPresenterDaoImpl;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hegua on 2018/7/16.
 */
public class RegisterActivity extends Activity implements RegisterView {

    @Bind(R.id.userregister_username)
    EditText userregisterUsername;
    @Bind(R.id.userregister_password)
    EditText userregisterPassword;
    @Bind(R.id.userregister_repassword)
    EditText userregisterRepassword;
    @Bind(R.id.userregister_gobutton)
    Button userregisterGobutton;
    @Bind(R.id.userregister_tv)
    TextView userregisterTv;

    RegisterPresenterDao registerPresenter = new RegisterPresenterDaoImpl(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);
        ButterKnife.bind(this);
        Toast.makeText(this, "欢迎注册", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.userregister_gobutton)
    public void onViewClicked() {
        registerPresenter.register();
    }

    @Override
    public String getUsername() {
        return userregisterUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return userregisterPassword.getText().toString();
    }

    @Override
    public String getRepassword() {
        return userregisterRepassword.getText().toString();
    }

    @Override
    public void showNetWorkError() {
        Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRegistering() {

    }

    @Override
    public void showRegisterSuccess(String state) {
        Toast.makeText(this, state, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setClass(RegisterActivity.this, LoginActivity.class);
        RegisterActivity.this.startActivity(intent);
    }

    @Override
    public void showRegisterFall(RegisterHint registerHint) {
        if (registerHint.getUsernameHint().equals("")==false){
            userregisterUsername.setText(null);
        }
        if (registerHint.getPasswordHint().equals("")==false){
            userregisterPassword.setText(null);
        }
        if (registerHint.getRepasswordHint().equals("")==false){
            userregisterRepassword.setText(null);
        }
        userregisterUsername.setHint(registerHint.getUsernameHint());
        userregisterUsername.setHintTextColor(Color.parseColor("#ff0000"));
        userregisterPassword.setHint(registerHint.getPasswordHint());
        userregisterPassword.setHintTextColor(Color.parseColor("#ff0000"));
        userregisterRepassword.setHint(registerHint.getRepasswordHint());
        userregisterRepassword.setHintTextColor(Color.parseColor("#ff0000"));

        if (registerHint.getMismatchHint().equals("") == false) {
            Toast.makeText(this, registerHint.getMismatchHint(), Toast.LENGTH_SHORT).show();
            userregisterRepassword.setText(null);
            userregisterPassword.setText(null);
        }
    }

    @Override
    public void showRegisterNoSuccess(String state) {
        userregisterUsername.setText("");
        Toast.makeText(this, state, Toast.LENGTH_SHORT).show();
    }


}
