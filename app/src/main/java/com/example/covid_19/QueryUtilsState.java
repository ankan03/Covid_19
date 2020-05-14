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


public final class QueryUtilsState {


    private static final String LOG_TAG = "QueryUtilsState";

    QueryUtilsState() {
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

            JSONArray baseJsonResponse = new JSONArray(stateDataJSON);

            for (int i=0;i<baseJsonResponse.length();i++){
                JSONObject currentState = baseJsonResponse.getJSONObject(i);

                String state = currentState.getString("state");
                String stateCode = currentState.getString("statecode");

                JSONArray districtData = currentState.getJSONArray("districtData");

                    for (int j=0;j<districtData.length();j++){
                        JSONObject districtDataObject = districtData.getJSONObject(j);

                        String district = districtDataObject.getString("district");

                        String confirmed = districtDataObject.getString("confirmed");
                        String deceased = districtDataObject.getString("deceased");
                        String recovered = districtDataObject.getString("recovered");

                        JSONObject todaysData = districtDataObject.getJSONObject("delta");
                        String todayConfirmed = todaysData.getString("confirmed");
                        String todayDeceased = todaysData.getString("deceased");
                        String todayRecovered = todaysData.getString("recovered");

                        StateData stateData = new StateData(state, stateCode,district,confirmed,todayConfirmed,deceased,todayDeceased,recovered,todayRecovered);
                        stateDataUpdate.add(stateData);
                    }
            }
        }catch (JSONException e) {
            Log.e("QueryUtilsState", "Problem parsing the stateDataUpdate JSON results", e);
        }
        return stateDataUpdate;
    }
    public static List<StateData> fetchStateData(String requestUrl) {
        Log.i(LOG_TAG,"TEST: fetchStateData() called ...");
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

