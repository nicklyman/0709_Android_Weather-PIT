package com.epicodus.weatherpit.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.weatherpit.Constants;
import com.epicodus.weatherpit.R;
import com.epicodus.weatherpit.adapters.ForecastListAdapter;
import com.epicodus.weatherpit.models.Forecast;
import com.epicodus.weatherpit.models.HistoricForecast;
import com.epicodus.weatherpit.services.HistoricForecastService;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CurrentHistoricWeatherActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = CurrentHistoricWeatherActivity.class.getSimpleName();
    @Bind(R.id.forecastLinkTextView) TextView mAPILink;
    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.sevenDayForecastButton) Button mSevenDayForecastButton;
    @Bind(R.id.historicWeatherTextView) TextView mHistoricWeatherTextView;
    @Bind(R.id.currentWeatherTextView) TextView mCurrentWeatherTextView;

    public ArrayList<HistoricForecast> mHistoricForecasts = new ArrayList<>();

    private HistoricForecast mHistoricForecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_historic_weather);
        ButterKnife.bind(this);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mHistoricForecasts);

        Intent intent = getIntent();
        double lat = intent.getDoubleExtra("lat", 0.0);
        double lng = intent.getDoubleExtra("lng", 0.0);

        getHistoricDailySummary(lat, lng);

        mSevenDayForecastButton.setOnClickListener(this);
        mAPILink.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == mSevenDayForecastButton) {
            Intent intent = new Intent(CurrentHistoricWeatherActivity.this, SevenDayForecastActivity.class);
            startActivity(intent);
        }
        if (view == mAPILink) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://forecast.io/"));
            startActivity(webIntent);
        }
    }


    private void getHistoricDailySummary(Double lat, Double lng) {
        final HistoricForecastService historicForecastService = new HistoricForecastService();
        historicForecastService.findHistoricForecast(lat, lng, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mHistoricForecasts = historicForecastService.processResults(response);
                Log.v(TAG, mHistoricForecasts.get(0).getHistoricDailySummary());

                mHistoricWeatherTextView.setText("The weather on this date in year XXXX was: " + mHistoricForecasts.get(0).getHistoricDailySummary() + ". The high for the day was " + mHistoricForecasts.get(0).getHistoricDailyMaxTemp() + "°F and the low was " + mHistoricForecasts.get(0).getHistoricDailyMinTemp() + "°F.");



                CurrentHistoricWeatherActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }

                });

            }
        });

    }
}

