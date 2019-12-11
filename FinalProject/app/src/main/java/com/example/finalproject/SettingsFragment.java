package com.example.finalproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.finalproject.util.UserManage;


public class SettingsFragment extends Fragment {

    private static final String TAG = "SettingsFragment";

    FrameLayout frameLayout;
    LoginFragment loginFragment;
    RegisterFragment registerFragment;
    SubSettingsFragment subSettingsFragment;

    public SettingsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        frameLayout = (FrameLayout) view.findViewById(R.id.settingsFrameLayout);

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        loginFragment = new LoginFragment();
        registerFragment = new RegisterFragment();
        subSettingsFragment = new SubSettingsFragment();

        fragmentTransaction.add(R.id.settingsFrameLayout, loginFragment, "login").show(loginFragment);
        fragmentTransaction.add(R.id.settingsFrameLayout, registerFragment, "register").hide(registerFragment);
        fragmentTransaction.add(R.id.settingsFrameLayout, subSettingsFragment, "subSettings").hide(subSettingsFragment);

        fragmentTransaction.commit();

        if (UserManage.getInstance().autoLogin(getActivity())) {
            Log.i(TAG, "autologin");
            gotoSub();
        }
    }

    public void gotoRegister() {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.show(registerFragment).hide(loginFragment).commit();
    }

    public void gotoLogin() {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.show(loginFragment).hide(registerFragment).commit();
    }

    public void gotoSub() {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.show(subSettingsFragment).hide(loginFragment).commit();
    }

    public void logout() {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.show(loginFragment).hide(subSettingsFragment).commit();
    }
}
