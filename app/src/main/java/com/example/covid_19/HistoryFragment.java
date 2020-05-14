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


public class HistoryFragment extends Fragment
        implements LoaderCallbacks<List<HistoryData>> {

    private static final String LOG_TAG = HistoryFragment.class.getName();
    private static final String HISTORY_USGS_REQUEST_URL =
            "https://api.covid19india.org/states_daily.json";
    private static final int HISTORY_LOADER_ID = 3;
    View loadingIndicator_history;
    private Context mcontext;
    private TextView mEmptyStateTextView;
    private HistoryAdapter mAdapter;


    public HistoryFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView_history = inflater.inflate(R.layout.history_fragment, container, false);

        Log.e(LOG_TAG, "TEST:StateFragment onCreate() called");


        mEmptyStateTextView = (TextView) rootView_history.findViewById(R.id.empty_view_history);
        ListView historyListView = (ListView) rootView_history.findViewById(R.id.list_history);


        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.heading_history, historyListView, false);
        historyListView.addHeaderView(header, null, false);

        mEmptyStateTextView = (TextView) rootView_history.findViewById(R.id.empty_view_history);
        //mEmptyStateTextView.setText(R.string.no_earthquakes);
        historyListView.setEmptyView(mEmptyStateTextView);

        mAdapter = new HistoryAdapter(getActivity(), new ArrayList<HistoryData>());

        historyListView.setAdapter(mAdapter);
        loadingIndicator_history = rootView_history.findViewById(R.id.loading_indicator_history);

        Log.i(LOG_TAG, "TEST: calling initLoader() ...");

        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getActivity().getLoaderManager();

            loaderManager.initLoader(HISTORY_LOADER_ID, null, this);
        } else {

            View loadingIndicator = rootView_history.findViewById(R.id.loading_indicator_history);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }


        return rootView_history;
    }

    @Override
    public Loader<List<HistoryData>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        Log.i(LOG_TAG, "TEST: onCreateLoader() called ...");
        return new HistoryLoader(getActivity(), HISTORY_USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<HistoryData>> loader, List<HistoryData> historyDataList) {
        // Clear the adapter of previous earthquake data

        loadingIndicator_history.setVisibility(View.GONE);

        // Set empty state text to display "No earthquakes found."
        mEmptyStateTextView.setText(R.string.unable_to_load);

        Log.i(LOG_TAG, "TEST: onLoadFinished() called ...");
        mAdapter.clear();

        // If there is a valid list of {@link History Fragment}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (historyDataList != null && !historyDataList.isEmpty()) {
            mAdapter.addAll(historyDataList);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<HistoryData>> loader) {
        // Loader reset, so we can clear out our existing data.
        Log.i(LOG_TAG, "TEST: onLoaderReset() called ...");
        mAdapter.clear();
    }

}