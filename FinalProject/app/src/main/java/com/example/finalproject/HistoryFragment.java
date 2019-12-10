package com.example.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowId;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.example.finalproject.db.WeatherInfo;
import com.example.finalproject.util.DataBaseUtil;

import java.util.ArrayList;
import java.util.List;


public class HistoryFragment extends Fragment {
    private static final String TAG = "HistoryFragment";

    private ArrayAdapter<WeatherInfo> adapter;

    private ListView listView;

    private List<WeatherInfo> weatherInfoList = new ArrayList<>();

    private Button button;

    public HistoryFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.list_view);
        adapter = new WeatherAdapter(getActivity(), R.layout.item_layout, weatherInfoList);
        listView.setAdapter(adapter);

        button = view.findViewById(R.id.choose_button);
        final PopupMenu popupMenu = new PopupMenu(getActivity(), button);
        popupMenu.getMenuInflater().inflate(R.menu.day_choose, popupMenu.getMenu());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SharedPreferences.Editor editor =
                        PreferenceManager.getDefaultSharedPreferences
                        (getActivity()).edit();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.day1:
                                if (menuItem.isChecked()) menuItem.setChecked(false);
                                else menuItem.setChecked(true);
                                editor.putString("number", "1");
                                editor.apply();
                                break;
                            case R.id.day2:
                                if (menuItem.isChecked()) menuItem.setChecked(false);
                                else menuItem.setChecked(true);
                                editor.putString("number", "2");
                                editor.apply();
                                break;
                            case R.id.day3:
                                if (menuItem.isChecked()) menuItem.setChecked(false);
                                else menuItem.setChecked(true);
                                editor.putString("number", "3");
                                editor.apply();
                                break;
                            case R.id.day4:
                                if (menuItem.isChecked()) menuItem.setChecked(false);
                                else menuItem.setChecked(true);
                                editor.putString("number", "4");
                                editor.apply();
                                break;
                            case R.id.day5:
                                if (menuItem.isChecked()) menuItem.setChecked(false);
                                else menuItem.setChecked(true);
                                editor.putString("number", "5");
                                editor.apply();
                                break;
                            case R.id.day6:
                                if (menuItem.isChecked()) menuItem.setChecked(false);
                                else menuItem.setChecked(true);
                                editor.putString("number", "6");
                                editor.apply();
                                break;
                            case R.id.day7:
                                if (menuItem.isChecked()) menuItem.setChecked(false);
                                else menuItem.setChecked(true);
                                editor.putString("number", "7");
                                editor.apply();
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
                popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
                    @Override
                    public void onDismiss(PopupMenu popupMenu) {
                        refresh();
                    }
                });
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        refresh();

    }

    public void refresh() {
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(getActivity());
        String cityId = preferences.getString("cityId", null);
        int number = 7;
        String numberString = preferences.getString("number", null);
        if (numberString != null) {
            number = Integer.parseInt(numberString);
        }
        List<WeatherInfo> weatherInfos = DataBaseUtil.getInstance().selectWeather(cityId, number);
        weatherInfoList.clear();
        for (WeatherInfo weatherInfo : weatherInfos) {
            weatherInfoList.add(weatherInfo);
        }
        Log.i(TAG, cityId + " " + weatherInfoList.size() + " " + number);
        adapter.notifyDataSetChanged();
        listView.setSelection(0);
    }
}
