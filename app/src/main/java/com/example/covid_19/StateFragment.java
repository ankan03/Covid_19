package com.example.covid_19;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;


public class StateFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<List<StateData>> {

    private static final String STATE_REQUEST_URL =
            "https://api.covid19india.org/data.json";
    private Context mcontext;
    private static final String LOG_TAG = StateFragment.class.getName();
    private TextView mEmptyStateTextView;
    View loadingIndicator_state;
    private static final int STATE_LOADER_ID = 7;
    public static List<StateData> stateList = new ArrayList<>();
    EditText edtSearch;
    private StateAdapter mAdapter;
    private String individualStateCode, individualStateName;
    private String individualStateConfirmed, individualStateActive, individualStateRecover, individualStateDeath, individualStateConfirmedToday, individualStateRecoverToday, individualStateDeathToday;

    public StateFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView_state = inflater.inflate(R.layout.state_fragment, container, false);

        edtSearch = (EditText) rootView_state.findViewById(R.id.edtSearch_state);

        Log.e(LOG_TAG, "TEST:StateFragment onCreate() called");


        mEmptyStateTextView = (TextView) rootView_state.findViewById(R.id.empty_view_state);
        ListView stateListView = (ListView) rootView_state.findViewById(R.id.list_state);


        stateListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                StateData currentState = (StateData) mAdapter.getItem(position - 1);

                individualStateCode = currentState.getStatecode();
                individualStateName = currentState.getState();


                individualStateConfirmed = currentState.getConfirmed();
                individualStateRecover = currentState.getRecovered();
                individualStateDeath = currentState.getDeaths();
                individualStateActive = currentState.getActive();
                individualStateConfirmedToday = currentState.getDeltaconfirmed();
                individualStateRecoverToday = currentState.getDeltarecovered();
                individualStateDeathToday = currentState.getDeltadeaths();


                Intent intent = new Intent(getActivity(), IndividualStateHistoryActivity.class);

                intent.putExtra("state_code", individualStateCode);
                intent.putExtra("state_name", individualStateName);


                intent.putExtra("individual_confirmed_state", individualStateConfirmed);
                intent.putExtra("individual_active_state", individualStateActive);
                intent.putExtra("individual_recover_state", individualStateRecover);
                intent.putExtra("individual_death_state", individualStateDeath);
                intent.putExtra("individual_today_confirmed_state", individualStateConfirmedToday);
                intent.putExtra("individual_today_recover_state", individualStateRecoverToday);
                intent.putExtra("individual_today_death_state", individualStateDeathToday);


                startActivity(intent);

            }
        });


        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.state_heading, stateListView, false);
        stateListView.addHeaderView(header, null, false);


//        edtSearch = (EditText)header.findViewById(R.id.edtSearch_state);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                mAdapter.getFilter().filter(s);
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mEmptyStateTextView = (TextView) rootView_state.findViewById(R.id.empty_view_state);

        stateListView.setEmptyView(mEmptyStateTextView);

        mAdapter = new StateAdapter(getActivity(), new ArrayList<StateData>(stateList));

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

        Log.i(LOG_TAG, "TEST: onCreateLoader() called ...");
        mAdapter.clear();
//        edtSearch.setVisibility(View.VISIBLE);
        return new StateLoader(getActivity(), STATE_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<StateData>> loader, List<StateData> stateDataList) {

        if (stateDataList != null) {
            stateList = stateDataList;
        }
        loadingIndicator_state.setVisibility(View.GONE);
        edtSearch.setVisibility(View.VISIBLE);
        mEmptyStateTextView.setText(R.string.unable_to_load);

        Log.i(LOG_TAG, "TEST: onLoadFinished() called ...");
//        mAdapter.clear();

        if (mAdapter.isEmpty() && stateDataList != null && !stateDataList.isEmpty()) {
            mAdapter.addAll(stateList);
//            edtSearch.setVisibility(View.VISIBLE);
//            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<StateData>> loader) {
        Log.i(LOG_TAG,"TEST: onLoaderReset() called ...");
        mAdapter.clear();
    }
}

