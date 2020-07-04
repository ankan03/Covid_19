package com.example.covid_19;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CategoryAdapter2 extends FragmentPagerAdapter {

    private Context mContext;


    public CategoryAdapter2(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new SymtomsFragment();
        } else if (position == 1) {
            return new TipsFragment();
        } else if (position == 2) {
            return new FaqFragment();
        } else {
            return new ContractFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Symtoms";
        } else if (position == 1) {
            return "Tips";
        } else if (position == 2) {
            return "FAQ";
        } else {
            return "Contract";
        }
    }

}
