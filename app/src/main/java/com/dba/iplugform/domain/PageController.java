package com.dba.iplugform.domain;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.dba.iplugform.domain.Entradas;
import com.dba.iplugform.domain.Salidas;

import java.util.ArrayList;
import java.util.List;

public class PageController extends FragmentPagerAdapter {

    public PageController(@NonNull FragmentManager fm)
    {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        Fragment fragment = null;
        if (position == 0)
            fragment = new Entradas();
        else if (position == 1)
            fragment = new Salidas();

        return fragment;
    }

    @Override
    public int getCount()
    {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        String title = null;
        if (position == 0)
            title = "Entradas";
        else if (position == 1)
            title = "Salidas";
        return title;
    }

}
