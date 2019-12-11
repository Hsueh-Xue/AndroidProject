package com.example.finalproject;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject.Weather.RecommendFragment;
import com.example.finalproject.db.UserInfo;
import com.example.finalproject.util.DataBaseUtil;
import com.example.finalproject.util.UserManage;


public class RegisterFragment extends Fragment {
    private EditText userNameEditText;
    private EditText passwordEditText;
    private Button registerButton;
    private Button cancelButton;

    public RegisterFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userNameEditText = (EditText) view.findViewById(R.id.userName);
        passwordEditText = (EditText) view.findViewById(R.id.password);
        registerButton = (Button) view.findViewById(R.id.registerButton);
        cancelButton = (Button) view.findViewById(R.id.cancel_button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userNameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (DataBaseUtil.getInstance().register(username, password)) {
                    ((SettingsFragment) (RegisterFragment.this.getParentFragment())).gotoLogin();
                } else {
                    Toast.makeText(getActivity(), "注册失败", Toast.LENGTH_LONG).show();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((SettingsFragment) (RegisterFragment.this.getParentFragment())).gotoLogin();
            }
        });
    }
}
