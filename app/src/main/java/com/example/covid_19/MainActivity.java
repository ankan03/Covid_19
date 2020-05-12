package com.example.covid_19;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.LoaderManager;
//import android.content.Context;
//import android.content.Intent;
//import android.content.Loader;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.net.Uri;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.List;
//import android.app.LoaderManager.LoaderCallbacks;
//
//
//public class MainActivity extends AppCompatActivity
//        implements LoaderCallbacks<List<WorldData>> {
//
//    private static final String LOG_TAG = MainActivity.class.getName();
//    private TextView mEmptyStateTextView;
//
//    /** URL for earthquake data from the USGS dataset */
//    private static final String USGS_REQUEST_URL =
//            "https://disease.sh/v2/countries";
//            //"https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=2";
//    /**
//     * Constant value for the earthquake loader ID. We can choose any integer.
//     * This really only comes into play if you're using multiple loaders.
//     */
//    private static final int EARTHQUAKE_LOADER_ID = 1;
//
//    /** Adapter for the list of earthquakes */
//    private CovidAdapter mAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        Log.e(LOG_TAG,"TEST:Main Activity onCreate() called");
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
//        ListView countryListView = (ListView) findViewById(R.id.list);
//
//        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
//        //mEmptyStateTextView.setText(R.string.no_earthquakes);
//        countryListView.setEmptyView(mEmptyStateTextView);
//
//
//
//
//        // Find a reference to the {@link ListView} in the layout
//
//        // Create a new adapter that takes an empty list of earthquakes as input
//        mAdapter = new CovidAdapter(this, new ArrayList<WorldData>());
//
//        // Set the adapter on the {@link ListView}
//        // so the list can be populated in the user interface
//        countryListView.setAdapter(mAdapter);
//
//        // Set an item click listener on the ListView, which sends an intent to a web browser
//        // to open a website with more information about the selected earthquake.
//
////        String imgUrl = "data:image/png;base64,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";
////
////        ImageView imageView = (ImageView) findViewById(R.id.imageView);
////
////        Glide.with(this)
////                .load(imgUrl)
////                .into(imageView);
////        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////            @Override
////            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
////                // Find the current earthquake that was clicked on
////                Earthquake currentEarthquake = (Earthquake) mAdapter.getItem(position);
////
////                // Convert the String URL into a URI object (to pass into the Intent constructor)
////                Uri earthquakeUri = Uri.parse(currentEarthquake.getmUrl());
////
////                // Create a new intent to view the earthquake URI
////                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
////
////                // Send the intent to launch a new activity
////                startActivity(websiteIntent);
////            }
////        });
//
//        // Get a reference to the LoaderManager, in order to interact with loaders.
////        LoaderManager loaderManager = getLoaderManager();
//        Log.i(LOG_TAG,"TEST: calling initLoader() ...");
//        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
//        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
//        // because this activity implements the LoaderCallbacks interface).
////        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
//
//        ConnectivityManager connMgr = (ConnectivityManager)
//                getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        // Get details on the currently active default data network
//        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//
//        // If there is a network connection, fetch data
//        if (networkInfo != null && networkInfo.isConnected()) {
//            LoaderManager loaderManager = getLoaderManager();
//
//            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
//            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
//            // because this activity implements the LoaderCallbacks interface).
//            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
//        } else {
//            // Otherwise, display error
//            // First, hide loading indicator so error message will be visible
//            View loadingIndicator = findViewById(R.id.loading_indicator);
//            loadingIndicator.setVisibility(View.GONE);
//            mEmptyStateTextView.setText(R.string.no_internet_connection);
//        }
//    }
//
//    @Override
//    public Loader<List<WorldData>> onCreateLoader(int i, Bundle bundle) {
//        // Create a new loader for the given URL
//        Log.i(LOG_TAG,"TEST: onCreateLoader() called ...");
//        return new CovidLoader(this, USGS_REQUEST_URL);
//    }
//
//    @Override
//    public void onLoadFinished(Loader<List<WorldData>> loader, List<WorldData> worldDataList) {
//        // Clear the adapter of previous earthquake data
//        //mEmptyStateTextView.setText(R.string.no_earthquakes);
//
//        View loadingIndicator = findViewById(R.id.loading_indicator);
//        loadingIndicator.setVisibility(View.GONE);
//
//        // Set empty state text to display "No earthquakes found."
//        mEmptyStateTextView.setText(R.string.unable_to_load);
//
//        Log.i(LOG_TAG,"TEST: onLoadFinished() called ...");
//        mAdapter.clear();
//
//        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
//        // data set. This will trigger the ListView to update.
//        if (worldDataList != null && !worldDataList.isEmpty()) {
//            mAdapter.addAll(worldDataList);
//        }
//    }
//
//    @Override
//    public void onLoaderReset(Loader<List<WorldData>> loader) {
//        // Loader reset, so we can clear out our existing data.
//        Log.i(LOG_TAG,"TEST: onLoaderReset() called ...");
//        mAdapter.clear();
//    }
//}
public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(LOG_TAG,"TEST:Covid19 Activity onCreate() called");
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        Log.e(LOG_TAG,"TEST: CategoryAdapter is going to callcalled");


        CategoryAdapter adapter = new CategoryAdapter(this, getSupportFragmentManager());
        Log.e(LOG_TAG,"TEST: CategoryAdapter called");


        viewPager.setAdapter(adapter);
        Log.e(LOG_TAG,"TEST: Adapter is set to viewPager");


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}