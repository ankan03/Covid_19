package com.example.covid_19;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;


public class StateLoader extends AsyncTaskLoader<List<StateData>> {


    private static final String LOG_TAG = StateLoader.class.getName();
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
    @Override
    public List<StateData> loadInBackground() {
        Log.i(LOG_TAG,"TEST: loadInBackground() called ...");
        if (mUrl == null) {
            return null;
        }

        List<StateData> stateList = StateQueryUtils.fetchStateData(mUrl);
        return stateList;
    }
}

