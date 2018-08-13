package com.example.hegua.androidwork.type.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hegua.androidwork.type.fragment.TypeFragment;

import java.util.ArrayList;

/**
 * Created by hegua on 2018/8/2.
 */

public class TypeViewPaperAdapter extends FragmentPagerAdapter{
    private ArrayList<TypeFragment> fragments;

    public TypeViewPaperAdapter(FragmentManager fm, ArrayList<TypeFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }
}
