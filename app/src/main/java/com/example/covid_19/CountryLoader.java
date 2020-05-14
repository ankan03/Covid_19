package com.example.covid_19;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class CountryLoader extends AsyncTaskLoader<List<WorldData>> {

    /** Tag for log messages */
    private static final String LOG_TAG = CountryLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link CountryLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public CountryLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG,"TEST: onStartLoading() called ...");
        forceLoad();

    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<WorldData> loadInBackground() {
        Log.i(LOG_TAG,"TEST: loadInBackground() called ...");
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<WorldData> countryList = QueryUtilsCountry.fetchCovid19Data(mUrl);
        return countryList;
    }
}
