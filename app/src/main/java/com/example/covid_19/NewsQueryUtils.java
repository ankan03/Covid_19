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


public final class NewsQueryUtils {


    private static final String LOG_TAG = "NewsQueryUtils";

    NewsQueryUtils() {
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
            Log.e(LOG_TAG, "Problem retrieving the news data JSON results.", e);
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

    private static List<NewsData> extractFeatureFromJson(String newsJSON) {
        if (TextUtils.isEmpty(newsJSON)) {
            return null;
        }

        List<NewsData> newsUpdate = new ArrayList<>();

        try {


            JSONObject baseJsonResponse = new JSONObject(newsJSON);
            JSONArray articles = baseJsonResponse.getJSONArray("articles");

            for (int i = 0; i < articles.length(); i++) {
                JSONObject currentNews = articles.getJSONObject(i);
                JSONObject source = currentNews.getJSONObject("source");
                String name = source.getString("name");

                String author = currentNews.getString("author");
                String title = currentNews.getString("title");
                String description = currentNews.getString("description");
                String url = currentNews.getString("url");
                String urlToImage = currentNews.getString("urlToImage");
                String publishedAt = currentNews.getString("publishedAt");
                String content = currentNews.getString("urlToImage");


                NewsData newsData = new NewsData(name, author, title, description, url, urlToImage, publishedAt, content);
                newsUpdate.add(newsData);
            }
        } catch (JSONException e) {
            Log.e("NewsQueryUtils", "Problem parsing the covidUpdate JSON results", e);
        }
        return newsUpdate;
    }

    public static List<NewsData> fetchNewsData(String requestUrl) {
        Log.i(LOG_TAG, "TEST: fetchNewsData() called ...");

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
        List<NewsData> newsDataUpdate = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link Covid19}s
        return newsDataUpdate;
    }
}





