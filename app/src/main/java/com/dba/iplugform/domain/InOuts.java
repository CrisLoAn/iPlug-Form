package com.dba.iplugform.domain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.dba.iplugform.R;
import com.dba.iplugform.domain.PageController;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class InOuts extends AppCompatActivity{
    TabLayout tabLayout;
    ViewPager viewPager;
    PageController viewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_outs);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayOut);

        viewPagerAdapter = new PageController(
                getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        // It is used to join TabLayout with ViewPager.
        tabLayout.setupWithViewPager(viewPager);

    }

}