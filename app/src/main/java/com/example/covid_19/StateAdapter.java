package com.example.covid_19;

import android.content.Context;
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

public class StateAdapter extends ArrayAdapter {

    private Context context;

    public StateAdapter(Context context, ArrayList<StateData> cityHolders ) {
        super(context, 0, cityHolders);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.state_list_item, parent, false);
        }

       StateData currentItem_state = (StateData) getItem(position);


        TextView state = (TextView)listItemView.findViewById(R.id.state);
        state.setText(currentItem_state.getState());

        TextView stateCode = (TextView)listItemView.findViewById(R.id.state_code);
        stateCode.setText(currentItem_state.getStatecode());

        TextView district = (TextView)listItemView.findViewById(R.id.district);
        district.setText(currentItem_state.getDistrict());

        TextView cases = (TextView)listItemView.findViewById(R.id.cases_state);
        cases.setText(currentItem_state.getConfirmed());

        TextView todayCases = (TextView)listItemView.findViewById(R.id.today_cases_state);
        todayCases.setText(currentItem_state.getTodayConfirmed());


        TextView recovered = (TextView)listItemView.findViewById(R.id.recovered_state);
        recovered.setText(currentItem_state.getRecovered());

        TextView todayRecovered = (TextView)listItemView.findViewById(R.id.today_recover_state);
        todayRecovered.setText(currentItem_state.getTodayRecovered());

        TextView death = (TextView)listItemView.findViewById(R.id.deaths_state);
        death.setText(currentItem_state.getDeceased());

        TextView todayDeath = (TextView)listItemView.findViewById(R.id.today_deth_state);
        todayDeath.setText(currentItem_state.getTodayDeceased());

        return listItemView;

    }
}
