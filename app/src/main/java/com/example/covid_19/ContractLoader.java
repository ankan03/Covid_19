package com.example.covid_19;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class ContractLoader extends AsyncTaskLoader<List<ContractData>> {


    private static final String LOG_TAG = ContractLoader.class.getName();

    private String mUrl;

    public ContractLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "TEST: onStartLoading() called ...");
        forceLoad();

    }

    @Override
    public List<ContractData> loadInBackground() {
        Log.i(LOG_TAG, "TEST: loadInBackground() called ...");
        if (mUrl == null) {
            return null;
        }

        List<ContractData> contractList = ContractQueryUtils.fetchContractData(mUrl);
        return contractList;
    }
}

