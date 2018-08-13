package com.example.hegua.androidwork.utils;

import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by hegua on 2018/7/18.
 */

public abstract class MyStringCallback extends Callback<byte[]> {

    @Override
    public byte[] parseNetworkResponse(Response response, int id) throws Exception {
        return response.body().string().getBytes();
    }

}
