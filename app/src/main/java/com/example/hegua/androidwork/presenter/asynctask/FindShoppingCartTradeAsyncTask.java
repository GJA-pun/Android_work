package com.example.hegua.androidwork.presenter.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.example.hegua.androidwork.View.MyShoppingCartView;
import com.example.hegua.androidwork.utils.MyStringCallback;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.UnsupportedEncodingException;

import okhttp3.Call;

/**
 * Created by hegua on 2018/8/7.
 */

/**
 * 1,Params 2,Progress 3,Result
 * Result doInBackground(Paraams... prarams)
 * onProgressUpdate(Progress...values) 在publishProgress()调用,时调用
 * onProstExecute(Result result) 结束时
 */

public class FindShoppingCartTradeAsyncTask extends AsyncTask<Void,String,Void>{
    private String url;
    private MyShoppingCartView myShoppingCartView;
    private Context mContext;

    public FindShoppingCartTradeAsyncTask(String url, MyShoppingCartView myShoppingCartView, Context mContext) {
        this.url = url;
        this.myShoppingCartView = myShoppingCartView;
        this.mContext = mContext;
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
                        System.out.println("FindShoppingCartTradeonError----------->"+e);
                    }

                    @Override
                    public void onResponse(byte[] response, int id) {
                        String sresponse = null;
                        try {
                            sresponse = new String(response,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        if (sresponse==null){
                            publishProgress("Null");
                        }else if(sresponse.equals("未发现商品")){
                            publishProgress("未发现商品");
                        }else {
                            publishProgress("data",sresponse);
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
        if (values[0].equals("NetWorkError")){
            myShoppingCartView.showNetWorkError();
        }else if(values[0].equals("未发现商品")){
            myShoppingCartView.showShoppingCartGetTradeNoSuccess(values[0]);
        }else if(values[0].equals("data")){
            myShoppingCartView.showShoppingCartGetTradeSuccess(values[1]);
        }
        super.onProgressUpdate(values);
    }
}
