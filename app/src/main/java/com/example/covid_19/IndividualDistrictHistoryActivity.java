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

import com.bumptech.glide.Glide;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class IndividualDistrictHistoryActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<IndividualDistrictHistoryData>> {

//    mGameType = getIntent().getExtras().getBoolean("gameType");

    private static final String LOG_TAG = IndividualDistrictHistoryActivity.class.getName();
    //Bundle bundle = getIntent().getExtras();
    //String INDIVIDUAL_COUNTRY_LIST_REQUEST_URL = url;
    //String requestUrl = getIntent().getStringExtra("REQUEST_URL");
//   private String INDIVIDUAL_COUNTRY_LIST_REQUEST_URL =i.getStringExtra("REQUEST_URL");
//    private String url="https://api.covid19api.com/dayone/country/india";
    private static final String INDIVIDUAL_DISTRICT_REQUEST_URL = "https://api.covid19india.org/districts_daily.json";
    private static final int INDIVIDUAL_DISTRICT_HISTORY_LOADER_ID = 6;
    public static List<IndividualDistrictHistoryData> individualDistrictList = new ArrayList<>();
    View loadingIndicator;
    PieChart pieChartAll, pieChartToday;
    TextView cases, recover, death, active, today_cases, today_recover, today_death;
    Date date1;
    EditText edtSearch;
    //    Intent i = getIntent();
    private String individual_district_name, individual_state_name, individual_state_code;// = getIntent().getExtras().getString("url");
    private TextView mEmptyStateTextView;
    private IndividualDistrictHistoryAdapter mIndividualDistrictHistoryAdapter;
    private String individual_DistrictConfirmed, individual_DistrictActive, individual_DistrictRecover, individual_DistrictDeath, individual_DistrictConfirmedToday, individual_DistrictRecoverToday, individual_DistrictDeathToday;
    private String individualDistrictConfirmed, individualDistrictActive, individualDistrictRecover, individualDistrictDeath, individualDistrictDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        individual_district_name = getIntent().getExtras().getString("district_name");
        individual_state_name = getIntent().getExtras().getString("state_name");
        individual_state_code = getIntent().getExtras().getString("state_code");


        individual_DistrictConfirmed = getIntent().getExtras().getString("individual_confirmed_district");
        individual_DistrictActive = getIntent().getExtras().getString("individual_active_district");
        individual_DistrictRecover = getIntent().getExtras().getString("individual_recover_district");
        individual_DistrictDeath = getIntent().getExtras().getString("individual_death_district");
        individual_DistrictConfirmedToday = getIntent().getExtras().getString("individual_today_confirmed_district");
        individual_DistrictRecoverToday = getIntent().getExtras().getString("individual_today_recover_district");
        individual_DistrictDeathToday = getIntent().getExtras().getString("individual_today_death_district");


        loadingIndicator = findViewById(R.id.loading_indicator_individual_district_history_list);


        Log.e(LOG_TAG, "TEST:IndividualCountryHistory Activity onCreate() called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_district_history_list);

        edtSearch = (EditText) findViewById(R.id.edtSearch_individual_district);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view_individual_district_history_list);
        ListView individualDistrictHistoryListView = (ListView) findViewById(R.id.list_individual_district_history_list);


        individualDistrictHistoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                IndividualDistrictHistoryData districtindividualDistrictHistoryData = (IndividualDistrictHistoryData) mIndividualDistrictHistoryAdapter.getItem(position - 1);

                individualDistrictConfirmed = districtindividualDistrictHistoryData.getConfirmed();
                individualDistrictActive = districtindividualDistrictHistoryData.getActive();
                individualDistrictRecover = districtindividualDistrictHistoryData.getRecovered();
                individualDistrictDeath = districtindividualDistrictHistoryData.getDeaths();
                individualDistrictDate = districtindividualDistrictHistoryData.getDate();

                date1 = StringToDate(individualDistrictDate);

//                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                String dateString = individualCountryDate.replace("Z", "GMT+00:00");
//                try {
//                    date1 = dateFormat.parse(dateString);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }

                String formattedDate = formatDate(date1);
//                String formattTime = formatTime(date1);
//
//                String date = formattTime + "  " + formattedDate +" (GMT)";


                Intent intent = new Intent(getBaseContext(), IndividualCountryBarGraph.class);

                intent.putExtra("individual_confirmed", individualDistrictConfirmed);
                intent.putExtra("individual_active", individualDistrictActive);
                intent.putExtra("individual_recover", individualDistrictRecover);
                intent.putExtra("individual_death", individualDistrictDeath);
                intent.putExtra("individual_date", formattedDate);

                startActivity(intent);
            }
        });


        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.individual_district_history_header, individualDistrictHistoryListView, false);

//        edtSearch = (EditText)header.findViewById(R.id.edtSearch_individual_district);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                mIndividualDistrictHistoryAdapter.getFilter().filter(s);
                mIndividualDistrictHistoryAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        cases = (TextView) header.findViewById(R.id.cases_value_district);
        recover = (TextView) header.findViewById(R.id.recover_value_district);
        death = (TextView) header.findViewById(R.id.death_value_district);
        active = (TextView) header.findViewById(R.id.active_values_district);
        today_cases = (TextView) header.findViewById(R.id.today_cases_value_district);
        today_recover = (TextView) header.findViewById(R.id.today_recover_value_district);
        today_death = (TextView) header.findViewById(R.id.today_death_value_district);
        pieChartAll = (PieChart) header.findViewById(R.id.piechartAll_district);
        pieChartToday = (PieChart) header.findViewById(R.id.piechartToday_district);


        cases.setText(individual_DistrictConfirmed);
        recover.setText(individual_DistrictRecover);
        death.setText(individual_DistrictDeath);
        active.setText(individual_DistrictActive);
        today_cases.setText(individual_DistrictConfirmedToday);
        today_recover.setText(individual_DistrictRecoverToday);
        today_death.setText(individual_DistrictDeathToday);


        pieChartAll.addPieSlice(new PieModel("Cases", Integer.parseInt(individual_DistrictConfirmed), Color.parseColor("#FFEB3B")));
        pieChartAll.addPieSlice(new PieModel("Recoverd", Integer.parseInt(individual_DistrictRecover), Color.parseColor("#0FF418")));
        pieChartAll.addPieSlice(new PieModel("Deaths", Integer.parseInt(individual_DistrictDeath), Color.parseColor("#F31707")));
        pieChartAll.addPieSlice(new PieModel("Active", Integer.parseInt(individual_DistrictActive), Color.parseColor("#FF9800")));
//        pieChartAll.startAnimation();


        pieChartToday.addPieSlice(new PieModel("Today Cases", Integer.parseInt(individual_DistrictConfirmedToday), Color.parseColor("#FFEB3B")));
        pieChartToday.addPieSlice(new PieModel("Today Recoverd", Integer.parseInt(individual_DistrictRecoverToday), Color.parseColor("#0FF418")));
        pieChartToday.addPieSlice(new PieModel("Today Deaths", Integer.parseInt(individual_DistrictDeathToday), Color.parseColor("#F31707")));
//        pieChartToday.startAnimation();


        TextView district_name = (TextView) header.findViewById(R.id.district_name_history);
        district_name.setText(individual_district_name);

        TextView stateName = (TextView) header.findViewById(R.id.state_name_history);
        stateName.setText(individual_state_name);

        TextView stateCode = (TextView) header.findViewById(R.id.individual_state_code);
        stateCode.setText(individual_state_code);

        individualDistrictHistoryListView.addHeaderView(header, null, false);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view_individual_district_history_list);
        individualDistrictHistoryListView.setEmptyView(mEmptyStateTextView);


        mIndividualDistrictHistoryAdapter = new IndividualDistrictHistoryAdapter(this, new ArrayList<IndividualDistrictHistoryData>(individualDistrictList));

        individualDistrictHistoryListView.setAdapter(mIndividualDistrictHistoryAdapter);


        Log.i(LOG_TAG, "TEST: calling initLoader() ...");
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();

            loaderManager.initLoader(INDIVIDUAL_DISTRICT_HISTORY_LOADER_ID
                    , null, this);
        } else {

            loadingIndicator = findViewById(R.id.loading_indicator_individual_district_history_list);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }

    @Override
    public Loader<List<IndividualDistrictHistoryData>> onCreateLoader(int i, Bundle bundle) {
        Log.i(LOG_TAG, "TEST: onCreateLoader() called ...");
        mIndividualDistrictHistoryAdapter.clear();
        return new IndividualDistrictHistoryLoader(this, INDIVIDUAL_DISTRICT_REQUEST_URL, individual_district_name, individual_state_name);
    }

    @Override
    public void onLoadFinished(Loader<List<IndividualDistrictHistoryData>> loader, List<IndividualDistrictHistoryData> individualDistrictHistoryDataList) {


        if (individualDistrictHistoryDataList != null) {
            individualDistrictList = individualDistrictHistoryDataList;

        }
        loadingIndicator = findViewById(R.id.loading_indicator_individual_district_history_list);
        loadingIndicator.setVisibility(View.GONE);

        mEmptyStateTextView.setText(R.string.unable_to_load);

        Log.i(LOG_TAG, "TEST: onLoadFinished() called ...");
//        mIndividualDistrictHistoryAdapter.clear();

        if (mIndividualDistrictHistoryAdapter.isEmpty() && individualDistrictHistoryDataList != null && !individualDistrictHistoryDataList.isEmpty()) {
            mIndividualDistrictHistoryAdapter.addAll(individualDistrictList);
            edtSearch.setVisibility(View.VISIBLE);
//            mIndividualDistrictHistoryAdapter.notifyDataSetChanged();
            pieChartAll.startAnimation();
            pieChartToday.startAnimation();

        }
    }

    @Override
    public void onLoaderReset(Loader<List<IndividualDistrictHistoryData>> loader) {

        Log.i(LOG_TAG, "TEST: onLoaderReset() called ...");
        mIndividualDistrictHistoryAdapter.clear();
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    public Date StringToDate(String s) {

        Date result = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            result = dateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return result;
    }
}
