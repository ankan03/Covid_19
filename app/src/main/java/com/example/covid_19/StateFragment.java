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



public class StateFragment extends Fragment
        implements LoaderCallbacks<List<StateData>> {

    private Context mcontext;
    private static final String LOG_TAG = StateFragment.class.getName();
    private TextView mEmptyStateTextView;
    View loadingIndicator_state;


    /** URL for earthquake data from the USGS dataset */
    private static final String STATE_USGS_REQUEST_URL =
            "https://api.covid19india.org/v2/state_district_wise.json";

    private static final int STATE_LOADER_ID = 2;

    /** Adapter for the list of earthquakes */
    private StateAdapter mAdapter;



    public StateFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView_state = inflater.inflate(R.layout.state_fragment, container, false);

        Log.e(LOG_TAG,"TEST:StateFragment onCreate() called");


        mEmptyStateTextView = (TextView) rootView_state.findViewById(R.id.empty_view_state);
        ListView stateListView = (ListView) rootView_state.findViewById(R.id.list_state);

        mEmptyStateTextView = (TextView) rootView_state.findViewById(R.id.empty_view_state);
        //mEmptyStateTextView.setText(R.string.no_earthquakes);
        stateListView.setEmptyView(mEmptyStateTextView);

        mAdapter = new StateAdapter(getActivity(), new ArrayList<StateData>());

        stateListView.setAdapter(mAdapter);
        loadingIndicator_state = rootView_state.findViewById(R.id.loading_indicator_state);


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
            loaderManager.initLoader(STATE_LOADER_ID, null,  this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = rootView_state.findViewById(R.id.loading_indicator_state);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }


        return rootView_state;
    }

    @Override
    public Loader<List<StateData>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        Log.i(LOG_TAG,"TEST: onCreateLoader() called ...");
        return new StateLoader(getActivity(), STATE_USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<StateData>> loader, List<StateData> stateDataList) {
        // Clear the adapter of previous earthquake data
        //mEmptyStateTextView.setText(R.string.no_earthquakes);

        //View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator_state.setVisibility(View.GONE);

        // Set empty state text to display "No earthquakes found."
        mEmptyStateTextView.setText(R.string.unable_to_load);

        Log.i(LOG_TAG,"TEST: onLoadFinished() called ...");
        mAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (stateDataList != null && !stateDataList.isEmpty()) {
            mAdapter.addAll(stateDataList);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<StateData>> loader) {
        // Loader reset, so we can clear out our existing data.
        Log.i(LOG_TAG,"TEST: onLoaderReset() called ...");
        mAdapter.clear();
    }



}
