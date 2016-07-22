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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    @Bind(R.id.randomYearTextView) TextView mRandomYearTextView;
    @Bind(R.id.historicTemperaturesTextView) TextView mHistoricTemperatureTextView;

    public ArrayList<HistoricForecast> mHistoricForecasts = new ArrayList<>();

    private HistoricForecast mHistoricForecast;

    public double lat;
    public double lng;

    private String formattedYear;
    private String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_historic_weather);
        ButterKnife.bind(this);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mHistoricForecasts);

        Intent intent = getIntent();
        cityName = intent.getStringExtra("cityName");
        double lat = intent.getDoubleExtra("lat", 0.0);
        double lng = intent.getDoubleExtra("lng", 0.0);
        long randomYear = getRandomYear();
        Log.v("randomYear: ", String.valueOf(randomYear));
        Date date = new Date(randomYear * 1000L);
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        formattedYear = year.format(date);

        Log.v("formatted year: ", formattedYear);

        getHistoricDailySummary(lat, lng, randomYear);

        mSevenDayForecastButton.setOnClickListener(this);
        mAPILink.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == mSevenDayForecastButton) {
            Bundle coordinates = getIntent().getExtras();
            lat = coordinates.getDouble("lat");
            lng = coordinates.getDouble("lng");
            Intent intent = new Intent(CurrentHistoricWeatherActivity.this, SevenDayForecastActivity.class);
            intent.putExtra("lat", lat);
            intent.putExtra("lng", lng);
            startActivity(intent);
        }
        if (view == mAPILink) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://forecast.io/"));
            startActivity(webIntent);
        }
    }


    private void getHistoricDailySummary(Double lat, Double lng, long randomYear) {
        final HistoricForecastService historicForecastService = new HistoricForecastService();

        historicForecastService.findHistoricForecast(lat, lng, randomYear, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mHistoricForecasts = historicForecastService.processResults(response);

                CurrentHistoricWeatherActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRandomYearTextView.setText(cityName + ": " + formattedYear);
                        mHistoricWeatherTextView.setText("The weather around this time of year in was: " + mHistoricForecasts.get(0).getHistoricDailySummary());
                        mHistoricTemperatureTextView.setText("The high temperature was " + mHistoricForecasts.get(0).getHistoricDailyMaxTemp() + "°F.\r\n The low temperature was " + mHistoricForecasts.get(0).getHistoricDailyMinTemp() + "°F.");
                    }

                });
            }
        });
    }

    public long getRandomYear() {

        long currentSeconds = System.currentTimeMillis()/1000L;
        Log.v("currentSeconds: ", String.valueOf(currentSeconds));
        long randomYearMath = (long) ((Math.random()*59)+1) * 31536000;
        long randomYear = currentSeconds - randomYearMath;
        return randomYear;
    }
}

