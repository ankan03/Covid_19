package com.example.covid_19;

import android.Manifest;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.ContextCompat.getSystemService;


public class ContractFragment extends Fragment
        implements LoaderCallbacks<List<ContractData>> {

    private static final String LOG_TAG = ContractFragment.class.getName();
    private static final int REQUEST_CALL = 1;
    private static final String CONTRACT_REQUEST_URL =
            "https://api.rootnet.in/covid19-in/contacts";
    private static final int CONTRACT_LOADER_ID = 9;
    View loadingIndicator;
    private Context mcontext;
    private TextView mEmptyStateTextView;
    private String ph_no;
    private ContractAdapter mAdapter;
//    EditText edtSearch;
//    public static List<ContractData> contractList = new ArrayList<>();


    public ContractFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.contract_fragment, container, false);
//        edtSearch = (EditText)rootView.findViewById(R.id.edtSearch_contact);


        Log.e(LOG_TAG, "TEST:Main Activity onCreate() called");


        mEmptyStateTextView = (TextView) rootView.findViewById(R.id.empty_view_contract);
        ListView contractListView = (ListView) rootView.findViewById(R.id.list_contract);


        contractListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ContractData currentContract = (ContractData) mAdapter.getItem(position - 1);

                String no = currentContract.getNumber_individual();
                String no1;
                if (no.contains(",")) {
                    String[] parts = no.split(",");
                    no1 = parts[0];
                    ph_no = no1;
                    Toast.makeText(getActivity(), "Calling in 1st no", Toast.LENGTH_SHORT).show();
//                        no2 = parts[1];
                } else {
                    ph_no = no;
                }

//                ph_no = currentContract.getNumber_individual();

                makePhoneCall();
            }
        });


        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.contract_list_item_header, contractListView, false);
        contractListView.addHeaderView(header, null, false);


//        edtSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                mAdapter.getFilter().filter(s);
//                mAdapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//
        final TextView normal_call = header.findViewById(R.id.call);

        normal_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ph_no = normal_call.getText().toString();
                makePhoneCall();
            }
        });

//        normal_call.setText(new ContractData().getNumber());
//
        final TextView tollfree_call = header.findViewById(R.id.toll_free_call);
        tollfree_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ph_no = tollfree_call.getText().toString();
                makePhoneCall();
            }
        });
//        tollfree_call.setText(new ContractData().getNumber_tollfree());
//
        View facebook = header.findViewById(R.id.facebook_layout);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/MoHFWIndia"));
                startActivity(websiteIntent);
            }
        });
//        facebook.setText(new ContractData().getFacebook());
//
        View twiter = header.findViewById(R.id.twiter_layout);
        twiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/MoHFW_INDIA"));
                startActivity(websiteIntent);
            }
        });
//        twiter.setText(new ContractData().getTwitter());
//
        View mail = header.findViewById(R.id.email_layout);
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"ncov2019@gov.in"});
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }

            }
        });
//        mail.setText(new ContractData().getEmail());
//
//        TextView lastRefreshed = header.findViewById(R.id.lastRefreshed);
//        lastRefreshed.setText(new ContractData().getLastRefreshed());
//
//        TextView lastOriginUpdate = header.findViewById(R.id.lastOriginUpdate);
//        lastOriginUpdate.setText(new ContractData().getLastOriginUpdate());


//        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                ContractData currentContract = (ContractData) mAdapter.getItem(position);
//
//                Uri NewsUri = Uri.parse(currentContract.getUrl());
//
//                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, NewsUri);
//
//                startActivity(websiteIntent);
//            }
//        });

        mEmptyStateTextView = (TextView) rootView.findViewById(R.id.empty_view_contract);

        contractListView.setEmptyView(mEmptyStateTextView);

        mAdapter = new ContractAdapter(getActivity(), new ArrayList<ContractData>());

        contractListView.setAdapter(mAdapter);
        loadingIndicator = rootView.findViewById(R.id.loading_indicator_contract);


        Log.i(LOG_TAG, "TEST: calling initLoader() ...");

        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();


        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getActivity().getLoaderManager();


            loaderManager.initLoader(CONTRACT_LOADER_ID, null, this);
        } else {

            View loadingIndicator = rootView.findViewById(R.id.loading_indicator_contract);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }


        return rootView;
    }


    @Override
    public Loader<List<ContractData>> onCreateLoader(int i, Bundle bundle) {

        Log.i(LOG_TAG, "TEST: onCreateLoader() called ...");
        mAdapter.clear();
        return new ContractLoader(getActivity(), CONTRACT_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<ContractData>> loader, List<ContractData> contractDataList) {
//        contractList = contractDataList;
        loadingIndicator.setVisibility(View.GONE);

        mEmptyStateTextView.setText(R.string.unable_to_load);

        Log.i(LOG_TAG, "TEST: onLoadFinished() called ...");
//        mAdapter.clear();

        if (mAdapter.isEmpty() && contractDataList != null && !contractDataList.isEmpty()) {
            mAdapter.addAll(contractDataList);
//            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<ContractData>> loader) {
        Log.i(LOG_TAG, "TEST: onLoaderReset() called ...");
        mAdapter.clear();
    }

    private void makePhoneCall() {
        String number = ph_no;
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(getActivity(), "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(getActivity(), "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }


}