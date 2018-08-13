package com.example.hegua.androidwork.presenter.asynctask;

import android.os.AsyncTask;

import com.example.hegua.androidwork.View.MyShoppingCartView;
import com.example.hegua.androidwork.utils.MyStringCallback;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.UnsupportedEncodingException;

import okhttp3.Call;

/**
 * Created by hegua on 2018/8/9.
 */

/**
 * 1,Params 2,Progress 3,Result
 * Result doInBackground(Paraams... prarams)
 * onProgressUpdate(Progress...values) 在publishProgress()调用,时调用
 * onProstExecute(Result result) 结束时
 */

public class DeleteShoppingCartAsyncTask extends AsyncTask<Void,String,Void>{
    private String url;
    private MyShoppingCartView myShoppingCartView;

    public DeleteShoppingCartAsyncTask(String url, MyShoppingCartView myShoppingCartView) {
        this.url = url;
        this.myShoppingCartView = myShoppingCartView;
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
                        System.out.println("DeleteShoppingCartonError----------->"+e);
                    }

                    @Override
                    public void onResponse(byte[] response, int id) {

                        String sresponse = null;
                        try {
                            sresponse = new String(response,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        if (sresponse.equals("删除失败")){
                            publishProgress(sresponse);
                        }else if (sresponse.equals("删除成功")){
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
            myShoppingCartView.showNetWorkError();
        }else if (values[0].equals("删除失败")){
            myShoppingCartView.showDeleteShoppingCartNoSuceess(values[0]);
        }else if (values[0].equals("删除成功")){
            myShoppingCartView.showDeleteShoppingCartSuccess("已删除");
        }
        super.onProgressUpdate(values);
    }
}
