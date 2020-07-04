package com.example.covid_19;

import android.content.Context;
import android.util.Log;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ContractAdapter extends ArrayAdapter {

    String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private Context context;
    //    private List<ContractData> contractModelsList;
//    private List<ContractData> contractModelsListFiltered;
    private String no1, no2;


    public ContractAdapter(Context context, ArrayList<ContractData> contractHolders) {
        super(context, 0, contractHolders);
        this.context = context;
//        contractModelsList = contractHolders;
//        this.contractModelsListFiltered = contractHolders;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.contract_list_item, parent, false);
        }

        ContractData currentItem = (ContractData) getItem(position);


        TextView state_name_contract = (TextView) listItemView.findViewById(R.id.state_name_contract);
        state_name_contract.setText(currentItem.getLoc());

        TextView number1 = (TextView) listItemView.findViewById(R.id.contract_no_1);

        String no = currentItem.getNumber_individual();

//             String[] parts = no.split(",");
//             no1 = parts[0];
//             no2 = parts[1];


        number1.setText(no);
//        number2.setText(no2);


        notifyDataSetChanged();
        return listItemView;

    }
//
//
//    @Override
//    public int getCount() {
//        return contractModelsListFiltered.size();
//    }
//
//    @Nullable
//    @Override
//    public ContractData getItem(int position) {
//        return contractModelsListFiltered.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public Filter getFilter() {
//        Filter filter = new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//
//                FilterResults filterResults = new FilterResults();
//                if(constraint == null || constraint.length() == 0){
//                    filterResults.count = contractModelsList.size();
//                    filterResults.values = contractModelsList;
//
//                }else{
//                    List<ContractData> resultsModel = new ArrayList<>();
//                    String searchStr = constraint.toString().toLowerCase();
//
//                    for(ContractData itemsModel:contractModelsList){
//                        if(itemsModel.getLoc().toLowerCase().contains(searchStr)){
//                            resultsModel.add(itemsModel);
//
//                        }
//                        filterResults.count = resultsModel.size();
//                        filterResults.values = resultsModel;
//                    }
//
//
//                }
//
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//
//                contractModelsListFiltered = (List<ContractData>) results.values;
//                ContractFragment.contractList = (List<ContractData>) results.values;
//                notifyDataSetChanged();
//
//            }
//        };
//        return filter;
//    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm:ss a");
        return timeFormat.format(dateObject);
    }

    public Date parseDate(String stringToParse) {
        Date dateAndTime = null;
        try {
            dateAndTime = new SimpleDateFormat(DATE_FORMAT_PATTERN).parse(stringToParse);
        } catch (ParseException e) {
            Log.e("Contract Adapter", "Problem retrieving the contract JSON results.", e);
        }
        return null;
    }
}