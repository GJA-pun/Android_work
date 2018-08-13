package com.example.hegua.androidwork.presenter.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.example.hegua.androidwork.View.UpDateGoodsView;
import com.example.hegua.androidwork.utils.MyStringCallback;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.UnsupportedEncodingException;

import okhttp3.Call;

/**
 * Created by hegua on 2018/7/31.
 */

/**
 * 1,Params 2,Progress 3,Result
 * Result doInBackground(Paraams... prarams)
 * onProgressUpdate(Progress...values) 在publishProgress()调用,时调用
 * onProstExecute(Result result) 结束时
 */

public class UpDateGoodsAsyncTask extends AsyncTask<Void,String,Void>{
    private String url;
    private UpDateGoodsView upDateGoodsView;

    public UpDateGoodsAsyncTask(String url, UpDateGoodsView upDateGoodsView) {
        this.url = url;
        this.upDateGoodsView = upDateGoodsView;
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
                        System.out.println("UpDateonError----------->"+e);
                    }

                    @Override
                    public void onResponse(byte[] response, int id) {
                        String stringresponse = null;
                        try {
                            stringresponse = new String(response,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        if (stringresponse.equals("修改成功")){
                            publishProgress(stringresponse);
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
        if (values[0].equals("修改成功")){
            upDateGoodsView.showUpDateSuccess(values[0]);
        }
        super.onProgressUpdate(values);
    }
}
