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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class IndividualCountryHistoryAdapter extends ArrayAdapter {

    Date date1;
    private Context context;
    private List<IndividualCountryHistoryData> individualCountryModelsList;
    private List<IndividualCountryHistoryData> individualCountryModelsListFiltered;

    public IndividualCountryHistoryAdapter(Context context, ArrayList<IndividualCountryHistoryData> cityHolders) {
        super(context, 0, cityHolders);
        this.context = context;
        individualCountryModelsList = cityHolders;
        this.individualCountryModelsListFiltered = cityHolders;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.individual_country_history_item, parent, false);
        }

        IndividualCountryHistoryData currentItem = (IndividualCountryHistoryData) getItem(position);


        TextView date = (TextView) listItemView.findViewById(R.id.individual_country_history_date);
        String formatted_individual_country_history_date = individualCountryModelsListFiltered.get(position).getDate();

//        Date formatedDate = new Date();
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
//        String dateAsISOString = df.format(formatedDate);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String dateString = formatted_individual_country_history_date.replace("Z", "GMT+00:00");
        try {
            date1 = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String formattedDate = formatDate(date1);
        String formattTime = formatTime(date1);

        date.setText(formattTime + "  " + formattedDate);


        TextView cases = (TextView) listItemView.findViewById(R.id.individual_country_history_cases);
        String formatted_individual_country_history_cases = individualCountryModelsListFiltered.get(position).getConfirmed();
        cases.setText(formatted_individual_country_history_cases);


        TextView recover = (TextView) listItemView.findViewById(R.id.individual_country_history_recover);
        String formatted_individual_country_history_recover = individualCountryModelsListFiltered.get(position).getRecovered();
        recover.setText(formatted_individual_country_history_recover);


        TextView death = (TextView) listItemView.findViewById(R.id.individual_country_history_daths);
        String formatted_individual_country_history_death = individualCountryModelsListFiltered.get(position).getDeaths();
        death.setText(formatted_individual_country_history_death);


        return listItemView;


    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }


    @Override
    public int getCount() {
        return individualCountryModelsListFiltered.size();
    }

    @Nullable
    @Override
    public IndividualCountryHistoryData getItem(int position) {
        return individualCountryModelsListFiltered.get(position);
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
                    filterResults.count = individualCountryModelsList.size();
                    filterResults.values = individualCountryModelsList;

                } else {
                    List<IndividualCountryHistoryData> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for (IndividualCountryHistoryData itemsModel : individualCountryModelsList) {
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

                individualCountryModelsListFiltered = (List<IndividualCountryHistoryData>) results.values;
                cActivity.individualCountryList = (List<IndividualCountryHistoryData>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}
