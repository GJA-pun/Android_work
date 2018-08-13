package com.example.hegua.androidwork.presenter.asynctask;

import android.os.AsyncTask;

import com.example.hegua.androidwork.View.SetUpUserView;
import com.example.hegua.androidwork.utils.Constant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * Created by hegua on 2018/8/8.
 */

/**
 * 1,Params 2,Progress 3,Result
 * Result doInBackground(Paraams... prarams)
 * onProgressUpdate(Progress...values) 在publishProgress()调用,时调用
 * onProstExecute(Result result) 结束时
 */

public class UploadUserImageAsyncTask extends AsyncTask<Void,String,Void>{

    private String imageName;
    private String loaclPath;
    private SetUpUserView setUpUserView;

    public UploadUserImageAsyncTask(String imageName, String loaclPath , SetUpUserView setUpUserView) {
        this.imageName = imageName;
        this.loaclPath = loaclPath;
        this.setUpUserView = setUpUserView;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        RequestParams params = new RequestParams(Constant.URL+Constant.UPLOADIMAGE);
        params.setMultipart(true);
        //strings[0] = imageurl,string[1] = imagename
        params.addBodyParameter("File",new File(loaclPath),null,imageName+".jpg");

        if(params!=null) {
            x.http().post(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    publishProgress("上传成功");
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    publishProgress("网络异常");
                    System.out.println("UploadUserImageonError----------------->??"+ex);
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    publishProgress("取消图片上传");
                    System.out.println("UploadUserImageCancelled----------------->");
                }

                @Override
                public void onFinished() {
                    System.out.println("UploadUserImage onFinished----------------->");
                }

            });
        }else{
            System.out.println("UserImage UpLocal params------------------>null");
        }
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
        if (values[0].equals("网络异常")){
            setUpUserView.showNetWorkError();
        }else if(values[0].equals("取消图片上传")){
            setUpUserView.showUpImageCancelled();
        }else if(values[0].equals("上传成功")){
            setUpUserView.showUpLocalImageSuccess("设置成功");
        }
        super.onProgressUpdate(values);
    }
}
