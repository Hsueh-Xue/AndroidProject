package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private List<City> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        Log.i(TAG, String.valueOf(list.size()));
        CityAdapter cityAdapter = new CityAdapter(MainActivity.this, R.layout.item_layout, list);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(cityAdapter);
    }

    private void Init() {
        for (int i = 0 ;i < 5; ++i) {
            City city = new City();
            city.setCityName("北京" + i);
            city.setWeatherInfo("晴天" + i);
            city.setImageId(R.drawable.ic_launcher_background);
            list.add(city);
        }
    }
}
