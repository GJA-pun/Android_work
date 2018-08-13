package com.example.hegua.androidwork.presenter.asynctask;

import android.os.AsyncTask;

import com.example.hegua.androidwork.View.AddGoodsView;
import com.example.hegua.androidwork.object.trade_object.Trade;
import com.example.hegua.androidwork.utils.MyStringCallback;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.UnsupportedEncodingException;

import okhttp3.Call;

/**
 * Created by hegua on 2018/7/25.
 */

/**
 * 1,Params 2,Progress 3,Result
 * Result doInBackground(Paraams... prarams)
 * onProgressUpdate(Progress...values) 在publishProgress()调用,时调用
 * onProstExecute(Result result) 结束时
 */

public class AddGoodsAsyncTask extends AsyncTask<Trade,String,String>{

    private AddGoodsView addGoodsView;
    private final String URL;

    public AddGoodsAsyncTask(AddGoodsView addGoodsView, String URL) {
        this.addGoodsView = addGoodsView;
        this.URL = URL;
    }

    @Override
    protected String doInBackground(Trade... trades) {
        OkHttpUtils
                .get()
                .url(URL)
                .build()
                .execute(new MyStringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        publishProgress("NetWorkError");
                        System.out.println("AddGoodsonError----------->"+e);
                    }

                    @Override
                    public void onResponse(byte[] response, int id) {

                        String sresponse = null;
                        try {
                            sresponse = new String(response,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        if (sresponse.equals("添加成功")){
                            publishProgress(sresponse);
                        }else if (sresponse.equals("商品已存在")){
                            publishProgress("商品已存在");
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
            addGoodsView.showNetWorkError();
        }else if(values[0].equals("添加成功")){
            addGoodsView.showAddGoodsSuccess(values[0]);
        }else if (values[0].equals("商品已存在")){
            addGoodsView.showAddGoodsNoSuccess(values[0]);
        }
        super.onProgressUpdate(values);
    }


}
