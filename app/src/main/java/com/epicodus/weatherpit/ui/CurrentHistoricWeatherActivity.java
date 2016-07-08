package com.epicodus.weatherpit.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.weatherpit.R;
import com.epicodus.weatherpit.models.Forecast;
import com.epicodus.weatherpit.services.ForecastService;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CurrentHistoricWeatherActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = CurrentHistoricWeatherActivity.class.getSimpleName();
    @Bind(R.id.forecastLinkTextView) TextView mForecastLinkTextView;
    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.hourlyTempsListView) ListView mListView;
    @Bind(R.id.sevenDayForecastButton) Button mSevenDayForecastButton;

    public ArrayList<Forecast> mForecast = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_historic_weather_graphs);
        ButterKnife.bind(this);
        mForecastLinkTextView.setText(Html.fromHtml("Powered by Forecast<a href=\"http://forecast.io/\"></a>"));
        Intent intent = getIntent();
        String cityState = intent.getStringExtra("cityState");
        mLocationTextView.setText(cityState);
        mSevenDayForecastButton.setOnClickListener(this);

        getForecast(cityState);
    }

    private void getForecast(String cityState) {
        final ForecastService forecastService = new ForecastService();
        forecastService.findForecast(cityState, new Callback() {

        @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

        @Override
            public void onResponse(Call call, Response response) throws IOException {
                try{
                    String jsonData = response.body().string();
                    if (response.isSuccessful()) {
                        Log.v(TAG, jsonData);
                        mForecast = forecastService.processResults(jsonData);

                        CurrentHistoricWeatherActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String[] hourlyTemps = new String[mForecast.size()];
                                for (int i = 0; i < hourlyTemps.length; i++) {
                                    hourlyTemps[i] = mForecast.get(i).getHourlySummary();
                                }
                                ArrayAdapter adapter = new ArrayAdapter(CurrentHistoricWeatherActivity.this,
                                        android.R.layout.simple_list_item_1, hourlyTemps);
                                mListView.setAdapter(adapter);

                                for (Forecast forecast : mForecast) {
                                    Log.d(TAG, "Hourly temp: " + forecast.getHourlySummary());
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

    @Override
    public void onClick(View view) {
        if(view == mSevenDayForecastButton) {
            Intent intent = new Intent(CurrentHistoricWeatherActivity.this, SevenDayForecastActivity.class);
            startActivity(intent);
        }
    }
}
