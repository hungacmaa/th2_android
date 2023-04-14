package com.example.demoviewpagertablayout.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.demoviewpagertablayout.fragment.FragmentFood;
import com.example.demoviewpagertablayout.fragment.FragmentMovie;
import com.example.demoviewpagertablayout.fragment.FragmentTravel;

public class FragmentAdapter extends FragmentPagerAdapter {
    private  int pageNumber;

    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.pageNumber = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FragmentFood food = new FragmentFood();
                return food;
            case 1:
                FragmentMovie movie = new FragmentMovie();
                return movie;
            case 2:
                FragmentTravel travel = new FragmentTravel();
                return travel;
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return pageNumber;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "FOOD";
            case 1:
                return "MOVIE";
            case 2:
                return "TRAVEL";
        }
        return null;
    }
}
