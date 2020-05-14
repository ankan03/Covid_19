package com.example.covid_19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class HistoryAdapter extends ArrayAdapter {

    private Context context;
    private String previousDate = null, previousStateCode = null, previousStatus = null;

    public HistoryAdapter(Context context, ArrayList<HistoryData> cityHolders) {
        super(context, 0, cityHolders);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        View listItemView = convertView;


        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.history_list_item, parent, false);


        }

        HistoryData currentItem_history = (HistoryData) getItem(position);

        TextView stateCode = (TextView) listItemView.findViewById(R.id.state_code_history);
        stateCode.setText(currentItem_history.getSelectedState().toUpperCase());


        TextView date = (TextView) listItemView.findViewById(R.id.date);
        date.setText(currentItem_history.getDate());


        TextView status = (TextView) listItemView.findViewById(R.id.status);
        status.setText(currentItem_history.getStatus());

        TextView noOfIssue = (TextView) listItemView.findViewById(R.id.noOfIssue);
        noOfIssue.setText(currentItem_history.getNoOfIssue());
        return listItemView;

    }
}

