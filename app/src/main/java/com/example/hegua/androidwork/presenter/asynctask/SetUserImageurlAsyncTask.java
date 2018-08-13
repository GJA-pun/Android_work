package com.example.hegua.androidwork.presenter.asynctask;

import android.os.AsyncTask;

import com.example.hegua.androidwork.View.SetUpUserView;
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
public class SetUserImageurlAsyncTask extends AsyncTask<Void,String,Void>{
    private SetUpUserView setUpUserView;
    private String url;

    public SetUserImageurlAsyncTask(SetUpUserView setUpUserView, String url) {
        this.setUpUserView = setUpUserView;
        this.url = url;
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
                        System.out.println("SetUserImageurlonError----------->"+e);
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

                        }else if (sresponse.equals("失败")){
                            publishProgress(sresponse);
                        }else if (sresponse.equals("成功")){
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
            setUpUserView.showNetWorkError();
        }else if(values[0].equals("失败")){
            setUpUserView.showSetUserImageurlNoSuccess(values[0]);
        }else if (values[0].equals("成功")){
            setUpUserView.showSetUserImageurlSuccess(values[0]);
        }
        super.onProgressUpdate(values);
    }
}
