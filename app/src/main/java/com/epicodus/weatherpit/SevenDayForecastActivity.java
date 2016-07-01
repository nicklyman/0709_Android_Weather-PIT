package com.epicodus.weatherpit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SevenDayForecastActivity extends AppCompatActivity {
    private TextView mForecastLinkTextView;
    private ListView mForecastListView;
    private String[] forecast = new String[] {"Monday - ICON, 56° - 78°", "Tuesday - ICON, 52° - 76°", "Wednesday - ICON, 48° - 75°", "Thursday - ICON, 58° - 82°", "Friday - ICON, 64° - 88°", "Saturday - ICON, 62° - 84°", "Sunday - ICON, 60° - 78°"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven_day_forecast);
        mForecastLinkTextView = (TextView) findViewById(R.id.forecastLinkTextView);
        mForecastListView = (ListView) findViewById(R.id.forecastListView);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, forecast);
        mForecastListView.setAdapter(adapter);
        mForecastLinkTextView.setText(Html.fromHtml("Powered by Forecast<a href=\"http://forecast.io/\"></a>"));

    }
}
