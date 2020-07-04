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

public final class IndividualCountryHistoryQueryUtils {


    private static final String LOG_TAG = "QueryUtils";

    IndividualCountryHistoryQueryUtils() {
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


    private static List<IndividualCountryHistoryData> extractFeatureFromJson(String individualCountryHistoryJSON) {

        if (TextUtils.isEmpty(individualCountryHistoryJSON)) {
            return null;
        }

        List<IndividualCountryHistoryData> individualCountryHistoryIndividualCountryHistoryIndividualCountryHistoryData1 = new ArrayList<>();

        try {

            JSONArray baseJsonResponse = new JSONArray(individualCountryHistoryJSON);

            for (int i = baseJsonResponse.length() - 1; i > 0; i--) {

                JSONObject currentIndividualCountryHistory = baseJsonResponse.getJSONObject(i);

                String Confirmed = currentIndividualCountryHistory.getString("Confirmed");
                String Deaths = currentIndividualCountryHistory.getString("Deaths");
                String Recovered = currentIndividualCountryHistory.getString("Recovered");
                String Date = currentIndividualCountryHistory.getString("Date");
                String Active = currentIndividualCountryHistory.getString("Active");
                IndividualCountryHistoryData individualCountryHistoryData = new IndividualCountryHistoryData(Confirmed, Deaths, Recovered, Date, Active);

                individualCountryHistoryIndividualCountryHistoryIndividualCountryHistoryData1.add(individualCountryHistoryData);
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the Activity JSON results", e);
        }

        return individualCountryHistoryIndividualCountryHistoryIndividualCountryHistoryData1;
    }

    public static List<IndividualCountryHistoryData> fetchCovid19DataForIndividualCountry(String requestUrl) {
        Log.i(LOG_TAG, "TEST: fetchCovid19quakeData() called ...");


        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }


        List<IndividualCountryHistoryData> covid19Update = extractFeatureFromJson(jsonResponse);
        return covid19Update;
    }
}




