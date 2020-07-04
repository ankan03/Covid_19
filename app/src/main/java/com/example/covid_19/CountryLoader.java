package com.example.covid_19;


import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class CountryLoader extends AsyncTaskLoader<List<CountryData>> {

    private static final String LOG_TAG = CountryLoader.class.getName();

    private String mUrl;

    public CountryLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }


    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "TEST: onStartLoading() called ...");
        forceLoad();

    }

    @Override
    public List<CountryData> loadInBackground() {
        Log.i(LOG_TAG, "TEST: loadInBackground() called ...");
        if (mUrl == null) {
            return null;
        }

        List<CountryData> countryList = QueryUtilsCountry.fetchCovid19Data(mUrl);
        return countryList;
    }
}