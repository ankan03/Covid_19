package com.example.covid_19;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;


public class IndividualStateHistoryLoader extends AsyncTaskLoader<List<IndividualStateHistoryData>> {

    private static final String LOG_TAG = IndividualStateHistoryLoader.class.getName();
    private String state_code;
    private String mUrl;

    public IndividualStateHistoryLoader(Context context, String url, String stateCode) {
        super(context);
        mUrl = url;
        state_code = stateCode;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "TEST: onStartLoading() called ...");
        forceLoad();

    }

    @Override
    public List<IndividualStateHistoryData> loadInBackground() {
        Log.i(LOG_TAG, "TEST: loadInBackground() called ...");
        if (mUrl == null) {
            return null;
        }


        List<IndividualStateHistoryData> stateList = null;

        stateList = IndividualStateHistoryQueryUtils.fetchCovid19DataForIndividualState(mUrl, state_code);

        return stateList;
    }
}

