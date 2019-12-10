package com.example.finalproject.util;

import android.net.wifi.WifiManager;
import android.widget.LinearLayout;

import com.example.finalproject.db.UserInfo;
import com.example.finalproject.db.WeatherInfo;
import com.example.finalproject.gson.Weather;

import org.litepal.crud.DataSupport;

import java.util.List;

public class DataBaseUtil {

    private static DataBaseUtil instance;

    public static DataBaseUtil getInstance() {
        if (instance == null) {
            instance = new DataBaseUtil();
        }
        return instance;
    }

    public static void setInstance(DataBaseUtil instance) {
        DataBaseUtil.instance = instance;
    }

    public boolean Login(String userName, String password) {
        UserInfo userInfo = (UserInfo) DataSupport.where("userName = " + userName + " and " +
                "password = " + password).find(UserInfo.class);
        return userInfo != null;
    }

    public void changePassword(String userName, String password, String newPassword) {
        UserInfo userInfo = (UserInfo) DataSupport.where("userName = " + userName + " and " +
                "password = " + password).find(UserInfo.class);
        userInfo.setPassword(newPassword);
        userInfo.save();
    }

    public List<WeatherInfo> selectWeather(String cityId, int Number) {
        List<WeatherInfo> weatherList =
                DataSupport.where("cityId = " + cityId).find(WeatherInfo.class);
        List<WeatherInfo> response = null;
        for (int i = 0; i < Number && i < weatherList.size(); ++i) {
            response.add(weatherList.get(i));
        }
        return response;
    }

    public void saveWeatherInfo(String cityId, String date, String temperature) {
        WeatherInfo weatherInfo =
                DataSupport.where("cityId = " + cityId + "and date = " + date).findFirst(WeatherInfo.class);
        if (weatherInfo == null) {
            weatherInfo = new WeatherInfo();
        }
        weatherInfo.setCityId(cityId);
        weatherInfo.setDate(date);
        weatherInfo.setTemperature(temperature);
        weatherInfo.save();
    }
}
