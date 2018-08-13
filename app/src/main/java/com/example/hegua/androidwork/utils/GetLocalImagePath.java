package com.example.hegua.androidwork.utils;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore;

/**
 * Created by hegua on 2018/7/28.
 */

public class GetLocalImagePath {
    private Context mContext;

    public GetLocalImagePath(Context context) {
        this.mContext = context;
    }

    public String getImagePath(Intent data){
        String[] imgPath = {MediaStore.Images.Media.DATA};

        //好像是android多媒体数据库的封装接口，具体的看Android文档
        Cursor cursor = mContext.getContentResolver().query(data.getData(), imgPath, null, null, null);

        //按我个人理解 这个是获得用户选择的图片的索引值
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        //将光标移至开头 ，这个很重要，不小心很容易引起越界
        cursor.moveToFirst();

        //最后根据索引值获取图片路径
        String path = cursor.getString(column_index);
        return path;
    }

}
