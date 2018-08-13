package com.example.hegua.androidwork.presenter.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hegua.androidwork.View.MyGoodsView;
import com.example.hegua.androidwork.mygoods.adapter.MyGoodsAdapter;
import com.example.hegua.androidwork.object.trade_object.Trade;
import com.example.hegua.androidwork.utils.MyStringCallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import okhttp3.Call;

/**
 * Created by hegua on 2018/7/29.
 */

/**
 * 1,Params 2,Progress 3,Result
 * Result doInBackground(Paraams... prarams)
 * onProgressUpdate(Progress...values)
 * onProstExecute(Result result)
 */

public class MyGoodsAsyncTask extends AsyncTask<String,String,String>{
    private MyGoodsView myGoodsView;
    private Context mContext;

    public MyGoodsAsyncTask(MyGoodsView myGoodsView , Context mCotext) {
        this.myGoodsView = myGoodsView;
        this.mContext = mCotext;
    }

    @Override
    protected String doInBackground(String... strings) {
        OkHttpUtils
                .get()
                .url(strings[0])
                .build()
                .execute(new MyStringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        System.out.println("MyGoodsE-------------->"+e);
                        publishProgress("NetWorkError");
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
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        if (values[0].equals("NetWorkError")){
            myGoodsView.showNetWorkError();
        }else if (values[0].equals("stringrepsoinse")){

            ArrayList<Trade> alTrades = new ArrayList<Trade>();
            Trade[] trades ;
            Gson gson = new Gson();
            if (values[1] != null&&values[1].toString().equals("[]")==false) {
                //alTrades = gson.fromJson(values[1], new TypeToken<ArrayList<Trade>>(){}.getType());
                trades = gson.fromJson(values[1], new TypeToken<Trade[]>(){}.getType());
                System.out.println("gson---->"+alTrades.toString());
            } else {
                System.out.println("values[1]-------->"+values[1]);
                trades = null;
            }

            for (int i =0;i<trades.length;i++){
                alTrades.add(trades[i]);
            }

            RecyclerView myGoodsRecyclerView = myGoodsView.getRecyclerView();
            MyGoodsAdapter myGoodsAdapter = new MyGoodsAdapter(mContext,alTrades,myGoodsView);
            myGoodsRecyclerView.setAdapter(myGoodsAdapter);

            myGoodsRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        }
    }
}
