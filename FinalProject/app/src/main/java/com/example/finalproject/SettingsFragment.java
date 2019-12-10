package com.example.finalproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.finalproject.util.UserManage;


public class SettingsFragment extends Fragment {

    private static final String TAG = "SettingsFragment";
    private Button nav_button;
    private TextView welcome;
    private EditText oldPassword;
    private EditText newPassword;
    private EditText prePassword;
    private Button changePasswordButton;
    private Button dayNumberButton;

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
        nav_button = view.findViewById(R.id.nav_button);
        welcome = view.findViewById(R.id.welcome);
        oldPassword = view.findViewById(R.id.old_password);
        newPassword = view.findViewById(R.id.new_password);
        prePassword = view.findViewById(R.id.pre_password);
        changePasswordButton = view.findViewById(R.id.change_password_button);
        dayNumberButton = view.findViewById(R.id.day_number);


        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = UserManage.getInstance().getUserName(getActivity());

            }
        });
    }
}
