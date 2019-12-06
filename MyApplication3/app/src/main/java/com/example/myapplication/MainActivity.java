package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = (EditText) findViewById(R.id.editText);
        final ImageView imageView = (ImageView) findViewById(R.id.image);
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String URL = editText.getText().toString();
                Bitmap bitmap = QRCodeUtil.createQRCodeBitmap(URL, 800, 800, "UTF-8", "H", "1",
                        Color.GREEN, Color.WHITE);
                imageView.setImageBitmap(bitmap);
            }
        });
    }
}
