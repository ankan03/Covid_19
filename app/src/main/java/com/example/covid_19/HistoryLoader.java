package com.example.covid_19;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;


public class HistoryLoader extends AsyncTaskLoader<List<HistoryData>> {

    /**
     * Tag for log messages
     */
    private static final String LOG_TAG = HistoryLoader.class.getName();

    /**
     * Query URL
     */
    private String mUrl;


    public HistoryLoader(Context context, String url) {
        super(context);
        mUrl = url;


    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "TEST: onStartLoading() called ...");
        forceLoad();
    }


    @Override
    public List<HistoryData> loadInBackground() {
        Log.i(LOG_TAG, "TEST: loadInBackground() called ...");
        if (mUrl == null) {
            return null;
        }


        List<HistoryData> historyDataList = QueryUtilsHistory.fetchHistoryData(mUrl);
        return historyDataList;
    }
}
