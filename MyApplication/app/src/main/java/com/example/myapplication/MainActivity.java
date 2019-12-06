package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.Marker;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MapView mapView;
    private AMap aMap;
    private LocationSource.OnLocationChangedListener mListener;
    private LocationManager locationManager;

    //    维度
    private double lat = 30.2910490000;
    //    精度
    private double lgt = 120.0109650000;
    //    地点名
    private String location = "杭州师范大学";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapView = (MapView) findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);

        setLocationMap();
    }

    //    设置地图定位属性
    private void setLocationMap() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        setLocationCallBack();

        aMap.setMapType(AMap.MAP_TYPE_NORMAL);

        aMap.setLocationSource(new LocationSource() {
            @Override
            public void activate(OnLocationChangedListener onLocationChangedListener) {
                mListener = onLocationChangedListener;
                locationManager.startLocation(MainActivity.this);
            }

            @Override
            public void deactivate() {
                mListener = null;
            }
        });
        aMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        //显示定位层并可触发，默认false
        aMap.setMyLocationEnabled(true);
    }


    private void setLocationCallBack() {
        locationManager = new LocationManager();

        //根据获取的经纬度，将地图移动到定位位置
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(new com.amap.api.maps2d.model.LatLng(lat
                , lgt)));
        //添加定位图标，并弹出标注弹框
        aMap.addMarker(locationManager.getMarkerOption(location, lat, lgt));
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();

        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();

        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mapView != null) {
            mapView.onDestroy();
        }
    }
}
