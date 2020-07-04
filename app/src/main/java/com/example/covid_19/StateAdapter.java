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

public class StateAdapter extends ArrayAdapter {

    private Context context;
    private List<StateData> stateModelsList;
    private List<StateData> stateModelsListFiltered;

    public StateAdapter(Context context, ArrayList<StateData> cityHolders) {
        super(context, 0, cityHolders);
        this.context = context;
        stateModelsList = cityHolders;
        this.stateModelsListFiltered = cityHolders;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.state_list_item, parent, false);
        }

        StateData currentItem_state = (StateData) getItem(position);


        TextView state = (TextView) listItemView.findViewById(R.id.state_name);
        state.setText(stateModelsListFiltered.get(position).getState());

        TextView stateCode = (TextView) listItemView.findViewById(R.id.state_code);
        stateCode.setText(stateModelsListFiltered.get(position).getStatecode());

        TextView cases = (TextView) listItemView.findViewById(R.id.cases_state);
        cases.setText(stateModelsListFiltered.get(position).getConfirmed());

        TextView todayCases = (TextView) listItemView.findViewById(R.id.today_cases_state);
        todayCases.setText(stateModelsListFiltered.get(position).getDeltaconfirmed());


        TextView recovered = (TextView) listItemView.findViewById(R.id.recovered_state);
        recovered.setText(stateModelsListFiltered.get(position).getRecovered());

        TextView todayRecovered = (TextView) listItemView.findViewById(R.id.today_recover_state);
        todayRecovered.setText(stateModelsListFiltered.get(position).getDeltarecovered());

        TextView death = (TextView) listItemView.findViewById(R.id.deaths_state);
        death.setText(stateModelsListFiltered.get(position).getDeaths());

        TextView todayDeath = (TextView) listItemView.findViewById(R.id.today_deth_state);
        todayDeath.setText(stateModelsListFiltered.get(position).getDeltadeaths());

        TextView statenotes = (TextView) listItemView.findViewById(R.id.statenotes);
        statenotes.setText(stateModelsListFiltered.get(position).getStatenotes());

        TextView lastupdatedtime = (TextView) listItemView.findViewById(R.id.lastupdatedtime);
        lastupdatedtime.setText(stateModelsListFiltered.get(position).getLastupdatedtime());

        notifyDataSetChanged();
        return listItemView;

    }

    @Override
    public int getCount() {
        return stateModelsListFiltered.size();
    }

    @Nullable
    @Override
    public StateData getItem(int position) {
        return stateModelsListFiltered.get(position);
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
                    filterResults.count = stateModelsList.size();
                    filterResults.values = stateModelsList;

                } else {
                    List<StateData> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for (StateData itemsModel : stateModelsList) {
                        if (itemsModel.getState().toLowerCase().contains(searchStr)) {
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

                stateModelsListFiltered = (List<StateData>) results.values;
                StateFragment.stateList = (List<StateData>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}

