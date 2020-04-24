package com.example.tugas.Fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tugas.Fragment.Home;
import com.example.tugas.Fragment.Panggilan;
import com.example.tugas.Fragment.Status;


public class PageAdapter extends FragmentPagerAdapter {

    private Fragment[] activities;

    public PageAdapter(FragmentManager fn){
        super(fn);
        activities = new Fragment[]{
                new Home(),
                new Status(),
                new Panggilan(),
                new Camera(),
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
