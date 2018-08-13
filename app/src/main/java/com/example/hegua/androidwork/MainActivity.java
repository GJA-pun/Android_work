package com.example.hegua.androidwork;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.hegua.androidwork.basefragment.BaseFragment;
import com.example.hegua.androidwork.home.fragment.HomeFragment;
import com.example.hegua.androidwork.object.user_object.NowUser;
import com.example.hegua.androidwork.presenter.getuserinfopresenter.UserInfoPresenter;
import com.example.hegua.androidwork.presenter.getuserinfopresenter.UserInfoPresenterImpl;
import com.example.hegua.androidwork.shoppingcart.fragment.ShoppingCartFragment;
import com.example.hegua.androidwork.user.fragment.UserFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.fl_main)
    FrameLayout flMain;
    @Bind(R.id.rb_home)
    RadioButton rbHome;
    @Bind(R.id.rb_shoppingcat)
    RadioButton rbShoppingcat;
    @Bind(R.id.rb_user)
    RadioButton rbUser;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;

    private ArrayList<BaseFragment> fragments;
    private int position = 0;
    private Fragment tempFragment;

    private UserInfoPresenter getUserInfoPresenter = new UserInfoPresenterImpl();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if(NowUser.getUser()==null) {
            getUserInfoPresenter.getUserInfo();
        }
        initFragment();
        initListenet();

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            if(bundle.getString("where").equals("user")){
                rgMain.check(R.id.rb_user);
            } else if(bundle.getString("where").equals("shoppingcart")){
                rgMain.check(R.id.rb_shoppingcat);
            }
        }else{
            rgMain.check(R.id.rb_home);
        }

    }

    private void initFragment(){
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new UserFragment());
    }

    private void initListenet(){
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_shoppingcat:
                        position = 1;
                        break;
                    case R.id.rb_user:
                        position = 2;
                        break;
                    default:
                        position = 0;
                        break;
                }
                BaseFragment baseFragment = getFragment(position);
                switchFragment(tempFragment,baseFragment);
            }
        });
    }

    private BaseFragment getFragment(int position){
        if(fragments!=null&&fragments.size()>0){
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    private void switchFragment(Fragment fromFragment,BaseFragment nextFragment){
        if(tempFragment!=nextFragment){
            tempFragment=nextFragment;
            if(nextFragment!=null){
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if(!nextFragment.isAdded()){
                    if(fromFragment!=null){
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.fl_main,nextFragment).commit();
                }else{
                    if(fromFragment!=null){
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }
}
