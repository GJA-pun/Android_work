package com.example.hegua.androidwork.presenter.asynctask;

import android.os.AsyncTask;

import com.example.hegua.androidwork.View.AddGoodsView;
import com.example.hegua.androidwork.baseview.BaseView;
import com.example.hegua.androidwork.utils.Constant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * Created by hegua on 2018/7/29.
 */

/**
 * 1,Params 2,Progress 3,Result
 * Result doInBackground(Paraams... prarams)
 * onProgressUpdate(Progress...values) 在publishProgress()调用,时调用
 * onProstExecute(Result result) 结束时
 */

public class UploadGoodsIMageAsyncTask extends AsyncTask<String,String,String>{

    private AddGoodsView addGoodsView;

    public UploadGoodsIMageAsyncTask(BaseView baseView, String view) {
        if (view.equals("addGoodsView")) {
            this.addGoodsView = (AddGoodsView) baseView;
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        RequestParams params = new RequestParams(Constant.URL+Constant.UPLOADIMAGE);
        params.setMultipart(true);
        //strings[0] = imageurl,string[1] = imagename
        params.addBodyParameter("File",new File(strings[0]),null,strings[1]+".jpg");

        if(params!=null) {
            x.http().post(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    System.out.println("url--------->"+ result);

                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    publishProgress("网络异常");
                    System.out.println("UploadonError----------------->??"+ex);
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    publishProgress("取消图片上传");
                    System.out.println("onCancelled----------------->");
                }

                @Override
                public void onFinished() {
                    System.out.println("onFinished----------------->");
                }

            });
        }else{
            System.out.println("params");
        }
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

        if (values[0].equals("网路异常")){
            addGoodsView.showNetWorkError();
        }else if (values[0].equals("取消图片上传")){
            addGoodsView.showUpImageCancelled();
        }

        super.onProgressUpdate(values);
    }
}
