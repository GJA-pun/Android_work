package com.example.hegua.androidwork.presenter.loginpresenter;

import com.example.hegua.androidwork.View.LoginView;
import com.example.hegua.androidwork.object.hint_object.LoginHint;
import com.example.hegua.androidwork.object.user_object.NowUser;
import com.example.hegua.androidwork.object.user_object.User;
import com.example.hegua.androidwork.presenter.asynctask.LoginAsyncTask;
import com.example.hegua.androidwork.utils.Constant;

/**
 * Created by hegua on 2018/7/17.
 */

public class LoginPresenterDaoImpl implements LoginPresenterDao {

    private LoginView loginView;
    private String url;
    private User user;

    public LoginPresenterDaoImpl(LoginView loginView){
        this.loginView = loginView;
    }

    @Override
    public void login() {
        LoginHint loginHint = new LoginHint();

        boolean flag = false;
        if(loginView.getUsername()==null||loginView.getUsername().equals("")){
            flag = true;
            loginHint.setUsernameHint("用户名不能为空");
        }
        if(loginView.getPassword()==null||loginView.getPassword().equals("")){
            flag = true;
            loginHint.setPasswordHint("密码不能为空");
        }

        if(flag){
            loginView.showLoginFall(loginHint);
            return;
        }

        user = new User(loginView.getUsername(),loginView.getPassword());

        String data = "?username=" + user.getUsername() + "&password=" + user.getPassword();
        //System.out.println("username password----------->"+username+" "+password);


        url = Constant.URL + Constant.LOGIN+ data;
        System.out.println(url);

        String[] strings ={url,user.getUsername(),user.getPassword()};

        LoginAsyncTask loginAsyncTask = new LoginAsyncTask(loginView);
        loginAsyncTask.execute(strings);

    }

    @Override
    public void getNowUser() {
        loginView.showNowUser(NowUser.getUser().getUsername(),NowUser.getUser().getPassword());
    }

}
