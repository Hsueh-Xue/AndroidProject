package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.finalproject.R;
import com.example.finalproject.db.WeatherInfo;
import com.example.finalproject.gson.Weather;

import java.util.List;

public class WeatherAdapter extends ArrayAdapter<WeatherInfo> {
    private int newResourceId;
    public WeatherAdapter(Context context, int resourceId, List<WeatherInfo> cityList){
        super(context, resourceId, cityList);
        newResourceId = resourceId;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        WeatherInfo weatherInfo = getItem (position);
        View view = LayoutInflater.from (getContext ()).inflate (newResourceId, parent, false);

        TextView date = view.findViewById(R.id.date);
        TextView temperature = view.findViewById(R.id.temperature);

        assert weatherInfo != null;
        date.setText(weatherInfo.getDate());
        temperature.setText(weatherInfo.getTemperature() + "℃");
        return view;
    }
}
