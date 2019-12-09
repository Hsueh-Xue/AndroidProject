package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class MainActivity extends AppCompatActivity {

    private static final int GO_HOME = 0;
    private static final int GO_LOGIN = 1;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    Intent intent1 = new Intent(MainActivity.this, Sub1.class);
                    startActivity(intent1);
                    finish();
                    break;
                case GO_LOGIN:
                    Intent intent2 = new Intent(MainActivity.this, Sub2.class);
                    startActivity(intent2);
                    finish();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (UserManage.getInstance().hasUserInfo(this)) {
            mHandler.sendEmptyMessageDelayed(GO_HOME, 2000);
        } else {
            mHandler.sendEmptyMessageDelayed(GO_LOGIN, 2000);
        }
    }
}
