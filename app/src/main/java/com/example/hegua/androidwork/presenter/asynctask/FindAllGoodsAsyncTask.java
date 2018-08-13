package com.example.hegua.androidwork.presenter.asynctask;

/**
 * Created by hegua on 2018/8/5.
 */

import android.os.AsyncTask;

import com.example.hegua.androidwork.View.HomeFragmentView;
import com.example.hegua.androidwork.utils.MyStringCallback;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.UnsupportedEncodingException;

import okhttp3.Call;

/**
 * 1,Params 2,Progress 3,Result
 * Result doInBackground(Paraams... prarams)
 * onProgressUpdate(Progress...values) 在publishProgress()调用,时调用
 * onProstExecute(Result result) 结束时
 */
public class FindAllGoodsAsyncTask extends AsyncTask<Void,String,String>{
    private String url;
    private HomeFragmentView homeFragmentView;
    public FindAllGoodsAsyncTask(String url, HomeFragmentView homeFragmentView) {
        this.homeFragmentView = homeFragmentView;
        this.url = url;
    }

    @Override
    protected String doInBackground(Void... voids) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new MyStringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        publishProgress("NetWorkError");
                        System.out.println("FindAllGoodsError----------->"+e);
                    }

                    @Override
                    public void onResponse(byte[] response, int id) {
                        String stringresponse = null;
                        try {
                            if (response!=null) {
                                stringresponse = new String(response, "utf-8");
                            }
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        String[] values = {"stringrepsoinse",stringresponse};
                        publishProgress(values);
                    }

                });
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        if (values[0].equals("NetWorkError")){
            homeFragmentView.showNetWorkError();
        }else if(values[0].equals("stringrepsoinse")){
            homeFragmentView.setTrades(values[1]);
        }
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }


}
