package com.example.covid_19;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.List;

public class StateLoader extends AsyncTaskLoader<List<StateData>> {

    /** Tag for log messages */
    private static final String LOG_TAG = StateLoader.class.getName();

    /** Query URL */
    private String mUrl;

    public StateLoader(Context context, String url) {
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
    public List<StateData> loadInBackground() {
        Log.i(LOG_TAG,"TEST: loadInBackground() called ...");
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<StateData> stateList = QueryUtilsState.fetchStateData(mUrl);
        return stateList;
    }
}
