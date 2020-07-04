package com.example.covid_19;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;


public class IndividualDistrictHistoryLoader extends AsyncTaskLoader<List<IndividualDistrictHistoryData>> {

    private static final String LOG_TAG = IndividualDistrictHistoryLoader.class.getName();
    private String district_name, state_name;
    private String mUrl;

    public IndividualDistrictHistoryLoader(Context context, String url, String district, String state) {
        super(context);
        mUrl = url;
        district_name = district;
        state_name = state;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "TEST: onStartLoading() called ...");
        forceLoad();

    }

    @Override
    public List<IndividualDistrictHistoryData> loadInBackground() {
        Log.i(LOG_TAG, "TEST: loadInBackground() called ...");
        if (mUrl == null) {
            return null;
        } else {
            List<IndividualDistrictHistoryData> districtList = IndividualDistrictHistoryQueryUtils.fetchCovid19DataForIndividualDistrict(mUrl, district_name, state_name);
            return districtList;
        }
    }
}
