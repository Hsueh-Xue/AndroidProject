package com.example.finalproject.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.finalproject.db.UserInfo;

public class UserManage {
    private static final String TAG = "UserManage";

    private static UserManage instance;

    private UserManage() {

    }

    public static UserManage getInstance() {
        if (instance == null) {
            instance = new UserManage();
        }
        return instance;
    }



    public static void setInstance(UserManage instance) {
        UserManage.instance = instance;
    }

    public void saveUserInfo(Context context, String username, String password, String autoLogin) {
        SharedPreferences sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("USER_NAME", username);
        editor.putString("PASSWORD", password);
        editor.putString("AUTO_LOGIN", autoLogin);
        editor.apply();
    }

    public UserInfo getUserInfo(Context context) {
        SharedPreferences sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(sp.getString("USER_NAME", ""));
        userInfo.setPassword(sp.getString("PASSWORD", ""));
        return userInfo;
    }

    public boolean autoLogin(Context context) {
        UserInfo userInfo = getUserInfo(context);
        SharedPreferences sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String autoLogin = sp.getString("AUTO_LOGIN", "");
        if (userInfo != null) {
            return (!TextUtils.isEmpty(userInfo.getPassword())) && (!TextUtils.isEmpty(userInfo.getPassword()) && (!TextUtils.isEmpty(autoLogin)));
        }
        return false;
    }

    public String getUserName(Context context) {
        UserInfo userInfo = getUserInfo(context);
        return userInfo.getUserName();
    }

    public String getPassword(Context context) {
        UserInfo userInfo = getUserInfo(context);
        return userInfo.getPassword();
    }

    public boolean hasUserInfo(Context context) {
        UserInfo userInfo = getUserInfo(context);
        if (userInfo != null) {
            return (!TextUtils.isEmpty(userInfo.getPassword())) && (!TextUtils.isEmpty(userInfo.getPassword()));
        }
        return false;
    }

    public void Destroy(Context context) {
        SharedPreferences sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("USER_NAME", null);
        editor.putString("PASSWORD", null);
        editor.putString("AUTO_LOGIN", null);
        editor.apply();
    }
}
