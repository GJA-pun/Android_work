package com.example.hegua.androidwork.presenter.asynctask;

import android.os.AsyncTask;

import com.example.hegua.androidwork.View.LoginView;
import com.example.hegua.androidwork.object.user_object.NowUser;
import com.example.hegua.androidwork.object.user_object.User;
import com.example.hegua.androidwork.utils.MyStringCallback;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.UnsupportedEncodingException;

import okhttp3.Call;

/**
 * Created by hegua on 2018/7/22.
 */

/**
 * 1,Params 2,Progress 3,Result
 * Result doInBackground(Paraams... prarams)
 * onProgressUpdate(Progress...values)
 * onProstExecute(Result result)
 */

public class LoginAsyncTask extends AsyncTask<String,String,String>{

    private LoginView loginView;

    public LoginAsyncTask(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    protected String doInBackground(final String... strings) {
        OkHttpUtils
                .get()
                .url(strings[0])
                .build()
                .execute(new MyStringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        publishProgress("NetWorkError");
                        System.out.println("LoginonError----------->"+e);
                    }

                    @Override
                    public void onResponse(byte[] response, int id) {
                        String sresponse = null;
                        try {
                            sresponse = new String(response,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        System.out.println("response-------->"+ sresponse);
                            String state = sresponse;
                            if(state.equals("登录成功")) {
                                User user = new User(strings[1],strings[2]);
                                NowUser.setUser(user);
                                String[] s = {"AskSaveUserInfo",strings[1],strings[2]};
                                publishProgress(s);
                            }else if(state.equals("用户名和密码不一致")){
                                publishProgress("用户名和密码不一致");
                            }
                    }

                });

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        //在doinbackground执行完后执行 接收String
        //在UI线程中
        super.onPostExecute(s);
    }

    @Override
    protected void onPreExecute() {
        //execute开始的执行方法
        //运行在UI线程中。。。。。
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(String... values) {
        if (values[0].equals("NetWorkError")){
            loginView.showNetWorkError();
        }else if(values[0].equals("AskSaveUserInfo")){
            loginView.askSaveUserInfo(values[1],values[2]);
        }else if (values[0].equals("用户名和密码不一致")){
            loginView.showLoginNoSuccess(values[0]);
        }
        super.onProgressUpdate(values);
    }
}
