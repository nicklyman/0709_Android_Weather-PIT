package com.epicodus.weatherpit.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.weatherpit.R;
import com.epicodus.weatherpit.models.Forecast;
import com.epicodus.weatherpit.services.ForecastService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SevenDayForecastActivity extends AppCompatActivity {
    public static final String TAG = SevenDayForecastActivity.class.getSimpleName();
    @Bind(R.id.forecastLinkTextView) TextView mForecastLinkTextView;
    @Bind(R.id.forecastListView) ListView mForecastListView;
    @Bind(R.id.locationTextView) TextView mLocationTextView;

    public ArrayList<Forecast> mForecasts = new ArrayList<>();

    private String[] forecasts = new String[] {"Monday - ICON, 56°F - 78°F", "Tuesday - ICON, 52°F - 76°F", "Wednesday - ICON, 48°F - 75°F", "Thursday - ICON, 58°F - 82°F", "Friday - ICON, 64°F - 88°F", "Saturday - ICON, 62°F - 84°F", "Sunday - ICON, 60°F - 78°F"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven_day_forecast);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, forecasts);
        mForecastListView.setAdapter(adapter);

        mForecastLinkTextView.setText(Html.fromHtml("Powered by Forecast<a href=\"http://forecast.io/\"></a>"));

        Intent intent = getIntent();
        String cityState = intent.getStringExtra("cityState");
        mLocationTextView.setText(cityState);

        getDailySummary(cityState);
    }

    private void getDailySummary(String cityState) {
        final ForecastService forecastService = new ForecastService();
        forecastService.findForecast(cityState, new Callback() {

        @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

        @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    if (response.isSuccessful()) {
                        Log.v(TAG, jsonData);
                        mForecasts = forecastService.processResults(jsonData);

                        SevenDayForecastActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String[] dailyForecast = new String[mForecasts.size()];
                                for (int i = 0; i < dailyForecast.length; i++) {
                                    dailyForecast[i] = mForecasts.get(i).getDailySummary();
                                }
                                ArrayAdapter adapter = new ArrayAdapter(SevenDayForecastActivity.this,
                                        android.R.layout.simple_list_item_1, dailyForecast);
                                mForecastListView.setAdapter(adapter);

                                for (Forecast forecast : mForecasts) {
                                    Log.d(TAG, "Daily forecast: " + forecast.getDailySummary());
                                }
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
