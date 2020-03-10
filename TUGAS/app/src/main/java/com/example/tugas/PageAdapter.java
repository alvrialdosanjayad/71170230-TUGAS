package com.example.tugas;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class PageAdapter extends FragmentPagerAdapter {

    private Fragment[] activities;

    public PageAdapter(FragmentManager fn){
        super(fn);
        activities = new Fragment[]{
                new HomeFragment(),
                new StatusFragment(),
                new PanggilanFragment(),
        };
    }

    @Override
    public Fragment getItem(int position) {
        return activities[position];
    }

    @Override
    public int getCount(){
        return activities.length;
    }

    @Override
    public CharSequence getPageTitle(int position){
        String title = getItem(position).getClass().getName();
        return title.subSequence(title.lastIndexOf(".") + 1, title.length());
    }
}
