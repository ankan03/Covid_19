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

import android.app.LoaderManager.LoaderCallbacks;


public class DistrictFragment extends Fragment
        implements LoaderCallbacks<List<DistrictData>> {

    private static final String LOG_TAG = DistrictFragment.class.getName();
    private static final String DISTRICT_REQUEST_URL =
            "https://api.covid19india.org/v2/state_district_wise.json";
    private static final int DISTRICT_LOADER_ID = 2;
    public static List<DistrictData> districtList = new ArrayList<>();
    View loadingIndicator_district;
    EditText edtSearch;
    private String individualDistrict, individualState, individualStateCode;
    private Context mcontext;
    private TextView mEmptyDistrictTextView;
    private DistrictAdapter mAdapter;
    private String individualDistrictConfirmed, individualDistrictActive, individualDistrictRecover, individualDistrictDeath, individualDistrictConfirmedToday, individualDistrictRecoverToday, individualDistrictDeathToday;

    public DistrictFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView_district = inflater.inflate(R.layout.district_fragment, container, false);
        edtSearch = (EditText) rootView_district.findViewById(R.id.edtSearch_district);

        Log.e(LOG_TAG, "TEST:DistrictFragment onCreate() called");


        mEmptyDistrictTextView = (TextView) rootView_district.findViewById(R.id.empty_view_district);
        ListView districtListView = (ListView) rootView_district.findViewById(R.id.list_district);


        districtListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                DistrictData currentDistrict = (DistrictData) mAdapter.getItem(position - 1);

                //Uri NewsUri = Uri.parse(currentNews.getUrl());

                individualState = currentDistrict.getState();
                individualDistrict = currentDistrict.getDistrict();
                individualStateCode = currentDistrict.getStatecode();


                individualDistrictConfirmed = currentDistrict.getConfirmed();
                individualDistrictConfirmedToday = currentDistrict.getTodayConfirmed();
                individualDistrictRecover = currentDistrict.getRecovered();
                individualDistrictRecoverToday = currentDistrict.getTodayRecovered();
                individualDistrictDeath = currentDistrict.getDeceased();
                individualDistrictDeathToday = currentDistrict.getTodayDeceased();
                individualDistrictActive = currentDistrict.getActive();


//                Intent myIntent = new Intent(getActivity(), cActivity.class);
//                getActivity().startActivity(myIntent);

//                Intent intent = new Intent(getActivity(), cActivity.class);
//                intent.putExtra("REQUEST_URL", "https://api.covid19api.com/dayone/country/"+individualCountry);
//                startActivity(intent);

                Intent intent = new Intent(getActivity(), IndividualDistrictHistoryActivity.class);


                // Bundle bundle = new Bundle();
                //bundle.putString("key1","value1");

                // intent.putExtras(bundle);
// Launch intent


                intent.putExtra("district_name", individualDistrict);
                intent.putExtra("state_name", individualState);
                intent.putExtra("state_code", individualStateCode);


                intent.putExtra("individual_confirmed_district", individualDistrictConfirmed);
                intent.putExtra("individual_active_district", individualDistrictActive);
                intent.putExtra("individual_recover_district", individualDistrictRecover);
                intent.putExtra("individual_death_district", individualDistrictDeath);
                intent.putExtra("individual_today_confirmed_district", individualDistrictConfirmedToday);
                intent.putExtra("individual_today_recover_district", individualDistrictRecoverToday);
                intent.putExtra("individual_today_death_district", individualDistrictDeathToday);


                startActivity(intent);


//                Intent intent = new Intent().setClassName(getActivity(), "com.example.covid_19.cActivity");
//                intent.putExtra("REQUEST_URL", "https://api.covid19api.com/dayone/country/"+individualCountry);
//                getActivity().startActivity(intent);

//                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, NewsUri);
//
//                startActivity(websiteIntent);
            }
        });


        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.heading_district, districtListView, false);
        districtListView.addHeaderView(header, null, false);


//        edtSearch = (EditText)header.findViewById(R.id.edtSearch_district);

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


        mEmptyDistrictTextView = (TextView) rootView_district.findViewById(R.id.empty_view_district);

        districtListView.setEmptyView(mEmptyDistrictTextView);

        mAdapter = new DistrictAdapter(getActivity(), new ArrayList<DistrictData>(districtList));

        districtListView.setAdapter(mAdapter);
        loadingIndicator_district = rootView_district.findViewById(R.id.loading_indicator_district);

        Log.i(LOG_TAG, "TEST: calling initLoader() ...");

        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();


        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getActivity().getLoaderManager();


            loaderManager.initLoader(DISTRICT_LOADER_ID, null, this);
        } else {

            View loadingIndicator = rootView_district.findViewById(R.id.loading_indicator_district);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyDistrictTextView.setText(R.string.no_internet_connection);
        }


        return rootView_district;
    }

    @Override
    public Loader<List<DistrictData>> onCreateLoader(int i, Bundle bundle) {

        Log.i(LOG_TAG, "TEST: onCreateLoader() called ...");
        mAdapter.clear();
        return new DistrictLoader(getActivity(), DISTRICT_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<DistrictData>> loader, List<DistrictData> districtDataList) {


        districtList = districtDataList;

        loadingIndicator_district.setVisibility(View.GONE);

        mEmptyDistrictTextView.setText(R.string.unable_to_load);

        Log.i(LOG_TAG, "TEST: onLoadFinished() called ...");
//        mAdapter.clear();

        if (mAdapter.isEmpty() && districtDataList != null && !districtDataList.isEmpty()) {
            mAdapter.addAll(districtList);
//            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<DistrictData>> loader) {
        Log.i(LOG_TAG, "TEST: onLoaderReset() called ...");
        mAdapter.clear();
    }
}


//    SharedPreferences receving_response = (SharedPreferences) getActivity().getSharedPreferences("preference",Context.MODE_PRIVATE);
//    String district_code = receving_response.getString("district_code",null);