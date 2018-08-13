package com.example.hegua.androidwork.presenter.asynctask;

import android.os.AsyncTask;

import com.example.hegua.androidwork.View.SearchBoxView;
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

public class FuzzyQuseryGoodsAsyncTask extends AsyncTask<Void,String,Void>{
    private String url;
    private SearchBoxView searchBoxView;

    public FuzzyQuseryGoodsAsyncTask(String url, SearchBoxView searchBoxView) {
        this.url = url;
        this.searchBoxView = searchBoxView;
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
                        System.out.println("FuzzyQuseryGoodsonError----------->"+e);
                    }

                    @Override
                    public void onResponse(byte[] response, int id) {

                        String sresponse = null;
                        try {
                            sresponse = new String(response,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        if (sresponse!=null&&sresponse.equals("[]")==false&&sresponse.equals("")==false){
                            publishProgress("data",sresponse);
                        }else{
                            publishProgress("Null");
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
            searchBoxView.showNetWorkError();
        }else if (values[0].equals("Null")){
            searchBoxView.showFuzzyQuseryGoodsDataNull("没有找到相应的商品");
        }else if (values[0].equals("data")){
            searchBoxView.showFuzzyQuseryGoodsData(values[1]);
        }
        super.onProgressUpdate(values);
    }
}
