package com.epicodus.weatherpit.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.weatherpit.R;
import com.epicodus.weatherpit.adapters.ForecastListAdapter;
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
//    @Bind(R.id.forecastLinkTextView) TextView mForecastLinkTextView;
//    @Bind(R.id.forecastListView) ListView mForecastListView;
//    @Bind(R.id.locationTextView) TextView mLocationTextView;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private ForecastListAdapter mAdapter;

    public ArrayList<Forecast> mForecasts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven_day_forecast);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mForecasts);
//        mForecastListView.setAdapter(adapter);

//        mForecastLinkTextView.setText(Html.fromHtml("Powered by Forecast<a href=\"http://forecast.io/\"></a>"));

        Intent intent = getIntent();
        String cityState = intent.getStringExtra("cityState");
//        mLocationTextView.setText(cityState);

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
            public void onResponse(Call call, Response response) {
                mForecasts = forecastService.processResults(response);

                SevenDayForecastActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new ForecastListAdapter(getApplicationContext(), mForecasts);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SevenDayForecastActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}

