package com.example.hegua.androidwork.presenter.setuserimagepresenter;

import android.content.Context;

import com.example.hegua.androidwork.View.HomeFragmentView;
import com.example.hegua.androidwork.View.SetUpUserView;
import com.example.hegua.androidwork.object.user_object.NowUser;
import com.example.hegua.androidwork.presenter.asynctask.GetUserImageUrlAsyncTask;
import com.example.hegua.androidwork.presenter.asynctask.SetUserImageurlAsyncTask;
import com.example.hegua.androidwork.presenter.asynctask.UploadUserImageAsyncTask;
import com.example.hegua.androidwork.utils.Constant;

/**
 * Created by hegua on 2018/8/8.
 */

public class UserDaoPresenterImpl implements UserDaoPresenter {
    private Context mContext;

    public UserDaoPresenterImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void upLoadUserImage(String localImagePath,SetUpUserView setUpUserView) {

        if (localImagePath==null){
            setUpUserView.showlocalImagePathNull("未获取图片");
        }else{
            if (NowUser.getUser()!=null) {
                String username = NowUser.getUser().getUsername();
                String imagename = "I_" + username;
                String imageurl = Constant.URL + Constant.LOADIMAGE + imagename;
                String data = "?username="+username+"&imageurl="+imageurl;
                String url = Constant.URL+Constant.SETUSERIMAGEURL+data;

                UploadUserImageAsyncTask uploadUserImageAsyncTask = new UploadUserImageAsyncTask(imagename, localImagePath, setUpUserView);
                uploadUserImageAsyncTask.execute();

                SetUserImageurlAsyncTask setUserImageurlAsyncTask = new SetUserImageurlAsyncTask(setUpUserView,url);
                setUserImageurlAsyncTask.execute();

            }else{
                setUpUserView.showNowUserNull("未登录");
            }
        }

    }

    @Override
    public void getUserIamgeurl(String username, HomeFragmentView homeFragmentView) {
        String data = "?username=" + username;
        String url = Constant.URL + Constant.GETUSERIMAGERURL + data;
        System.out.println("getuseriamgeurl---------------->"+url);

        GetUserImageUrlAsyncTask getUserImageUrlAsyncTask = new GetUserImageUrlAsyncTask(url,homeFragmentView);
        getUserImageUrlAsyncTask.execute();
    }
}
