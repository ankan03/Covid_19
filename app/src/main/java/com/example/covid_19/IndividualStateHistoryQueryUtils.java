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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public final class IndividualStateHistoryQueryUtils {

    private static final String LOG_TAG = "QueryUtils";
    public static String stateCode;//,date, cases,recover,death;
    private static ArrayList<String> finalDateValues = new ArrayList<String>();

    IndividualStateHistoryQueryUtils() {
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


    private static List<IndividualStateHistoryData> extractFeatureFromJson(String individualStatetHistoryJSON) {

        if (TextUtils.isEmpty(individualStatetHistoryJSON)) {
            return null;
        }

        List<IndividualStateHistoryData> individualStateHistoryData1 = new ArrayList<>();

//        try {
////            String date="", cases="",recover="",death="";
////            String status[] = {"Confirmed","Recovered","Deceased"};
//
//            JSONObject baseJsonResponse = new JSONObject(individualStatetHistoryJSON);
//
//
//
//
//
//            Calendar calendar = Calendar.getInstance();
//            Date happyNewYearDate = calendar.getTime();
//            String abc = "2020-05-01";
//            ArrayList<Date> dates = (ArrayList<Date>) dateGenarater.generateDateBetween2Dates(abc,formatDate(happyNewYearDate));
//            for (int i=0;i<dates.size();i++){
//
//                String date="", cases="",recover="",death="";
//
//
//                JSONObject individualDate = baseJsonResponse.getJSONObject(formatDate(dates.get(dates.size()-i-2)));
//                JSONObject individualStateCode = individualDate.getJSONObject(stateCode.toUpperCase());
//
//                JSONObject total = individualStateCode.getJSONObject("total");
//                cases = total.getString("confirmed");
//                recover = total.getString("recovered");
//                death = total.getString("deceased");
//                date = formatDate(dates.get(dates.size()-i-1));
//
//                IndividualStateHistoryData individualStateHistoryData = new IndividualStateHistoryData(date, cases, recover, death);
//                individualStateHistoryData1.add(individualStateHistoryData);
//
//            }
//        }
        try {


            JSONObject baseJsonResponse = new JSONObject(individualStatetHistoryJSON);

            JSONArray dailyDataHistory = baseJsonResponse.getJSONArray("states_daily");


            for (int i = dailyDataHistory.length() - 1; i > 0; i--) {
                JSONObject dailyData = dailyDataHistory.getJSONObject(i);


                String date = dailyData.getString("date");
                String status = dailyData.getString("status");
                String noOfIssues = dailyData.getString(stateCode.toLowerCase());

                IndividualStateHistoryData individualStateHistoryData = new IndividualStateHistoryData(date, status, noOfIssues);
                individualStateHistoryData1.add(individualStateHistoryData);


            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the IndividualDistrictHistoryActivity JSON results", e);
        }

        return individualStateHistoryData1;
    }

    public static List<IndividualStateHistoryData> fetchCovid19DataForIndividualState(String requestUrl, String state_code) {
        stateCode = state_code;
        Log.i(LOG_TAG, "TEST: fetch<IndividualDistrictHistoryCovid19Data() called ...");


        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }


        List<IndividualStateHistoryData> covid19Update = extractFeatureFromJson(jsonResponse);
        return covid19Update;
    }


    private static String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(dateObject);
    }
}


//public final class IndividualStateHistoryQueryUtils {
//
//    private static final String LOG_TAG = "QueryUtils";
//
//    IndividualStateHistoryQueryUtils() {
//    }
//
//    public static List<IndividualStateHistoryData> fetchCovid19DataForIndividualState(String requestUrl,String state_name) throws JSONException {
////        stateName = state_name;
//        Log.i(LOG_TAG,"TEST: fetch<IndividualDistrictHistoryCovid19Data() called ...");
//
//
//
////        URL url = createUrl(requestUrl);
////
////        String jsonResponse = null;
////        try {
////            jsonResponse = makeHttpRequest(url);
////        } catch (IOException e) {
////            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
////        }
//
//
////        try {
////            OkHttpClient client = new OkHttpClient();
////            Request request = new Request.Builder()
////                    .url(urls[0])
////                    .build();
////            Response responses = null;
////
////            try {
////                responses = client.newCall(request).execute();
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////            String jsonData = responses.body().string();
////            JSONObject Jobject = new JSONObject(jsonData);
////            JSONArray Jarray = Jobject.getJSONArray("employees");
////
////            for (int i = 0; i < Jarray.length(); i++) {
////                JSONObject object     = Jarray.getJSONObject(i);
////            }
////        }
//
//
//
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(requestUrl)//"https://covid-19-india-data-by-zt.p.rapidapi.com/GetIndiaAllHistoricalDataForState?statecode=KA"
//                .get()
//                .addHeader("x-rapidapi-host", "covid-19-india-data-by-zt.p.rapidapi.com")
//                .addHeader("x-rapidapi-key", "8aa382d62bmsh1eca65d3b8ebbbbp19a53fjsn3b5ae14a0c73")
//                .build();
//
//        Response responses = null;
//        try {
//            responses = client.newCall(request).execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String jsonData = responses.body().toString();
////        JSONObject json = new JSONObject(jsonData);
////
////
////        JSONArray Jarray = json.getJSONArray("employees");
////
////        for (int i = 0; i < Jarray.length(); i++) {
////            JSONObject object     = Jarray.getJSONObject(i);
////        }
//
//
//
//
//
//
//
//
//
//        List<IndividualStateHistoryData> covid19Update = extractFeatureFromJson(jsonData);
//        return covid19Update;
//    }
//
//
//
//    private static List<IndividualStateHistoryData> extractFeatureFromJson(String individualStatetHistoryJSON) {
//
//        if (TextUtils.isEmpty(individualStatetHistoryJSON)) {
//            return null;
//        }
//
//        List<IndividualStateHistoryData> individualStateHistoryData1 = new ArrayList<>();
//
//        try {
//
//            JSONObject baseJsonResponse //= new JSONObject(individualStatetHistoryJSON);
//                                        =new JSONObject(individualStatetHistoryJSON.substring(individualStatetHistoryJSON.indexOf("{"), individualStatetHistoryJSON.lastIndexOf("}") + 1));
//            JSONArray individualState = baseJsonResponse.getJSONArray("records");
//
//            for (int i = 0; i < individualState.length(); i++) {
//
//                JSONObject currentIndividualStateHistory = individualState.getJSONObject(i);
//                 String date = currentIndividualStateHistory.getString("dateofrecord");
//
//                JSONObject currentState = currentIndividualStateHistory.getJSONObject("cases");
//
////                for (int j=0;j<regional.length();j++){
//
//
////                        String loc = currentState.getString("loc");
//                        String cases = currentState.getString("totalconfirmed");
//                        String recover = currentState.getString("totalrecovered");
//                        String death = currentState.getString("totaldeceased");
//                        IndividualStateHistoryData individualStateHistoryData = new IndividualStateHistoryData(date,cases, recover,death);
//                        individualStateHistoryData1.add(individualStateHistoryData);
//
////                }
//
//            }
//
//        } catch (JSONException e) {
//            Log.e(LOG_TAG, "Problem parsing the IndividualDistrictHistoryActivity JSON results", e);
//        }
//
//        return individualStateHistoryData1;
//    }
//
//}