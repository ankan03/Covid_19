package com.example.covid_19;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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

import static android.app.PendingIntent.getActivity;


public final class QueryUtilsHistory {


    private static final String LOG_TAG = "HistoryQueryUtils";

    QueryUtilsHistory() {

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

    private static List<HistoryData> extractFeatureFromJson(String historyDataJSON) {
        if (TextUtils.isEmpty(historyDataJSON)) {
            return null;
        }

        List<HistoryData> historyDataUpdate = new ArrayList<>();

        try {


            String a[] = {"an", "ap", "ar", "as", "br", "ch", "ct", "dd", "dl", "dn", "ga", "gj", "hp", "hr", "jh", "jk", "ka", "kl", "la", "ld", "mh", "ml", "mn", "mp", "mz", "nl", "or", "pb", "py", "rj", "sk", "tg", "tn", "tr", "up", "ut", "wb"};

            JSONObject baseJsonResponse = new JSONObject(historyDataJSON);

            JSONArray dailyDataHistory = baseJsonResponse.getJSONArray("states_daily");




                for (int i = dailyDataHistory.length() - 1; i > 0; i--) {
                    JSONObject dailyData = dailyDataHistory.getJSONObject(i);


                    String date = dailyData.getString("date");
                    String status = dailyData.getString("status");
                    for (int j = 0; j < a.length; j++) {

                        String noOfIssue = dailyData.getString(a[j]);

                        HistoryData historyData = new HistoryData(a[j], date, status, noOfIssue);
                        historyDataUpdate.add(historyData);

                    }
            }
        } catch (JSONException e) {
            Log.e("QueryUtilsHistory", "Problem parsing the historyDataUpdate JSON results", e);
        }
        return historyDataUpdate;
    }

    public static List<HistoryData> fetchHistoryData(String requestUrl) {
        Log.i(LOG_TAG, "TEST: fetchStateData() called ...");

        URL url = createUrl(requestUrl);
        String jsonResponse = null;

        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<HistoryData> covid19HistoryUpdate = extractFeatureFromJson(jsonResponse);

        return covid19HistoryUpdate;
    }
}


