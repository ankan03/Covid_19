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


public final class IndividualDistrictHistoryQueryUtils {

    private static final String LOG_TAG = "QueryUtils";
    private static String districtName, stateName;

    IndividualDistrictHistoryQueryUtils() {
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
            Log.e(LOG_TAG, "Problem retrieving the covid19 data JSON results.", e);
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


    private static List<IndividualDistrictHistoryData> extractFeatureFromJson(String individualDistrictHistoryJSON) {

        if (TextUtils.isEmpty(individualDistrictHistoryJSON)) {
            return null;
        }

        List<IndividualDistrictHistoryData> individualDistrictHistoryData1 = new ArrayList<>();

        try {

            JSONObject baseJsonResponse = new JSONObject(individualDistrictHistoryJSON);
            JSONObject districtsDaily = baseJsonResponse.getJSONObject("districtsDaily");
            JSONObject stateNameOfCurrentDistrict = districtsDaily.getJSONObject(stateName);
            JSONArray individualDistrict = stateNameOfCurrentDistrict.getJSONArray(districtName);

            for (int i = individualDistrict.length() - 1; i > 0; i--) {

                JSONObject currentIndividualDistrictHistory = individualDistrict.getJSONObject(i);

                String Confirmed = currentIndividualDistrictHistory.getString("confirmed");
                String Deaths = currentIndividualDistrictHistory.getString("deceased");
                String Recovered = currentIndividualDistrictHistory.getString("recovered");
                String Date = currentIndividualDistrictHistory.getString("date");
                String active = currentIndividualDistrictHistory.getString("active");

                IndividualDistrictHistoryData individualDistrictHistoryData = new IndividualDistrictHistoryData(Confirmed, Deaths, Recovered, Date, active);

                individualDistrictHistoryData1.add(individualDistrictHistoryData);
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the IndividualDistrictHistoryActivity JSON results", e);
        }

        return individualDistrictHistoryData1;
    }

    public static List<IndividualDistrictHistoryData> fetchCovid19DataForIndividualDistrict(String requestUrl, String district, String state) {
        districtName = district;
        stateName = state;
        Log.i(LOG_TAG, "TEST: fetch<IndividualDistrictHistoryCovid19Data() called ...");


        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }


        List<IndividualDistrictHistoryData> covid19Update = extractFeatureFromJson(jsonResponse);
        return covid19Update;
    }
}





