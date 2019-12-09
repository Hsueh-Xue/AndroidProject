package com.example.finalproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.Weather.FragementAdapter;
import com.google.android.material.tabs.TabLayout;

public class WeatherFragment extends Fragment {

    public WeatherFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayout tableLayout = (TabLayout) view.findViewById(R.id.tableLayout);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.weatherViewPager);

        tableLayout.setTabMode(TabLayout.MODE_FIXED);
        tableLayout.addTab(tableLayout.newTab().setText("今日").setIcon(R.mipmap.ic_launcher));
        tableLayout.addTab(tableLayout.newTab().setText("推荐").setIcon(R.mipmap.ic_launcher));

        viewPager.setAdapter(new FragementAdapter(getFragmentManager()));
        tableLayout.setupWithViewPager(viewPager);

    }
}
