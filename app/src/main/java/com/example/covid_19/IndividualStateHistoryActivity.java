package com.example.covid_19;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.ConversationActions;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.request.Request;

import java.util.ArrayList;
import java.util.List;


import org.apache.http.HttpResponse;

import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.params.CoreProtocolPNames;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Response;


public class IndividualStateHistoryActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<IndividualStateHistoryData>> {


    private static final String LOG_TAG = IndividualStateHistoryActivity.class.getName();
    //Bundle bundle = getIntent().getExtras();
    //String INDIVIDUAL_COUNTRY_LIST_REQUEST_URL = url;
    //String requestUrl = getIntent().getStringExtra("REQUEST_URL");
//   private String INDIVIDUAL_COUNTRY_LIST_REQUEST_URL =i.getStringExtra("REQUEST_URL");
//    private String url="https://api.covid19api.com/dayone/country/india";
    private static final String INDIVIDUAL_STATE_REQUEST_URL = "https://api.covid19india.org/states_daily.json";//"https://covid-19-india-data-by-zt.p.rapidapi.com/GetIndiaAllHistoricalDataForState?statecode=KA";//"https://api.rootnet.in/covid19-in/stats/history";//"https://api.covid19india.org/states_daily.json";
    private static final int INDIVIDUAL_STATE_HISTORY_LOADER_ID = 6;


    //    mGameType = getIntent().getExtras().getBoolean("gameType");
    public static List<IndividualStateHistoryData> individualStateList = new ArrayList<>();
    PieChart pieChartAll, pieChartToday;
    TextView cases, recover, death, active, today_cases, today_recover, today_death;
    View loadingIndicator;
    EditText edtSearch;
    private String individual_StateConfirmed, individual_StateActive, individual_StateRecover, individual_StateDeath, individual_StateConfirmedToday, individual_StateRecoverToday, individual_StateDeathToday;
    //    Intent i = getIntent();
    private String individual_state_code, individual_state_name;// = getIntent().getExtras().getString("url");
    private TextView mEmptyStateTextView;
    private IndividualStateHistoryAdapter mIndividualStateHistoryAdapter;

    //    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        individual_state_code = getIntent().getExtras().getString("state_code");
        individual_state_name = getIntent().getExtras().getString("state_name");


        individual_StateConfirmed = getIntent().getExtras().getString("individual_confirmed_state");
        individual_StateActive = getIntent().getExtras().getString("individual_active_state");
        individual_StateRecover = getIntent().getExtras().getString("individual_recover_state");
        individual_StateDeath = getIntent().getExtras().getString("individual_death_state");
        individual_StateConfirmedToday = getIntent().getExtras().getString("individual_today_confirmed_state");
        individual_StateRecoverToday = getIntent().getExtras().getString("individual_today_recover_state");
        individual_StateDeathToday = getIntent().getExtras().getString("individual_today_death_state");


//        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://httpbin.org/get).newBuilder();
//                urlBuilder.addQueryParameter("website", "www.journaldev.com");
//        urlBuilder.addQueryParameter("tutorials", "android");
//        String url = urlBuilder.build().toString();


//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url("https://covid19india.p.rapidapi.com/getStateData/TN")
//                .get()
//                .addHeader("x-rapidapi-host", "covid19india.p.rapidapi.com")
//                .addHeader("x-rapidapi-key", "8aa382d62bmsh1eca65d3b8ebbbbp19a53fjsn3b5ae14a0c73")
//                .build();
//
//        Response response = client.newCall(request).execute();

//        Response response = client.newCall(request).execute();


//        HttpResponse response = Unirest.get("https://covid19india.p.rapidapi.com/getStateData/TN")
//                .header("x-rapidapi-host", "covid19india.p.rapidapi.com")
//                .header("x-rapidapi-key", "8aa382d62bmsh1eca65d3b8ebbbbp19a53fjsn3b5ae14a0c73")
//                .asString();


//        HttpResponse<String> response = Unirest.get("https://covid19india.p.rapidapi.com/getStateData/TN")
//                .header("x-rapidapi-host", "covid19india.p.rapidapi.com")
//                .header("x-rapidapi-key", "8aa382d62bmsh1eca65d3b8ebbbbp19a53fjsn3b5ae14a0c73")
//                .asString();

        loadingIndicator = findViewById(R.id.loading_indicator_individual_state_history_list);


        Log.e(LOG_TAG, "TEST:IndividualStateHistory Activity onCreate() called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_state_history_list);

        edtSearch = (EditText) findViewById(R.id.edtSearch_individual_state);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view_individual_state_history_list);
        ListView individualStateHistoryListView = (ListView) findViewById(R.id.list_individual_state_history_list);

        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.individual_state_history_header, individualStateHistoryListView, false);


//        edtSearch = (EditText)header.findViewById(R.id.edtSearch_individual_state);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                mIndividualStateHistoryAdapter.getFilter().filter(s);
                mIndividualStateHistoryAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        cases = (TextView) header.findViewById(R.id.cases_value_state);
        recover = (TextView) header.findViewById(R.id.recover_value_state);
        death = (TextView) header.findViewById(R.id.death_value_state);
        active = (TextView) header.findViewById(R.id.active_values_state);
        today_cases = (TextView) header.findViewById(R.id.today_cases_value_state);
        today_recover = (TextView) header.findViewById(R.id.today_recover_value_state);
        today_death = (TextView) header.findViewById(R.id.today_death_value_state);
        pieChartAll = (PieChart) header.findViewById(R.id.piechartAll_state);
        pieChartToday = (PieChart) header.findViewById(R.id.piechartToday_state);


        cases.setText(individual_StateConfirmed);
        recover.setText(individual_StateRecover);
        death.setText(individual_StateDeath);
        active.setText(individual_StateActive);
        today_cases.setText(individual_StateConfirmedToday);
        today_recover.setText(individual_StateRecoverToday);
        today_death.setText(individual_StateDeathToday);


        pieChartAll.addPieSlice(new PieModel("Cases", Integer.parseInt(individual_StateConfirmed), Color.parseColor("#FFEB3B")));
        pieChartAll.addPieSlice(new PieModel("Recoverd", Integer.parseInt(individual_StateRecover), Color.parseColor("#0FF418")));
        pieChartAll.addPieSlice(new PieModel("Deaths", Integer.parseInt(individual_StateDeath), Color.parseColor("#F31707")));
        pieChartAll.addPieSlice(new PieModel("Active", Integer.parseInt(individual_StateActive), Color.parseColor("#FF9800")));
//        pieChartAll.startAnimation();


        pieChartToday.addPieSlice(new PieModel("Today Cases", Integer.parseInt(individual_StateConfirmedToday), Color.parseColor("#FFEB3B")));
        pieChartToday.addPieSlice(new PieModel("Today Recoverd", Integer.parseInt(individual_StateRecoverToday), Color.parseColor("#0FF418")));
        pieChartToday.addPieSlice(new PieModel("Today Deaths", Integer.parseInt(individual_StateDeathToday), Color.parseColor("#F31707")));
//        pieChartToday.startAnimation();


        TextView state_code = (TextView) header.findViewById(R.id.state_code_history);
        state_code.setText(individual_state_code);

        TextView stateName = (TextView) header.findViewById(R.id.state_name_history);
        stateName.setText(individual_state_name);

        individualStateHistoryListView.addHeaderView(header, null, false);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view_individual_state_history_list);
        individualStateHistoryListView.setEmptyView(mEmptyStateTextView);


        mIndividualStateHistoryAdapter = new IndividualStateHistoryAdapter(this, new ArrayList<IndividualStateHistoryData>(individualStateList), individual_state_code);

        individualStateHistoryListView.setAdapter(mIndividualStateHistoryAdapter);


        Log.i(LOG_TAG, "TEST: calling initLoader() ...");
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();

            loaderManager.initLoader(INDIVIDUAL_STATE_HISTORY_LOADER_ID
                    , null, this);
        } else {

            loadingIndicator = findViewById(R.id.loading_indicator_individual_state_history_list);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }


    @Override
    public Loader<List<IndividualStateHistoryData>> onCreateLoader(int i, Bundle bundle) {
        Log.i(LOG_TAG, "TEST: onCreateLoader() called ...");
        mIndividualStateHistoryAdapter.clear();
        return new IndividualStateHistoryLoader(this, INDIVIDUAL_STATE_REQUEST_URL, individual_state_code);
    }

    @Override
    public void onLoadFinished(Loader<List<IndividualStateHistoryData>> loader, List<IndividualStateHistoryData> individualStatetHistoryDataList) {

        if (individualStatetHistoryDataList != null) {
            individualStateList = individualStatetHistoryDataList;

        }

        loadingIndicator = findViewById(R.id.loading_indicator_individual_state_history_list);
        loadingIndicator.setVisibility(View.GONE);

        mEmptyStateTextView.setText(R.string.unable_to_load);

        Log.i(LOG_TAG, "TEST: onLoadFinished() called ...");
//        mIndividualStateHistoryAdapter.clear();

        if (mIndividualStateHistoryAdapter.isEmpty() && individualStatetHistoryDataList != null && !individualStatetHistoryDataList.isEmpty()) {
            mIndividualStateHistoryAdapter.addAll(individualStateList);
            edtSearch.setVisibility(View.VISIBLE);

//            mIndividualStateHistoryAdapter.notifyDataSetChanged();
            pieChartAll.startAnimation();
            pieChartToday.startAnimation();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<IndividualStateHistoryData>> loader) {

        Log.i(LOG_TAG, "TEST: onLoaderReset() called ...");
        mIndividualStateHistoryAdapter.clear();
    }


}

