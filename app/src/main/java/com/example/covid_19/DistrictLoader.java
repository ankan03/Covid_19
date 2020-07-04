package com.example.covid_19;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class DistrictLoader extends AsyncTaskLoader<List<DistrictData>> {


    private static final String LOG_TAG = DistrictLoader.class.getName();
    private String mUrl;

    public DistrictLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "TEST: onStartLoading() called ...");
        forceLoad();
    }

    @Override
    public List<DistrictData> loadInBackground() {
        Log.i(LOG_TAG, "TEST: loadInBackground() called ...");
        if (mUrl == null) {
            return null;
        }

        List<DistrictData> districtList = QueryUtilsDistrict.fetchDistrictData(mUrl);
        return districtList;
    }
}
