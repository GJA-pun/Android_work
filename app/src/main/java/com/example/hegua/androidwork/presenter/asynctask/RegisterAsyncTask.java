package com.example.hegua.androidwork.presenter.asynctask;

import android.os.AsyncTask;

import com.example.hegua.androidwork.View.RegisterView;
import com.example.hegua.androidwork.utils.MyStringCallback;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;

/**
 * Created by hegua on 2018/7/22.
 */

public class RegisterAsyncTask extends AsyncTask<String,String,String>{
    private RegisterView registerView;

    public RegisterAsyncTask(RegisterView registerView) {
        this.registerView = registerView;
    }

    @Override
    protected String doInBackground(String... strings) {
        OkHttpUtils
                .get()
                .url(strings[0])
                .build()
                .execute(new MyStringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        publishProgress("NetWorkError");
                        System.out.println("REgisteronError----------->"+e);
                    }

                    @Override
                    public void onResponse(byte[] response, int id) {
                        try {
                            System.out.println("response-------->"+ new String(response,"utf-8"));
                            String state = new String(response, "utf-8");
                            if (state.equals("注册成功")) {
                                publishProgress("注册成功");
                            }else if(state.equals("用户名已存在")){
                                publishProgress("用户名已存在");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                });

        return null;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        if (values[0].equals("NetWorkError")){
            registerView.showNetWorkError();
        }else if(values[0].equals("注册成功")){
            registerView.showRegisterSuccess(values[0]);
        }else if (values[0].equals("用户名已存在")){
            registerView.showRegisterNoSuccess(values[0]);
        }
        super.onProgressUpdate(values);
    }

}
