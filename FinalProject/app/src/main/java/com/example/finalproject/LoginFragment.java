package com.example.finalproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.finalproject.util.DataBaseUtil;
import com.example.finalproject.util.HttpUtil;
import com.example.finalproject.util.UserManage;

import java.io.IOException;

import kotlin.math.UMathKt;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class LoginFragment extends Fragment {

    private static final String TAG = "LoginFragment";
    EditText userNameEditText;

    EditText passwordEditText;

    Button loginButton;

    Button registerButton;

    CheckBox rememberPassword;

    CheckBox autoLogin;
    private ImageView bingPicImg;

    public LoginFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated");

        String storeUserName = UserManage.getInstance().getUserName(getActivity());
        String storePassword = UserManage.getInstance().getPassword(getActivity());
        bingPicImg = view.findViewById(R.id.bing_pic_img);
        userNameEditText = view.findViewById(R.id.userName);
        passwordEditText = view.findViewById(R.id.password);
        loginButton = view.findViewById(R.id.login);
        registerButton = view.findViewById(R.id.register);
        rememberPassword = view.findViewById(R.id.rememberPassword);
        autoLogin = view.findViewById(R.id.autoLogin);
        rememberPassword.setChecked(false);
        autoLogin.setChecked(false);

        userNameEditText.setText(storeUserName);
        Log.i(TAG, "password" + storePassword);
        if (storePassword != null && !TextUtils.isEmpty(storePassword)) {
            Log.i(TAG, "step1");
            passwordEditText.setText(storePassword);
            rememberPassword.setChecked(true);
        }

        /**
         * 自动登录CheckBox 事件监听
         * */
        autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    rememberPassword.setChecked(true);
                }
            }
        });

        /**
         * 记住密码CheckBox 事件监听
         */
        rememberPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!isChecked) {
                    autoLogin.setChecked(false);
                }
            }
        });

        /**
         * 登录事件
         */
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = userNameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (DataBaseUtil.getInstance().Login(userName, password)) {
                    String isAutoLogin = "false";
                    if (autoLogin.isChecked()) {
                        isAutoLogin = "true";
                    } else if (!rememberPassword.isChecked()) {
                        password = "";
                    }
                    UserManage.getInstance().saveUserInfo(getActivity(), userName, password,
                            isAutoLogin);
                    ((SettingsFragment) (LoginFragment.this.getParentFragment())).gotoSub();
                } else {
                    Toast.makeText(getActivity(), "用户名密码不正确", Toast.LENGTH_LONG).show();
                }
            }
        });

        /**
         * 注册事件
         */
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((SettingsFragment) (LoginFragment.this.getParentFragment())).gotoRegister();
            }
        });
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(getActivity());
        String bingPic = preferences.getString("bing_pic", null);
        if (bingPic != null) {
            Glide.with(getActivity()).load(bingPic).into(bingPicImg);
        } else {
            loadBingPic();
        }
    }

    /**
     * 加载必应每日一图
     */
    private void loadBingPic() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences
                        (getActivity()).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(getActivity()).load(bingPic).into(bingPicImg);
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });
    }
}
