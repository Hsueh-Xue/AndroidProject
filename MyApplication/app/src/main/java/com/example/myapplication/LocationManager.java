package com.example.myapplication;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;

import java.io.BufferedInputStream;

public class LocationManager implements AMapLocationListener {
    private ILocationCallBack callBack;

    public LocationManager() {

    }

    public void startLocation(Context context) {
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        AMapLocationClient mLocationClient = new AMapLocationClient(context);
        mLocationClient.setLocationListener(this);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy); //设置定位模式
        mLocationOption.setOnceLocation(true);
        mLocationOption.setOnceLocationLatest(true);
        mLocationOption.setNeedAddress(true);  //是否返回地址信息
        mLocationOption.setWifiActiveScan(false);
        mLocationOption.setMockEnable(false);
        mLocationOption.setLocationCacheEnable(false);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
    }

    public static boolean isLocationEnabled(Context context) {
        int locationMode;
        String locationProviders;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(),
                        Settings.Secure.LOCATION_MODE);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }
            return locationMode != Settings.Secure.LOCATION_MODE_OFF;
        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }
    }


    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation == null) {
            return;
        }

        if (aMapLocation.getErrorCode() == 0) {
            double lat = aMapLocation.getLatitude();//获取纬度
            double lgt = aMapLocation.getLongitude();//获取经度
            String country = aMapLocation.getCountry();//国家信息
            String province = aMapLocation.getProvince();//省信息
            String city = aMapLocation.getCity();//城市信息
            String district = aMapLocation.getDistrict();//城区信息
            String street = aMapLocation.getStreet();//街道信息

            if (callBack != null) {
                callBack.callBack(country + province + city + district + street,lat,lgt,aMapLocation);
            }
        }
    }

    /**
     * 自定义图标
     * @return
     */
    public MarkerOptions getMarkerOption(String str, double lat, double lgt) {
        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.coordinate));
        markerOptions.position(new LatLng(lat,lgt));
        markerOptions.title(str);
        markerOptions.snippet("纬度:" + lat + "   经度:" + lgt);
        markerOptions.period(100);
        return markerOptions;
    }

    public interface ILocationCallBack {
        void callBack(String str, double lat, double lgt, AMapLocation aMapLocation);
    }

    public void setLocationCallBack(ILocationCallBack callBack) {
        this.callBack = callBack;
    }
}
