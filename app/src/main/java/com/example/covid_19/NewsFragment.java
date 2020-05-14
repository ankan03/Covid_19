package com.example.covid_19;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import android.app.LoaderManager.LoaderCallbacks;


public class NewsFragment extends Fragment
        implements LoaderCallbacks<List<NewsData>> {

    private Context mcontext;
    private static final String LOG_TAG = NewsFragment.class.getName();
    private TextView mEmptyStateTextView;
    View loadingIndicator;



    private static final String NEWS_REQUEST_URL =
            "http://newsapi.org/v2/top-headlines?country=in&category=health&apiKey=67c4b6b63f3e4b74ab87ef3d15b99017";


    private static final int NEWS_LOADER_ID = 4;

    private NewsAdapter mAdapter;



    public NewsFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_fragment, container, false);

        Log.e(LOG_TAG,"TEST:Main Activity onCreate() called");


        mEmptyStateTextView = (TextView) rootView.findViewById(R.id.empty_view_news);
        ListView newsListView = (ListView) rootView.findViewById(R.id.list_news);




        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                NewsData currentNews = (NewsData) mAdapter.getItem(position);

                Uri NewsUri = Uri.parse(currentNews.getUrl());

                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, NewsUri);

                startActivity(websiteIntent);
            }
        });

        mEmptyStateTextView = (TextView) rootView.findViewById(R.id.empty_view_news);

        newsListView.setEmptyView(mEmptyStateTextView);

        mAdapter = new NewsAdapter(getActivity(), new ArrayList<NewsData>());

        newsListView.setAdapter(mAdapter);
        loadingIndicator = rootView.findViewById(R.id.loading_indicator_news);


        Log.i(LOG_TAG,"TEST: calling initLoader() ...");

        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();


        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getActivity().getLoaderManager();


            loaderManager.initLoader(NEWS_LOADER_ID, null,  this);
        } else {

            View loadingIndicator = rootView.findViewById(R.id.loading_indicator_news);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }


        return rootView;
    }

    @Override
    public Loader<List<NewsData>> onCreateLoader(int i, Bundle bundle) {

        Log.i(LOG_TAG,"TEST: onCreateLoader() called ...");
        return new NewsLoader(getActivity(), NEWS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsData>> loader, List<NewsData> newsDataList) {
        loadingIndicator.setVisibility(View.GONE);

        mEmptyStateTextView.setText(R.string.unable_to_load);

        Log.i(LOG_TAG,"TEST: onLoadFinished() called ...");
        mAdapter.clear();

        if (newsDataList != null && !newsDataList.isEmpty()) {
            mAdapter.addAll(newsDataList);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<NewsData>> loader) {
        Log.i(LOG_TAG,"TEST: onLoaderReset() called ...");
        mAdapter.clear();
    }



}
