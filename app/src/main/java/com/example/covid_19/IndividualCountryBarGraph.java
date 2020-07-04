package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class IndividualCountryBarGraph extends AppCompatActivity {
    PieChart pieChart;
    TextView date, cases, recover, death, active;
    private String individualConfirmed, individualActive, individualRecover, individualDeath, individualDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_country_bar_graph);

        pieChart = findViewById(R.id.piechart);
        date = findViewById(R.id.date_individual);
        cases = findViewById(R.id.cases_value);
        recover = findViewById(R.id.recover_value);
        death = findViewById(R.id.death_value);
        active = findViewById(R.id.active_values);

        individualConfirmed = getIntent().getExtras().getString("individual_confirmed");
        individualActive = getIntent().getExtras().getString("individual_active");
        individualRecover = getIntent().getExtras().getString("individual_recover");
        individualDeath = getIntent().getExtras().getString("individual_death");
        individualDate = getIntent().getExtras().getString("individual_date");

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