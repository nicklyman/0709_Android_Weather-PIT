package com.epicodus.weatherpit.services;


import android.util.Log;

import com.epicodus.weatherpit.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ForecastService {
    public static void findForecast(String userInputEditText, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder().build();

//        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BaseURL).newBuilder();
//        urlBuilder.addQueryParameter(Constants.Latitude, "45.6770");
//        urlBuilder.addQueryParameter(Constants.Longitude, "-111.0429");
//        urlBuilder.addQueryParameter(Constants.Key_Prefix, Constants.Key);
//        String url = urlBuilder.build().toString();

        String url = Constants.BaseURL + Constants.Key + "/" + Constants.Latitude + "," + Constants.Longitude;

        // Working example: https://api.forecast.io/forecast/4d67d511c3eed2b7be581fc31fe32cf9/37.8267,-122.423

        Log.v("url: ", url);

        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Forecast> processResults(String jsonData) {
        ArrayList<Forecast> forecasts = new ArrayList<>();

        try {
            JSONObject forecastServiceJSON = new JSONObject(jsonData);
            JSONArray resultsJSON = forecastServiceJSON.getJSONArray("results");
            for (int i = 0; i < resultsJSON.length(); i++) {
                JSONObject dailyForecastJSON = resultsJSON.getJSONObject(i);
                String hourlyTemperature = dailyForecastJSON("hourly");

                Forecast forecast = new Forecast(hourlyTemperature);
                forecasts.add(forecast);
            }
        } catch(JSONException e) {
            e.printStackTrace();
        }
        return forecasts;
    }
}
