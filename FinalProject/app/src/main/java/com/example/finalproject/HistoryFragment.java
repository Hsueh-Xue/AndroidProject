package com.example.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowId;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.example.finalproject.db.WeatherInfo;
import com.example.finalproject.util.DataBaseUtil;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;


public class HistoryFragment extends Fragment {
    private static final String TAG = "HistoryFragment";

    private Button button;

    private int number = 7;

    private List<WeatherInfo> weatherInfoList;

    private LineChartView  lineChartView;

    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();

    public HistoryFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        listView = view.findViewById(R.id.list_view);
//        adapter = new WeatherAdapter(getActivity(), R.layout.item_layout, weatherInfoList);
//        listView.setAdapter(adapter);

        lineChartView = view.findViewById(R.id.line_chart);

        button = view.findViewById(R.id.choose_button);
        final PopupMenu popupMenu = new PopupMenu(getActivity(), button);
        popupMenu.getMenuInflater().inflate(R.menu.day_choose, popupMenu.getMenu());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SharedPreferences.Editor editor =
                        PreferenceManager.getDefaultSharedPreferences
                                (getActivity()).edit();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.day1:
                                if (menuItem.isChecked()) menuItem.setChecked(false);
                                else menuItem.setChecked(true);
                                editor.putString("number", "1");
                                editor.apply();
                                break;
                            case R.id.day2:
                                if (menuItem.isChecked()) menuItem.setChecked(false);
                                else menuItem.setChecked(true);
                                editor.putString("number", "2");
                                editor.apply();
                                break;
                            case R.id.day3:
                                if (menuItem.isChecked()) menuItem.setChecked(false);
                                else menuItem.setChecked(true);
                                editor.putString("number", "3");
                                editor.apply();
                                break;
                            case R.id.day4:
                                if (menuItem.isChecked()) menuItem.setChecked(false);
                                else menuItem.setChecked(true);
                                editor.putString("number", "4");
                                editor.apply();
                                break;
                            case R.id.day5:
                                if (menuItem.isChecked()) menuItem.setChecked(false);
                                else menuItem.setChecked(true);
                                editor.putString("number", "5");
                                editor.apply();
                                break;
                            case R.id.day6:
                                if (menuItem.isChecked()) menuItem.setChecked(false);
                                else menuItem.setChecked(true);
                                editor.putString("number", "6");
                                editor.apply();
                                break;
                            case R.id.day7:
                                if (menuItem.isChecked()) menuItem.setChecked(false);
                                else menuItem.setChecked(true);
                                editor.putString("number", "7");
                                editor.apply();
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
                popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
                    @Override
                    public void onDismiss(PopupMenu popupMenu) {
                        refresh();
                    }
                });
            }
        });

        refresh();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        refresh();
    }

    private void getData() {
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(getActivity());
        String cityId = preferences.getString("cityId", null);
        number = 7;
        String numberString = preferences.getString("number", null);
        if (numberString != null) {
            number = Integer.parseInt(numberString);
        }
        weatherInfoList  = DataBaseUtil.getInstance().selectWeather(cityId, number);
    }

    public void refresh() {
        getData();
        List<PointValue> pointValues = new ArrayList<>();
        String[] xData = new String[weatherInfoList.size()];

//        获取x的标注
        for (int i = 0; i < weatherInfoList.size(); ++i) {
            xData[i] = weatherInfoList.get(i).getDate();
        }

//        设置X 轴的显示
        for (int i = 0; i < xData.length; ++i) {
            mAxisXValues.add(new AxisValue(i).setLabel(xData[i]));
        }

//        获取坐标点
        for (int i = 0;  i < weatherInfoList.size(); ++i) {
            pointValues.add(new PointValue((float) i, Float.valueOf(weatherInfoList.get(i).getTemperature())));
        }


        Log.i(TAG, "Point" + pointValues.size());
        Line line = new Line(pointValues);
        line.setColor(ChartUtils.COLOR_BLUE);
        line.setShape(ValueShape.CIRCLE);
        line.setHasPoints(true);
        line.setHasLabels(true);

        ArrayList<Line> lines = new ArrayList<>();
        lines.add(line);

        LineChartData data = new LineChartData(lines);

        Axis axisX = new Axis();
        axisX.setName("日期");
        axisX.setValues(mAxisXValues);
        data.setAxisXBottom(axisX);

        Axis axisY = new Axis();
        axisY.setName("气温");
        data.setAxisYLeft(axisY);

        lineChartView.setZoomEnabled(true);
        lineChartView.setLineChartData(data);

    }
}
