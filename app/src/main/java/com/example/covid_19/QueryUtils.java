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

public final class QueryUtils {


    private static final String LOG_TAG = "QueryUtils";

    QueryUtils() {
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

        // If the URL is null, then return early.
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

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
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
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
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

    /**
     * Return a list of {@link WorldData} objects that has been built up from
     * parsing the given JSON response.
     */
    private static List<WorldData> extractFeatureFromJson(String covid19JSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(covid19JSON)) {
            return null;
        }

        List<WorldData> covidUpdate = new ArrayList<>();

        try {

            // Create a JSONObject from the JSON response string
            JSONArray baseJsonResponse = new JSONArray(covid19JSON);

            for (int i=0;i<baseJsonResponse.length();i++){
                JSONObject currentCountry = baseJsonResponse.getJSONObject(i);

                String country = currentCountry.getString("country");
                String cases = currentCountry.getString("cases");
                String todayCases = currentCountry.getString("todayCases");
                String deaths = currentCountry.getString("deaths");
                String todayDeaths = currentCountry.getString("todayDeaths");
                Long recovered = currentCountry.getLong("recovered");
                String active = currentCountry.getString("active");


                JSONObject countryInfo = currentCountry.getJSONObject("countryInfo");
                String flag = countryInfo.getString("flag");

                WorldData worldData = new WorldData(country,flag,cases,todayCases,deaths,todayDeaths,recovered,active);
                covidUpdate.add(worldData);
                }
            }catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the covidUpdate JSON results", e);
        }
    return covidUpdate;
    }
    /**
     * Query the USGS dataset and return a list of {@link WorldData} objects.
     */
    public static List<WorldData> fetchCovid19Data(String requestUrl) {
        Log.i(LOG_TAG,"TEST: fetchCovid19quakeData() called ...");

//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Covid19}s
        List<WorldData> covid19Update = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link Covid19}s
        return covid19Update;
    }
}




