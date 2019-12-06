package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CityAdapter extends ArrayAdapter<City> {
    private int newResourceId;
    public CityAdapter(Context context, int resourceId, List<City> cityList){
        super(context, resourceId, cityList);
        newResourceId = resourceId;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        City city = getItem (position);
        View view = LayoutInflater.from (getContext ()).inflate (newResourceId, parent, false);

        TextView cityName = view.findViewById (R.id.city_name);
        TextView cityWeather = view.findViewById(R.id.weather_name);
        ImageView cityImage = view.findViewById (R.id.image);

        assert city != null;
        cityName.setText(city.getCityName());
        cityWeather.setText(city.getWeatherInfo());
        cityImage.setImageResource (city.getImageId());
        return view;
    }
}
