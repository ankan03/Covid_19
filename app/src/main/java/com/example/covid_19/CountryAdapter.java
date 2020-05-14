package com.example.covid_19;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CountryAdapter extends ArrayAdapter {

    private Context context;

    public CountryAdapter(Context context, ArrayList<WorldData> cityHolders) {
        super(context, 0, cityHolders);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        WorldData currentItem = (WorldData) getItem(position);


        TextView country = (TextView) listItemView.findViewById(R.id.country);
        country.setText(currentItem.getCountry());

        TextView cases = (TextView) listItemView.findViewById(R.id.cases);
        cases.setText(currentItem.getCases());

        TextView todayCases = (TextView) listItemView.findViewById(R.id.today_cases);
        todayCases.setText(currentItem.getTodayCases());


        TextView recovered = (TextView) listItemView.findViewById(R.id.recovered);
        recovered.setText(currentItem.getRecovered().toString());

        TextView death = (TextView) listItemView.findViewById(R.id.deaths);
        death.setText(currentItem.getDeaths());

        TextView todayDeath = (TextView) listItemView.findViewById(R.id.today_deth);
        todayDeath.setText(currentItem.getTodayDeaths());


        ImageView flag = (ImageView) listItemView.findViewById(R.id.flag);
        Glide.with(context)
                .load(currentItem.getFlag())
                .centerCrop()
                .into(flag);


        return listItemView;


    }
}