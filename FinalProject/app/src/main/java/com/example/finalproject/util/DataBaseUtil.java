package com.example.finalproject.util;

import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.finalproject.db.UserInfo;
import com.example.finalproject.db.WeatherInfo;
import com.example.finalproject.gson.Weather;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class DataBaseUtil {
    private static final String TAG = "DataBaseUtil";

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
        UserInfo userInfo = (UserInfo) DataSupport.where("userName = ? and password = ?",
                userName, password).findFirst(UserInfo.class);
        return userInfo != null;
    }

    public boolean register(String userName, String password) {
        UserInfo userInfo = DataSupport.where("userName = ?", userName).findFirst(UserInfo.class);
        if (userInfo == null) {
            userInfo = new UserInfo();
            userInfo.setUserName(userName);
            userInfo.setPassword(password);
            return true;
        } else {
            return false;
        }
    }

    public void changePassword(String userName, String password, String newPassword) {
        UserInfo userInfo = (UserInfo) DataSupport.where("userName = ? and password = ?",
                userName, password).findFirst(UserInfo.class);
        userInfo.setPassword(newPassword);
        userInfo.save();
    }

    public List<WeatherInfo> selectWeather(String cityId, int Number) {
        List<WeatherInfo> weatherList =
                DataSupport.where("cityid = ?", cityId).find(WeatherInfo.class);
        List<WeatherInfo> response = new ArrayList<WeatherInfo>();
        for (int i = 0; i < Number && i < weatherList.size(); ++i) {
            response.add(weatherList.get(i));
        }
        return response;
    }

    public void saveWeatherInfo(String cityId, String date, String temperature) {
//        List<WeatherInfo> weatherInfos = DataSupport.findAll(WeatherInfo.class);
        WeatherInfo weatherInfo =
                DataSupport.where("cityid = ? AND date = ?", cityId, date).findFirst(WeatherInfo.class);
        if (weatherInfo == null) {
            weatherInfo = new WeatherInfo();
        }
        weatherInfo.setCityId(cityId);
        Log.i(TAG, cityId + date);
        weatherInfo.setDate(date);
        weatherInfo.setTemperature(temperature);
        weatherInfo.save();
    }
}
