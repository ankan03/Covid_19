package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

public class MainActivity2 extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager2);
        Log.e(LOG_TAG, "TEST: CategoryAdapter is going to callcalled");


        CategoryAdapter2 adapter = new CategoryAdapter2(this, getSupportFragmentManager());
        Log.e(LOG_TAG, "TEST: CategoryAdapter called");


        viewPager.setAdapter(adapter);
        Log.e(LOG_TAG, "TEST: Adapter is set to viewPager");


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs2);
        tabLayout.setupWithViewPager(viewPager);
    }
}