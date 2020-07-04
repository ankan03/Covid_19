package com.example.covid_19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class IndividualStateHistoryAdapter extends ArrayAdapter {

    public String individual_state_code;
    TextView date, noOfIssued, status, death;
    Date date1;
    private Context context;

    private List<IndividualStateHistoryData> individualStateModelsList;
    private List<IndividualStateHistoryData> individualStateModelsListFiltered;

    public IndividualStateHistoryAdapter(Context context, ArrayList<IndividualStateHistoryData> cityHolders, String individualStateCode) {
        super(context, 0, cityHolders);
        this.context = context;
        individual_state_code = individualStateCode;
        individualStateModelsList = cityHolders;
        this.individualStateModelsListFiltered = cityHolders;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.individual_state_history_item, parent, false);
        }

        IndividualStateHistoryData currentItem = (IndividualStateHistoryData) getItem(position);

//        if (currentItem.getStateName() == "Delhi"){

        date = (TextView) listItemView.findViewById(R.id.date);
        date.setText(individualStateModelsListFiltered.get(position).getDate());

        noOfIssued = (TextView) listItemView.findViewById(R.id.noOfIssue);
        noOfIssued.setText(individualStateModelsListFiltered.get(position).getNoOfIssues());

        status = (TextView) listItemView.findViewById(R.id.status);
        status.setText(individualStateModelsListFiltered.get(position).getStatus());


//            return listItemView;
//        }
        return listItemView;


    }


    @Override
    public int getCount() {
        return individualStateModelsListFiltered.size();
    }

    @Nullable
    @Override
    public IndividualStateHistoryData getItem(int position) {
        return individualStateModelsListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    filterResults.count = individualStateModelsList.size();
                    filterResults.values = individualStateModelsList;

                } else {
                    List<IndividualStateHistoryData> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for (IndividualStateHistoryData itemsModel : individualStateModelsList) {
                        if (itemsModel.getDate().toLowerCase().contains(searchStr)) {
                            resultsModel.add(itemsModel);

                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }


                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                individualStateModelsListFiltered = (List<IndividualStateHistoryData>) results.values;
                IndividualStateHistoryActivity.individualStateList = (List<IndividualStateHistoryData>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}


