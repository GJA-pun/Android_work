package com.example.hegua.androidwork.presenter.asynctask;

import android.os.AsyncTask;

import com.example.hegua.androidwork.View.HomeFragmentView;
import com.example.hegua.androidwork.utils.MyStringCallback;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.UnsupportedEncodingException;

import okhttp3.Call;

/**
 * Created by hegua on 2018/8/8.
 */

/**
 * 1,Params 2,Progress 3,Result
 * Result doInBackground(Paraams... prarams)
 * onProgressUpdate(Progress...values) 在publishProgress()调用,时调用
 * onProstExecute(Result result) 结束时
 */

public class GetUserImageUrlAsyncTask extends AsyncTask<Void,String,Void>{
    private String url;
    private HomeFragmentView homeFragmentView;

    public GetUserImageUrlAsyncTask(String url, HomeFragmentView homeFragmentView) {
        this.url = url;
        this.homeFragmentView = homeFragmentView;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new MyStringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        publishProgress("NetWorkError");
                        System.out.println("GetUserImageUrlonError----------->"+e);
                    }

                    @Override
                    public void onResponse(byte[] response, int id) {

                        String sresponse = null;
                        try {
                            sresponse = new String(response,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        if (sresponse.equals("未发现该用户")){
                            publishProgress(sresponse);
                        }else if (sresponse.equals("没头像")){
                            publishProgress(sresponse);
                        }else if (sresponse.equals("有头像")){
                            publishProgress(sresponse);
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
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        if (values[0].equals("未发现该用户")){
            homeFragmentView.showUserNoExis("未登录");
        }else if (values[0].equals("没头像")){
            homeFragmentView.showUserNoImageurl();
        }else if(values[0].equals("有头像")){
            homeFragmentView.showUserImageurl();
        }
        super.onProgressUpdate(values);
    }
}
