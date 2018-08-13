package com.example.hegua.androidwork;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import org.xutils.x;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by hegua on 2018/7/18.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        itinOkHttp();
        itinXutils();
    }

    private void itinXutils() {
        x.Ext.init(this);
        x.Ext.setDebug(false); // 是否输出debug日志, 开启debug会影响性能.
    }

    private void itinOkHttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(3000L, TimeUnit.MILLISECONDS)
                .readTimeout(3000L,TimeUnit.MILLISECONDS)
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }
}
