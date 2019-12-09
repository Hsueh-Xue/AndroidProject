package com.example.finalproject.Weather;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.finalproject.R;
import com.example.finalproject.util.QRCodeUtil;
import com.google.zxing.qrcode.encoder.QRCode;

public class RecommendFragment extends Fragment {
    private static final String TAG = "RecommendFragment";

    public RecommendFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        return inflater.inflate(R.layout.fragment_recommend, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText editText = (EditText) view.findViewById(R.id.editText);
        Button button = (Button) view.findViewById(R.id.button);
        final ImageView imageView = (ImageView) view.findViewById(R.id.image);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String URL = editText.getText().toString();
                Log.i(TAG, URL);
                Bitmap bitmap = QRCodeUtil.createQRCodeBitmap(URL, 200, 200, "UTF-8", "H", "1",
                        Color.BLACK, Color.WHITE);
                imageView.setImageBitmap(bitmap);
            }
        });
    }
}
