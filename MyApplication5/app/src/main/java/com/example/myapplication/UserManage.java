package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class UserManage {
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

    public void saveUserInfo(Context context, String username, String password) {
        SharedPreferences sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("USER_NAME", username);
        editor.putString("PASSWORD", password);
        editor.commit();
    }

    public UserInfo getUserInfo(Context context) {
        SharedPreferences sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserNmae(sp.getString("USER_NAME", ""));
        userInfo.setPassword(sp.getString("PASSWORD", ""));
        return userInfo;
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
        editor.commit();
    }
}
