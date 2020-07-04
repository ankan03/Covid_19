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


public final class StateQueryUtils {


    private static final String LOG_TAG = StateQueryUtils.class.getName();
    ;

    StateQueryUtils() {
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

    private static List<StateData> extractFeatureFromJson(String stateDataJSON) {
        if (TextUtils.isEmpty(stateDataJSON)) {
            return null;
        }

        List<StateData> stateDataUpdate = new ArrayList<>();

        try {

            JSONObject baseJsonResponse = new JSONObject(stateDataJSON);
            JSONArray statewise = baseJsonResponse.getJSONArray("statewise");

            for (int i = 1; i < statewise.length(); i++) {
                JSONObject currentState = statewise.getJSONObject(i);

                String active = currentState.getString("active");
                String confirmed = currentState.getString("confirmed");
                String deltaconfirmed = currentState.getString("deltaconfirmed");
                String recovered = currentState.getString("recovered");
                String deltarecovered = currentState.getString("deltarecovered");
                String deaths = currentState.getString("deaths");
                String deltadeaths = currentState.getString("deltadeaths");
                String state = currentState.getString("state");
                String statecode = currentState.getString("statecode");
                String statenotes = currentState.getString("statenotes");
                String lastupdatedtime = currentState.getString("lastupdatedtime");


                StateData stateData = new StateData(confirmed, deltaconfirmed, recovered, deltarecovered, deaths, deltadeaths, state, statecode, statenotes, lastupdatedtime, active);
                stateDataUpdate.add(stateData);

            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the districtDataUpdate JSON results", e);
        }
        return stateDataUpdate;
    }

    public static List<StateData> fetchStateData(String requestUrl) {
        Log.i(LOG_TAG, "TEST: fetchDistrictData() called ...");
        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<StateData> covid19StateUpdate = extractFeatureFromJson(jsonResponse);

        return covid19StateUpdate;
    }
}


