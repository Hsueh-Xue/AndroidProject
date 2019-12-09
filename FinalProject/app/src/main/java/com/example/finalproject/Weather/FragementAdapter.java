package com.example.finalproject.Weather;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragementAdapter extends FragmentPagerAdapter {
    private static final String TAG = "FragementAdapter";

    private String[] title = {"今日", "推荐"};

    public FragementAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        Log.i(TAG, String.valueOf(position));
        switch (position) {
            case 0:
                fragment = new TodayFragment();
                return fragment;
            case 1:
                fragment = new RecommendFragment();
                Log.i(TAG, "Choose 1");
                return fragment;
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
