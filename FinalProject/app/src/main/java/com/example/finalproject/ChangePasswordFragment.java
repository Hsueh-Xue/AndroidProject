package com.example.finalproject;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.finalproject.util.DataBaseUtil;
import com.example.finalproject.util.UserManage;


public class ChangePasswordFragment extends Fragment {
    private EditText oldPasswordEditText;
    private EditText newPasswordEditText;
    private EditText repPasswordEditText;
    private Button notarize;
    private Button cancel;

    public ChangePasswordFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_change_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        oldPasswordEditText = (EditText) view.findViewById(R.id.old_password);
        newPasswordEditText = (EditText) view.findViewById(R.id.new_password);
        repPasswordEditText = (EditText) view.findViewById(R.id.rep_password);
        notarize = (Button) view.findViewById(R.id.notarize);
        cancel = (Button) view.findViewById(R.id.cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((SettingsFragment)(ChangePasswordFragment.this.getParentFragment())).fromChangeToSub();
            }
        });

        notarize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPassword = oldPasswordEditText.getText().toString();
                String newPassword = newPasswordEditText.getText().toString();
                String repPassword = repPasswordEditText.getText().toString();
                if (!(repPassword.equals(newPassword))) {
                    Toast.makeText(getActivity(), "两次密码不一致", Toast.LENGTH_LONG).show();
                    return;
                }
                String userName = UserManage.getInstance().getUserName(getActivity());
                if (!DataBaseUtil.getInstance().changePassword(userName, oldPassword, newPassword)) {
                    Toast.makeText(getActivity(), "密码不正确", Toast.LENGTH_LONG).show();
                    return;
                }
                UserManage.getInstance().Destroy(getActivity());
            }
        });
    }
}
