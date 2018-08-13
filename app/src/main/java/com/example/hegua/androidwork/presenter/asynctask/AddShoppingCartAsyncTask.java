package com.example.hegua.androidwork.presenter.asynctask;

import android.os.AsyncTask;

import com.example.hegua.androidwork.View.ShowGoodsView;
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
public class AddShoppingCartAsyncTask extends AsyncTask<Void,String,Void>{

    private String url;
    private ShowGoodsView showGoodsView;

    public AddShoppingCartAsyncTask(String url, ShowGoodsView showGoodsView) {
        this.url = url;
        this.showGoodsView = showGoodsView;
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
                        System.out.println("AddShoppingCartonError----------->"+e);
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
                        }else if (sresponse.equals("添加失败")){
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
        if (values[0].equals("NetWorkError")){
            showGoodsView.showNetWorkError();
        }else if (values[0].equals("添加失败")){
            showGoodsView.showAddShoppingCartNoSuccess("添加到购物车失败");
        }else if (values[0].equals("添加成功")){
            showGoodsView.showAddShoppingCartSuccess("已添加到购物车");
        }
        super.onProgressUpdate(values);
    }
}
