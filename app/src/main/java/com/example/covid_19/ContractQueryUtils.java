package com.example.covid_19;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public final class ContractQueryUtils {


    private static final String LOG_TAG = "ContractQueryUtils";

    ContractQueryUtils() {
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();


            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the contract data JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {

                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<ContractData> extractFeatureFromJson(String contractJSON) {
        if (TextUtils.isEmpty(contractJSON)) {
            return null;
        }

        List<ContractData> contractUpdate = new ArrayList<>();

        try {


            JSONObject baseJsonResponse = new JSONObject(contractJSON);
            String lastRefreshed = baseJsonResponse.getString("lastRefreshed");
            String lastOriginUpdate = baseJsonResponse.getString("lastOriginUpdate");

            JSONObject data = baseJsonResponse.getJSONObject("data");
            JSONObject contacts = data.getJSONObject("contacts");
            JSONObject primary = contacts.getJSONObject("primary");

            String number = primary.getString("number");
            String number_tollfree = primary.getString("number-tollfree");
            String email = primary.getString("email");
            String twitter = primary.getString("twitter");
            String facebook = primary.getString("facebook");


            ContractData abc = new ContractData(number, number_tollfree, email, twitter, facebook, lastRefreshed, lastOriginUpdate);
            contractUpdate.add(abc);

            JSONArray regional = contacts.getJSONArray("regional");

            for (int i = 0; i < regional.length(); i++) {
                JSONObject currentRegion = regional.getJSONObject(i);

                String loc = currentRegion.getString("loc");
                String individual_number = currentRegion.getString("number");


                ContractData contractData = new ContractData(loc, individual_number);
                contractUpdate.add(contractData);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the contract Update JSON results", e);
        }
        return contractUpdate;
    }

    public static List<ContractData> fetchContractData(String requestUrl) {
        Log.i(LOG_TAG, "TEST: fetchContractData() called ...");

//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Covid19}s
        List<ContractData> contractDataUpdate = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link Covid19}s
        return contractDataUpdate;
    }
}





