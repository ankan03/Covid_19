package com.example.covid_19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends ArrayAdapter {

    private Context context;
    private List<CountryData> countryModelsList;
    private List<CountryData> countryModelsListFiltered;

    public CountryAdapter(Context context, ArrayList<CountryData> cityHolders) {
        super(context, 0, cityHolders);
        this.context = context;
        countryModelsList = cityHolders;
        this.countryModelsListFiltered = cityHolders;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

//        CountryData currentItem = (CountryData) getItem(position);


        TextView country = (TextView) listItemView.findViewById(R.id.country);
//        country.setText(currentItem.getCountry());
        country.setText(countryModelsListFiltered.get(position).getCountry());


        TextView cases = (TextView) listItemView.findViewById(R.id.cases);
        cases.setText(countryModelsListFiltered.get(position).getCases());

        TextView todayCases = (TextView) listItemView.findViewById(R.id.today_cases);
        todayCases.setText(countryModelsListFiltered.get(position).getTodayCases());


        TextView recovered = (TextView) listItemView.findViewById(R.id.recovered);
        recovered.setText(countryModelsListFiltered.get(position).getRecovered().toString());


        TextView todayRecover = (TextView) listItemView.findViewById(R.id.today_recover);
        todayRecover.setText(countryModelsListFiltered.get(position).getTodayRecovered());

        TextView death = (TextView) listItemView.findViewById(R.id.deaths);
        death.setText(countryModelsListFiltered.get(position).getDeaths());

        TextView todayDeath = (TextView) listItemView.findViewById(R.id.today_deth);
        todayDeath.setText(countryModelsListFiltered.get(position).getTodayDeaths());


        ImageView flag = (ImageView) listItemView.findViewById(R.id.flag);
        Glide.with(context)
                .load(countryModelsListFiltered.get(position).getFlag())
                .centerCrop()
                .into(flag);


        notifyDataSetChanged();
        return listItemView;


    }


    @Override
    public int getCount() {
        return countryModelsListFiltered.size();
    }

    @Nullable
    @Override
    public CountryData getItem(int position) {
        return countryModelsListFiltered.get(position);
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
                    filterResults.count = countryModelsList.size();
                    filterResults.values = countryModelsList;

                } else {
                    List<CountryData> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for (CountryData itemsModel : countryModelsList) {
                        if (itemsModel.getCountry().toLowerCase().contains(searchStr)) {
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

                countryModelsListFiltered = (List<CountryData>) results.values;
                CountryFragment.countryList = (List<CountryData>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}