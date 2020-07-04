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
import java.util.List;

public class DistrictAdapter extends ArrayAdapter {

    private Context context;
    private List<DistrictData> districtModelsList;
    private List<DistrictData> districtModelsListFiltered;

    public DistrictAdapter(Context context, ArrayList<DistrictData> cityHolders) {
        super(context, 0, cityHolders);
        this.context = context;
        districtModelsList = cityHolders;
        this.districtModelsListFiltered = cityHolders;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.district_list_item, parent, false);
        }

        DistrictData currentItem_district = (DistrictData) getItem(position);


        TextView state = (TextView) listItemView.findViewById(R.id.state);
        state.setText(districtModelsListFiltered.get(position).getState());

        TextView stateCode = (TextView) listItemView.findViewById(R.id.state_code);
        stateCode.setText(districtModelsListFiltered.get(position).getStatecode());

        TextView district = (TextView) listItemView.findViewById(R.id.district);
        district.setText(districtModelsListFiltered.get(position).getDistrict());

        TextView cases = (TextView) listItemView.findViewById(R.id.cases_district);
        cases.setText(districtModelsListFiltered.get(position).getConfirmed());

        TextView todayCases = (TextView) listItemView.findViewById(R.id.today_cases_district);
        todayCases.setText(districtModelsListFiltered.get(position).getTodayConfirmed());


        TextView recovered = (TextView) listItemView.findViewById(R.id.recovered_district);
        recovered.setText(districtModelsListFiltered.get(position).getRecovered());

        TextView todayRecovered = (TextView) listItemView.findViewById(R.id.today_recover_district);
        todayRecovered.setText(districtModelsListFiltered.get(position).getTodayRecovered());

        TextView death = (TextView) listItemView.findViewById(R.id.deaths_district);
        death.setText(districtModelsListFiltered.get(position).getDeceased());

        TextView todayDeath = (TextView) listItemView.findViewById(R.id.today_deth_district);
        todayDeath.setText(districtModelsListFiltered.get(position).getTodayDeceased());

        return listItemView;

    }

    @Override
    public int getCount() {
        return districtModelsListFiltered.size();
    }

    @Nullable
    @Override
    public DistrictData getItem(int position) {
        return districtModelsListFiltered.get(position);
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
                    filterResults.count = districtModelsList.size();
                    filterResults.values = districtModelsList;

                } else {
                    List<DistrictData> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for (DistrictData itemsModel : districtModelsList) {
                        if (itemsModel.getDistrict().toLowerCase().contains(searchStr)) {
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

                districtModelsListFiltered = (List<DistrictData>) results.values;
                DistrictFragment.districtList = (List<DistrictData>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}
