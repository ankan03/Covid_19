package com.example.covid_19;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(LOG_TAG,"TEST:Covid19 Activity onCreate() called");
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        Log.e(LOG_TAG,"TEST: CategoryAdapter is going to callcalled");


        CategoryAdapter adapter = new CategoryAdapter(this, getSupportFragmentManager());
        Log.e(LOG_TAG,"TEST: CategoryAdapter called");


        viewPager.setAdapter(adapter);
        Log.e(LOG_TAG,"TEST: Adapter is set to viewPager");


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}