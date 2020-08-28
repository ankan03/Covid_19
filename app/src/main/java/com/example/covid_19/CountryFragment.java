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

public class CountryFragment extends Fragment
        implements LoaderCallbacks<List<CountryData>> {

    private static final String COUNTRY_REQUEST_URL =
            "https://disease.sh/v2/countries";
    private Context mcontext;
    private static final String LOG_TAG = CountryFragment.class.getName();
    private TextView mEmptyStateTextView;
    View loadingIndicator;
    private static final int COUNTRY_LOADER_ID = 1;
    public static List<CountryData> countryList = new ArrayList<>();
    //    private Spinner dropdown;
    EditText edtSearch;
    private String individualCountry, individualCountryFlag;
    private CountryAdapter mAdapter;
    private String individualCountryConfirmed, individualCountryActive, individualCountryRecover, individualCountryDeath, individualCountryConfirmedToday, individualCountryRecoverToday, individualCountryDeathToday;


    public CountryFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.country_fragment, container, false);

        Log.e(LOG_TAG, "TEST:Main Activity onCreate() called");

        edtSearch = (EditText) rootView.findViewById(R.id.edtSearch_country);


        mEmptyStateTextView = (TextView) rootView.findViewById(R.id.empty_view);
        ListView countryListView = (ListView) rootView.findViewById(R.id.list);

        countryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                CountryData currentCountry = (CountryData) mAdapter.getItem(position - 1);

                //Uri NewsUri = Uri.parse(currentNews.getUrl());

                individualCountry = currentCountry.getCountry();
                individualCountryFlag = currentCountry.getFlag();

                individualCountryConfirmed = currentCountry.getCases();
                individualCountryConfirmedToday = currentCountry.getTodayCases();
                individualCountryRecover = currentCountry.getRecovered();
                individualCountryRecoverToday = currentCountry.getTodayRecovered();
                individualCountryDeath = currentCountry.getDeaths();
                individualCountryDeathToday = currentCountry.getTodayDeaths();
                individualCountryActive = currentCountry.getActive();
//                countryList = currentCountry.getCountryList();

//                Intent myIntent = new Intent(getActivity(), cActivity.class);
//                getActivity().startActivity(myIntent);

//                Intent intent = new Intent(getActivity(), cActivity.class);
//                intent.putExtra("REQUEST_URL", "https://api.covid19api.com/dayone/country/"+individualCountry);
//                startActivity(intent);

                Intent intent = new Intent(getActivity(), cActivity.class);


                // Bundle bundle = new Bundle();
                //bundle.putString("key1","value1");

                // intent.putExtras(bundle);
// Launch intent


                intent.putExtra("url", "https://api.covid19api.com/dayone/country/" + individualCountry);
                intent.putExtra("country_name", individualCountry);
                intent.putExtra("country_flag", individualCountryFlag);

                intent.putExtra("individual_confirmed_country", individualCountryConfirmed);
                intent.putExtra("individual_active_country", individualCountryActive);
                intent.putExtra("individual_recover_country", individualCountryRecover);
                intent.putExtra("individual_death_country", individualCountryDeath);
                intent.putExtra("individual_today_confirmed_country", individualCountryConfirmedToday);
                intent.putExtra("individual_today_recover_country", individualCountryRecoverToday);
                intent.putExtra("individual_today_death_country", individualCountryDeathToday);

                startActivity(intent);


//                Intent intent = new Intent().setClassName(getActivity(), "com.example.covid_19.cActivity");
//                intent.putExtra("REQUEST_URL", "https://api.covid19api.com/dayone/country/"+individualCountry);
//                getActivity().startActivity(intent);

//                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, NewsUri);
//
//                startActivity(websiteIntent);
            }
        });


        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.heading_country, countryListView, false);
        countryListView.addHeaderView(header, null, false);


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


//        dropdown = header.findViewById(R.id.spinner1);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item,countryList);
//        dropdown.setAdapter(adapter);

        mEmptyStateTextView = (TextView) rootView.findViewById(R.id.empty_view);

        countryListView.setEmptyView(mEmptyStateTextView);

//        mAdapter = new CountryAdapter(getActivity(), new ArrayList<CountryData>());
        mAdapter = new CountryAdapter(getActivity(), new ArrayList<CountryData>(countryList));

        countryListView.setAdapter(mAdapter);
        loadingIndicator = rootView.findViewById(R.id.loading_indicator);


        Log.i(LOG_TAG, "TEST: calling initLoader() ...");

        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();


        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getActivity().getLoaderManager();


            loaderManager.initLoader(COUNTRY_LOADER_ID, null, this);
        } else {

            View loadingIndicator = rootView.findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }



        return rootView;
    }

    @Override
    public Loader<List<CountryData>> onCreateLoader(int i, Bundle bundle) {

        Log.i(LOG_TAG, "TEST: onCreateLoader() called ...");
        mAdapter.clear();
//        edtSearch.setVisibility(View.VISIBLE);
        return new CountryLoader(getActivity(), COUNTRY_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<CountryData>> loader, List<CountryData> countryDataList) {

        if (countryDataList != null) {
            countryList = countryDataList;
        }
        loadingIndicator.setVisibility(View.GONE);
        edtSearch.setVisibility(View.VISIBLE);
        mEmptyStateTextView.setText(R.string.unable_to_load);

        Log.i(LOG_TAG, "TEST: onLoadFinished() called ...");
//        mAdapter.clear();


        if (mAdapter.isEmpty() && countryDataList != null && !countryDataList.isEmpty()) {
//            mAdapter.addAll(countryDataList);
            //  mAdapter.notifyDataSetChanged();
            mAdapter.addAll(countryList);


        }


    }

    @Override
    public void onLoaderReset(Loader<List<CountryData>> loader) {
        Log.i(LOG_TAG, "TEST: onLoaderReset() called ...");
        mAdapter.clear();
    }


}