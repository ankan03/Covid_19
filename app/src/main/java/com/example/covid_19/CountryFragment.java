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


    /** URL for earthquake data from the USGS dataset */
    private static final String USGS_REQUEST_URL =
            "https://disease.sh/v2/countries";
    //"https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=2";

    private static final int EARTHQUAKE_LOADER_ID = 1;

    /** Adapter for the list of earthquakes */
    private CountryAdapter mAdapter;



    public CountryFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.country_fragment, container, false);

        Log.e(LOG_TAG,"TEST:Main Activity onCreate() called");


        mEmptyStateTextView = (TextView) rootView.findViewById(R.id.empty_view);
        ListView countryListView = (ListView) rootView.findViewById(R.id.list);


        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.heading_country, countryListView, false);
        countryListView.addHeaderView(header, null, false);

        mEmptyStateTextView = (TextView) rootView.findViewById(R.id.empty_view);
        //mEmptyStateTextView.setText(R.string.no_earthquakes);
        countryListView.setEmptyView(mEmptyStateTextView);

        mAdapter = new CountryAdapter(getActivity(), new ArrayList<WorldData>());

        countryListView.setAdapter(mAdapter);
        loadingIndicator = rootView.findViewById(R.id.loading_indicator);


        Log.i(LOG_TAG,"TEST: calling initLoader() ...");

        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getActivity().getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null,  this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = rootView.findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }


        return rootView;
    }

    @Override
    public Loader<List<WorldData>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        Log.i(LOG_TAG,"TEST: onCreateLoader() called ...");
        return new CountryLoader(getActivity(), USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<WorldData>> loader, List<WorldData> worldDataList) {
        // Clear the adapter of previous earthquake data
        //mEmptyStateTextView.setText(R.string.no_earthquakes);

        //View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No earthquakes found."
        mEmptyStateTextView.setText(R.string.unable_to_load);

        Log.i(LOG_TAG,"TEST: onLoadFinished() called ...");
        mAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (worldDataList != null && !worldDataList.isEmpty()) {
            mAdapter.addAll(worldDataList);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<WorldData>> loader) {
        // Loader reset, so we can clear out our existing data.
        Log.i(LOG_TAG,"TEST: onLoaderReset() called ...");
        mAdapter.clear();
    }



}