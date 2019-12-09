package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.finalproject.Weather.RecommendFragment;
import com.example.finalproject.util.UserManage;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private MenuItem menuItem;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_weather:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.navigation_history:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.navigation_map:
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.navigation_settings:
                    viewPager.setCurrentItem(3);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        final BottomNavigationView bottomNavigationView =
                (BottomNavigationView) findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {


            private String[] title = {"天气", "历史", "地图", "设置"};

            @NonNull
            @Override
            public Fragment getItem(int position) {
                Fragment fragment;
                switch (position) {
                    case 0:
                        fragment = new WeatherFragment();
                        return fragment;
                    case 1:
                        fragment = new HistoryFragment();
                        return fragment;
                    case 2:
                        fragment = new MapFragment();
                        return fragment;
                    case 3:
                        if (UserManage.getInstance().autoLogin(MainActivity.this)) {
                            fragment = new SettingsFragment();
                        } else {
                            fragment = new LoginFragment();
                        }
                        return fragment;
                }
                return null;
            }

            @Override
            public int getCount() {
                return title.length;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        });
    }
}
