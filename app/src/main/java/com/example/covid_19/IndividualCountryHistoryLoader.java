package com.example.covid_19;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class IndividualCountryHistoryLoader extends AsyncTaskLoader<List<IndividualCountryHistoryData>> {

    private static final String LOG_TAG = IndividualCountryHistoryLoader.class.getName();

    private String mUrl;

    public IndividualCountryHistoryLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "TEST: onStartLoading() called ...");
        forceLoad();

    }

    @Override
    public List<IndividualCountryHistoryData> loadInBackground() {
        Log.i(LOG_TAG, "TEST: loadInBackground() called ...");
        if (mUrl == null) {
            return null;
        } else {
            List<IndividualCountryHistoryData> countryList = IndividualCountryHistoryQueryUtils.fetchCovid19DataForIndividualCountry(mUrl);
            return countryList;
        }

    }
}