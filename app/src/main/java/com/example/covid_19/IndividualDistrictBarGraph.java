package com.example.covid_19;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class IndividualDistrictBarGraph extends AppCompatActivity {
    PieChart pieChart;
    TextView date, cases, recover, death, active;
    private String individualConfirmed, individualActive, individualRecover, individualDeath, individualDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_district_bar_graph);

        pieChart = (PieChart) findViewById(R.id.piechart_d);
        date = (TextView) findViewById(R.id.date_individual_d);
        cases = (TextView) findViewById(R.id.cases_value_d);
        recover = (TextView) findViewById(R.id.recover_value_d);
        death = (TextView) findViewById(R.id.death_value_d);
        active = (TextView) findViewById(R.id.active_values_d);

        individualConfirmed = getIntent().getExtras().getString("individual_confirmed_d");
        individualActive = getIntent().getExtras().getString("individual_active_d");
        individualRecover = getIntent().getExtras().getString("individual_recover_d");
        individualDeath = getIntent().getExtras().getString("individual_death_d");
        individualDate = getIntent().getExtras().getString("individual_date_d");

        date.setText(individualDate);
        cases.setText(individualConfirmed);
        recover.setText(individualRecover);
        death.setText(individualDeath);
        active.setText(individualActive);

        pieChart.addPieSlice(new PieModel("Cases", Integer.parseInt(individualConfirmed), Color.parseColor("#FFEB3B")));
        pieChart.addPieSlice(new PieModel("Recoverd", Integer.parseInt(individualRecover), Color.parseColor("#0FF418")));
        pieChart.addPieSlice(new PieModel("Deaths", Integer.parseInt(individualDeath), Color.parseColor("#F31707")));
        pieChart.addPieSlice(new PieModel("Active", Integer.parseInt(individualActive), Color.parseColor("#FF9800")));
        pieChart.startAnimation();


    }
}