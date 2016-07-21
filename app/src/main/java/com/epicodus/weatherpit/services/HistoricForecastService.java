package com.epicodus.weatherpit.services;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.epicodus.weatherpit.Constants;
import com.epicodus.weatherpit.models.Forecast;
import com.epicodus.weatherpit.models.HistoricForecast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HistoricForecastService {
    public static void findHistoricForecast(Double lat, Double lng, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder().build();

//        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BaseURL).newBuilder();
//        urlBuilder.addQueryParameter(Constants.Latitude, "45.6770");
//        urlBuilder.addQueryParameter(Constants.Longitude, "-111.0429");
//        urlBuilder.addQueryParameter(Constants.Key_Prefix, Constants.Key);
//        String url = urlBuilder.build().toString();









//        **************** Not Working **********************
//        Bundle bundle = intent.getExtras();
//        long randomYear = bundle.getLong("randomYear", 0);



        String url = Constants.BaseURL + Constants.Key + "/" + lat + "," + lng + "," + Constants.Time;

        // Working example: https://api.forecast.io/forecast/4d67d511c3eed2b7be581fc31fe32cf9/37.8267,-122.423,1468556360

        Log.v("url: ", url);

        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<HistoricForecast> processResults(Response response) {
        ArrayList<HistoricForecast> historicForecasts = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject historicForecastServiceJSON = new JSONObject(jsonData);

                long historicTimeOffset = historicForecastServiceJSON.getLong("offset");
                double lat = historicForecastServiceJSON.getDouble("latitude");
                double lng = historicForecastServiceJSON.getDouble("longitude");

                JSONArray historicDailySummaryJSON = historicForecastServiceJSON.getJSONObject("daily").getJSONArray("data");
                for (int i = 0; i < historicDailySummaryJSON.length(); i++) {
                    JSONObject summaryHistoricForecastJSON = historicDailySummaryJSON.getJSONObject(i);
                    long historicTime = summaryHistoricForecastJSON.getLong("time");
                    String dailyHistoricSummary = summaryHistoricForecastJSON.getString("summary");
                    double minHistoricTemp = summaryHistoricForecastJSON.getDouble("temperatureMin");
                    double maxHistoricTemp = summaryHistoricForecastJSON.getDouble("temperatureMax");

                    HistoricForecast historicForecast = new HistoricForecast(historicTime, dailyHistoricSummary, minHistoricTemp, maxHistoricTemp, historicTimeOffset, lat, lng);
                    historicForecasts.add(historicForecast);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return historicForecasts;
    }
}