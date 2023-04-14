package com.example.sqlitedemo.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.sqlitedemo.fragment.FragmentFavorite;
import com.example.sqlitedemo.fragment.FragmentList;
import com.example.sqlitedemo.fragment.FragmentSearch;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentList();
            case 1:
                return new FragmentFavorite();
            case 2:
                return new FragmentSearch();
            default:
                return new FragmentList();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
