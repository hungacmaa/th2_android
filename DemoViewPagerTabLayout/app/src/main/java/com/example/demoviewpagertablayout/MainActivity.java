package com.example.demoviewpagertablayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.demoviewpagertablayout.adapter.FragmentAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Button btnPrev, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        btnPrev = findViewById(R.id.btnPrev);
        btnPrev.setOnClickListener(this);
        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);

        FragmentManager manager = getSupportFragmentManager();
        FragmentAdapter adapter = new FragmentAdapter(manager, 3);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        setTabLayoutTitleColor();

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int color;
                switch (tab.getPosition()) {
                    case 0:
                        color = R.color.colorPink;
                        tabLayout.setTabTextColors(Color.BLACK, color);
                        btnNext.setBackgroundColor(color);
                        btnPrev.setBackgroundColor(color);
                        break;
                    case 1:
                        color = R.color.colorBlue;
                        tabLayout.setTabTextColors(Color.BLACK, color);
                        btnNext.setBackgroundColor(color);
                        btnPrev.setBackgroundColor(color);
                        break;
                    case 2:
                        color = R.color.colorGreen;
                        tabLayout.setTabTextColors(Color.BLACK, color);
                        btnNext.setBackgroundColor(color);
                        btnPrev.setBackgroundColor(color);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        int current = viewPager.getCurrentItem();
        switch (view.getId()) {
            case R.id.btnNext:
                if (current == 2) {
                    return;
                } else {
                    viewPager.setCurrentItem(current + 1);
                    setTabLayoutTitleColor();
                }
                break;
            case R.id.btnPrev:
                if (current == 0) {
                    return;
                } else {
                    viewPager.setCurrentItem(current - 1);
                    setTabLayoutTitleColor();
                }
                break;
        }
    }

    private void setTabLayoutTitleColor() {
        switch (viewPager.getCurrentItem()) {
            case 0:
                tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.colorPink));
                break;
            case 1:
                tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.colorBlue));
                break;
            case 2:
                tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.colorGreen));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }
}