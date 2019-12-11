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

import com.example.finalproject.util.UserManage;

public class SubSettingsFragment extends Fragment {
    private Button changePassword;
    private Button logoutButton;

    public SubSettingsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sub_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        changePassword = (Button) view.findViewById(R.id.change_password);
        logoutButton = (Button) view.findViewById(R.id.logout);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserManage.getInstance().Destroy(getActivity());
                ((SettingsFragment)(SubSettingsFragment.this.getParentFragment())).logout();
            }
        });
    }
}
