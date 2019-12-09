package com.example.finalproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TableLayout;

import com.example.finalproject.Weather.RecommendFragment;
import com.example.finalproject.Weather.TodayFragment;
import com.google.android.material.tabs.TabLayout;

public class WeatherFragment extends Fragment {

    private TabLayout tabLayout;
    private FrameLayout frameLayout;
    private TodayFragment todayFragment;
    private RecommendFragment recommendFragment;

    TabLayout.OnTabSelectedListener listener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            switch (tab.getPosition()) {
                case 0:
                    fragmentTransaction.show(todayFragment).hide(recommendFragment).commit();
                    break;
                case 1:
                    fragmentTransaction.show(recommendFragment).hide(todayFragment).commit();
                    break;
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

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

        tabLayout = (TabLayout) view.findViewById(R.id.tableLayout);
        frameLayout = (FrameLayout) view.findViewById(R.id.weatherFrameLayout);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        todayFragment = new TodayFragment();
        recommendFragment = new RecommendFragment();

        fragmentTransaction.add(R.id.weatherFrameLayout, todayFragment, "today").show(todayFragment);
        fragmentTransaction.add(R.id.weatherFrameLayout, recommendFragment, "recommend").hide(recommendFragment);

        fragmentTransaction.commit();
        tabLayout.addOnTabSelectedListener(listener);


    }


}
