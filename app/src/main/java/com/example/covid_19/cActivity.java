package com.example.covid_19;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Color;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager.LoaderCallbacks;

import com.bumptech.glide.Glide;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class cActivity extends AppCompatActivity
        implements LoaderCallbacks<List<IndividualCountryHistoryData>> {


    private static final String LOG_TAG = cActivity.class.getName();
    //Bundle bundle = getIntent().getExtras();
    //String INDIVIDUAL_COUNTRY_LIST_REQUEST_URL = url;
    //String requestUrl = getIntent().getStringExtra("REQUEST_URL");
//   private String INDIVIDUAL_COUNTRY_LIST_REQUEST_URL =i.getStringExtra("REQUEST_URL");
//    private String url="https://api.covid19api.com/dayone/country/india";
    private static final int INDIVIDUAL_COUNTRY_HISTORY_LOADER_ID = 5;
    public static List<IndividualCountryHistoryData> individualCountryList = new ArrayList<>();


    //    mGameType = getIntent().getExtras().getBoolean("gameType");
    PieChart pieChartAll, pieChartToday;
    TextView cases, recover, death, active, today_cases, today_recover, today_death;
    View loadingIndicator;
    Date date1;
    EditText edtSearch;
    private String individual_CountryConfirmed, individual_CountryActive, individual_CountryRecover, individual_CountryDeath, individual_CountryConfirmedToday, individual_CountryRecoverToday, individual_CountryDeathToday;
    //    Intent i = getIntent();
    private String url, individual_country_name, individual_country_flag;// = getIntent().getExtras().getString("url");
    private TextView mEmptyStateTextView;
    private IndividualCountryHistoryAdapter mIndividualCountryHistoryAdapter;
    private String individualCountryConfirmed, individualCountryActive, individualCountryRecover, individualCountryDeath, individualCountryDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        individualCountryList = null;

        url = getIntent().getExtras().getString("url");
        individual_country_name = getIntent().getExtras().getString("country_name");
        individual_country_flag = getIntent().getExtras().getString("country_flag");


        individual_CountryConfirmed = getIntent().getExtras().getString("individual_confirmed_country");
        individual_CountryActive = getIntent().getExtras().getString("individual_active_country");
        individual_CountryRecover = getIntent().getExtras().getString("individual_recover_country");
        individual_CountryDeath = getIntent().getExtras().getString("individual_death_country");
        individual_CountryConfirmedToday = getIntent().getExtras().getString("individual_today_confirmed_country");
        individual_CountryRecoverToday = getIntent().getExtras().getString("individual_today_recover_country");
        individual_CountryDeathToday = getIntent().getExtras().getString("individual_today_death_country");


        loadingIndicator = findViewById(R.id.loading_indicator_individual_country_history_list);


        Log.e(LOG_TAG, "TEST:IndividualCountryHistory Activity onCreate() called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_country_history_list);


        edtSearch = (EditText) findViewById(R.id.edtSearch_individual_country);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view_individual_country_history_list);
        ListView individualCountryHistoryListView = (ListView) findViewById(R.id.list_individual_country_history_list);


        individualCountryHistoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                IndividualCountryHistoryData currentCountry = (IndividualCountryHistoryData) mIndividualCountryHistoryAdapter.getItem(position - 1);
                individualCountryConfirmed = currentCountry.getConfirmed();
                individualCountryActive = currentCountry.getActive();
                individualCountryRecover = currentCountry.getRecovered();
                individualCountryDeath = currentCountry.getDeaths();
                individualCountryDate = currentCountry.getDate();

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
                String dateString = individualCountryDate.replace("Z", "GMT+00:00");
                try {
                    date1 = dateFormat.parse(dateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String formattedDate = formatDate(date1);
                String formattTime = formatTime(date1);

                String date = formattTime + "  " + formattedDate + " (GMT)";


                Intent intent = new Intent(getBaseContext(), IndividualCountryBarGraph.class);

                intent.putExtra("individual_confirmed", individualCountryConfirmed);
                intent.putExtra("individual_active", individualCountryActive);
                intent.putExtra("individual_recover", individualCountryRecover);
                intent.putExtra("individual_death", individualCountryDeath);
                intent.putExtra("individual_date", date);

                startActivity(intent);
            }
        });


        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.individual_country_history_header, individualCountryHistoryListView, false);
        TextView country_name = (TextView) header.findViewById(R.id.country_name_history);


//        edtSearch = (EditText)header.findViewById(R.id.edtSearch_individual_country);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (mIndividualCountryHistoryAdapter != null) {
                    mIndividualCountryHistoryAdapter.getFilter().filter(s);
                    mIndividualCountryHistoryAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        cases = (TextView) header.findViewById(R.id.cases_value_country);
        recover = (TextView) header.findViewById(R.id.recover_value_country);
        death = (TextView) header.findViewById(R.id.death_value_country);
        active = (TextView) header.findViewById(R.id.active_values_country);
        today_cases = (TextView) header.findViewById(R.id.today_cases_value_country);
        today_recover = (TextView) header.findViewById(R.id.today_recover_value_country);
        today_death = (TextView) header.findViewById(R.id.today_death_value_country);
        pieChartAll = (PieChart) header.findViewById(R.id.piechartAll_country);
        pieChartToday = (PieChart) header.findViewById(R.id.piechartToday_country);


        cases.setText(individual_CountryConfirmed);
        recover.setText(individual_CountryRecover);
        death.setText(individual_CountryDeath);
        active.setText(individual_CountryActive);
        today_cases.setText(individual_CountryConfirmedToday);
        today_recover.setText(individual_CountryRecoverToday);
        today_death.setText(individual_CountryDeathToday);


        pieChartAll.addPieSlice(new PieModel("Cases", Integer.parseInt(individual_CountryConfirmed), Color.parseColor("#FFEB3B")));
        pieChartAll.addPieSlice(new PieModel("Recoverd", Integer.parseInt(individual_CountryRecover), Color.parseColor("#0FF418")));
        pieChartAll.addPieSlice(new PieModel("Deaths", Integer.parseInt(individual_CountryDeath), Color.parseColor("#F31707")));
        pieChartAll.addPieSlice(new PieModel("Active", Integer.parseInt(individual_CountryActive), Color.parseColor("#FF9800")));
//        pieChartAll.startAnimation();


        pieChartToday.addPieSlice(new PieModel("Today Cases", Integer.parseInt(individual_CountryConfirmedToday), Color.parseColor("#FFEB3B")));
        pieChartToday.addPieSlice(new PieModel("Today Recoverd", Integer.parseInt(individual_CountryRecoverToday), Color.parseColor("#0FF418")));
        pieChartToday.addPieSlice(new PieModel("Today Deaths", Integer.parseInt(individual_CountryDeathToday), Color.parseColor("#F31707")));
//        pieChartToday.startAnimation();


        country_name.setText(individual_country_name);

        ImageView flag = (ImageView) header.findViewById(R.id.country_flag_history);
        Glide.with(this)
                .load(individual_country_flag)
                .centerCrop()
                .into(flag);

        individualCountryHistoryListView.addHeaderView(header, null, false);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view_individual_country_history_list);
        individualCountryHistoryListView.setEmptyView(mEmptyStateTextView);


        mIndividualCountryHistoryAdapter = new IndividualCountryHistoryAdapter(this, new ArrayList<IndividualCountryHistoryData>(individualCountryList));

        individualCountryHistoryListView.setAdapter(mIndividualCountryHistoryAdapter);


        Log.i(LOG_TAG, "TEST: calling initLoader() ...");
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();

            loaderManager.initLoader(INDIVIDUAL_COUNTRY_HISTORY_LOADER_ID
                    , null, this);
        } else {

            loadingIndicator = findViewById(R.id.loading_indicator_individual_country_history_list);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }


    }

    @Override
    public Loader<List<IndividualCountryHistoryData>> onCreateLoader(int i, Bundle bundle) {
        Log.i(LOG_TAG, "TEST: onCreateLoader() called ...");
        mIndividualCountryHistoryAdapter.clear();
        return new IndividualCountryHistoryLoader(this, url);
    }

    @Override
    public void onLoadFinished(Loader<List<IndividualCountryHistoryData>> loader, List<IndividualCountryHistoryData> individualCountryHistoryDataList) {

        if (individualCountryHistoryDataList != null) {
            individualCountryList = individualCountryHistoryDataList;
//            edtSearch.setVisibility(View.VISIBLE);
        }
//        if (individualCountryHistoryDataList == null){
//            edtSearch.setVisibility(View.GONE);
//        }

        loadingIndicator = findViewById(R.id.loading_indicator_individual_country_history_list);
        loadingIndicator.setVisibility(View.GONE);

        mEmptyStateTextView.setText(R.string.unable_to_load);

        Log.i(LOG_TAG, "TEST: onLoadFinished() called ...");
//        mIndividualCountryHistoryAdapter.clear();

        if (mIndividualCountryHistoryAdapter.isEmpty() && individualCountryHistoryDataList != null && !individualCountryHistoryDataList.isEmpty()) {
            mIndividualCountryHistoryAdapter.addAll(individualCountryList);
            edtSearch.setVisibility(View.VISIBLE);
//            mIndividualCountryHistoryAdapter.notifyDataSetChanged();
            pieChartAll.startAnimation();
            pieChartToday.startAnimation();
        }

    }

    @Override
    public void onLoaderReset(Loader<List<IndividualCountryHistoryData>> loader) {

        Log.i(LOG_TAG, "TEST: onLoaderReset() called ...");
        mIndividualCountryHistoryAdapter.clear();
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}