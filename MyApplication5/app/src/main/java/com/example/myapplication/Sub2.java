package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Sub2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);

        final EditText editText1 = (EditText) findViewById(R.id.username);
        final EditText editText2 = (EditText) findViewById(R.id.password);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editText1.getText().toString();
                String password = editText2.getText().toString();
                UserManage.getInstance().saveUserInfo(Sub2.this, username, password);
                Intent intent = new Intent(Sub2.this, Sub1.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
