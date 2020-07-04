package com.example.covid_19;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;


public class WorldActivity extends AppCompatActivity {

    private static final String LOG_TAG = WorldActivity.class.getName();
    TextView tvCases, tvRecovered, tvTodayRecovered, tvActive, tvTodayCases, tvTotalDeaths, tvTodayDeaths, tvAffectedCountries, critical, emptyView;
    SimpleArcLoader simpleArcLoader;
    ScrollView scrollView;
    PieChart pieChart, pieChartToday;
//    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(LOG_TAG, "TEST:Covid19 Activity onCreate() called");
        setContentView(R.layout.world_activity_layout);


//        emptyView = findViewById(R.id.empty_view_world);
        tvCases = findViewById(R.id.tvCases);
        tvRecovered = findViewById(R.id.tvRecovered);
        critical = findViewById(R.id.tvCritical);
        tvActive = findViewById(R.id.tvActive);
        tvTotalDeaths = findViewById(R.id.tvTotalDeaths);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        tvTodayRecovered = findViewById(R.id.tvTodayRecover);
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths);
        tvAffectedCountries = findViewById(R.id.tvAffectedCountries);


        simpleArcLoader = findViewById(R.id.loader);
        scrollView = findViewById(R.id.scrollStats);
        pieChart = findViewById(R.id.piechart);
        pieChartToday = findViewById(R.id.piechartToday_world);


//        if (isNetworkAvailable(this) == true){
//            fetchData();
//        }
//        else {
//            emptyView.setText("No internet connection");
//        }

        fetchData();


    }

    private void fetchData() {

        String url = "https://corona.lmao.ninja/v2/all/";

        simpleArcLoader.start();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());

                            tvCases.setText(jsonObject.getString("cases"));
                            tvRecovered.setText(jsonObject.getString("recovered"));
                            tvActive.setText(jsonObject.getString("active"));
                            critical.setText(jsonObject.getString("critical"));
                            tvTotalDeaths.setText(jsonObject.getString("deaths"));
                            tvTodayCases.setText(jsonObject.getString("todayCases"));
                            tvTodayRecovered.setText(jsonObject.getString("todayRecovered"));
                            tvTodayDeaths.setText(jsonObject.getString("todayDeaths"));
                            tvAffectedCountries.setText(jsonObject.getString("affectedCountries"));


                            pieChart.addPieSlice(new PieModel("Cases", Integer.parseInt(tvCases.getText().toString()), Color.parseColor("#FFEB3B")));
                            pieChart.addPieSlice(new PieModel("Recoverd", Integer.parseInt(tvRecovered.getText().toString()), Color.parseColor("#0FF418")));
                            pieChart.addPieSlice(new PieModel("Deaths", Integer.parseInt(tvTotalDeaths.getText().toString()), Color.parseColor("#F31707")));
                            pieChart.addPieSlice(new PieModel("Active", Integer.parseInt(tvActive.getText().toString()), Color.parseColor("#FF9800")));
                            pieChart.startAnimation();

                            pieChartToday.addPieSlice(new PieModel("Cases", Integer.parseInt(tvTodayCases.getText().toString()), Color.parseColor("#FFEB3B")));
                            pieChartToday.addPieSlice(new PieModel("Recoverd", Integer.parseInt(tvTodayRecovered.getText().toString()), Color.parseColor("#0FF418")));
                            pieChartToday.addPieSlice(new PieModel("Deaths", Integer.parseInt(tvTodayDeaths.getText().toString()), Color.parseColor("#F31707")));
                            pieChartToday.startAnimation();

                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                Toast.makeText(WorldActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }

    public void goTrackCountries(View view) {

        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void health(View view) {

        startActivity(new Intent(getApplicationContext(), MainActivity2.class));
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
