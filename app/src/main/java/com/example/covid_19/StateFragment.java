package com.example.covid_19;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

    



    private static final String STATE_USGS_REQUEST_URL =
            "https://api.covid19india.org/v2/state_district_wise.json";

    private static final int STATE_LOADER_ID = 2;


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


        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.heading_state, stateListView, false);
        stateListView.addHeaderView(header, null, false);


        mEmptyStateTextView = (TextView) rootView_state.findViewById(R.id.empty_view_state);

        stateListView.setEmptyView(mEmptyStateTextView);

        mAdapter = new StateAdapter(getActivity(), new ArrayList<StateData>());

        stateListView.setAdapter(mAdapter);
        loadingIndicator_state = rootView_state.findViewById(R.id.loading_indicator_state);

        Log.i(LOG_TAG,"TEST: calling initLoader() ...");

        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();


        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getActivity().getLoaderManager();


            loaderManager.initLoader(STATE_LOADER_ID, null,  this);
        } else {

            View loadingIndicator = rootView_state.findViewById(R.id.loading_indicator_state);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }


        return rootView_state;
    }

    @Override
    public Loader<List<StateData>> onCreateLoader(int i, Bundle bundle) {

        Log.i(LOG_TAG,"TEST: onCreateLoader() called ...");
        return new StateLoader(getActivity(), STATE_USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<StateData>> loader, List<StateData> stateDataList) {
        loadingIndicator_state.setVisibility(View.GONE);

        mEmptyStateTextView.setText(R.string.unable_to_load);

        Log.i(LOG_TAG,"TEST: onLoadFinished() called ...");
        mAdapter.clear();

        if (stateDataList != null && !stateDataList.isEmpty()) {
            mAdapter.addAll(stateDataList);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<StateData>> loader) {
        Log.i(LOG_TAG,"TEST: onLoaderReset() called ...");
        mAdapter.clear();
    }
}




//    SharedPreferences receving_response = (SharedPreferences) getActivity().getSharedPreferences("preference",Context.MODE_PRIVATE);
//    String state_code = receving_response.getString("state_code",null);