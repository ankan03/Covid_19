package com.example.covid_19;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.LoaderManager.LoaderCallbacks;

public class CountryFragment extends Fragment
        implements LoaderCallbacks<List<WorldData>> {

    private Context mcontext;
    private static final String LOG_TAG = CountryFragment.class.getName();
    private TextView mEmptyStateTextView;
    View loadingIndicator;


    private static final String USGS_REQUEST_URL =
            "https://disease.sh/v2/countries";

    private static final int EARTHQUAKE_LOADER_ID = 1;

    private CountryAdapter mAdapter;


    public CountryFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.country_fragment, container, false);

        Log.e(LOG_TAG, "TEST:Main Activity onCreate() called");


        mEmptyStateTextView = (TextView) rootView.findViewById(R.id.empty_view);
        ListView countryListView = (ListView) rootView.findViewById(R.id.list);


        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.heading_country, countryListView, false);
        countryListView.addHeaderView(header, null, false);

        mEmptyStateTextView = (TextView) rootView.findViewById(R.id.empty_view);

        countryListView.setEmptyView(mEmptyStateTextView);

        mAdapter = new CountryAdapter(getActivity(), new ArrayList<WorldData>());

        countryListView.setAdapter(mAdapter);
        loadingIndicator = rootView.findViewById(R.id.loading_indicator);


        Log.i(LOG_TAG, "TEST: calling initLoader() ...");

        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();


        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getActivity().getLoaderManager();


            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
        } else {

            View loadingIndicator = rootView.findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }


        return rootView;
    }

    @Override
    public Loader<List<WorldData>> onCreateLoader(int i, Bundle bundle) {

        Log.i(LOG_TAG, "TEST: onCreateLoader() called ...");
        return new CountryLoader(getActivity(), USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<WorldData>> loader, List<WorldData> worldDataList) {

        loadingIndicator.setVisibility(View.GONE);

        mEmptyStateTextView.setText(R.string.unable_to_load);

        Log.i(LOG_TAG, "TEST: onLoadFinished() called ...");
        mAdapter.clear();


        if (worldDataList != null && !worldDataList.isEmpty()) {
            mAdapter.addAll(worldDataList);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<WorldData>> loader) {
        Log.i(LOG_TAG, "TEST: onLoaderReset() called ...");
        mAdapter.clear();
    }


}