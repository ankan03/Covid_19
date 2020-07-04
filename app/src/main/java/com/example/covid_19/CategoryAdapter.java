package com.example.covid_19;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CategoryAdapter extends FragmentPagerAdapter {

    private Context mContext;


    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new CountryFragment();
        } else if (position == 1) {
            return new StateFragment();
        } else if (position == 2) {
            return new DistrictFragment();
        } else {
            return new NewsFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_world);
        } else if (position == 1) {
            return mContext.getString(R.string.category_state);
        } else if (position == 2) {
            return mContext.getString(R.string.category_district);
        } else {
            return mContext.getString(R.string.category_news);
        }
    }

}
