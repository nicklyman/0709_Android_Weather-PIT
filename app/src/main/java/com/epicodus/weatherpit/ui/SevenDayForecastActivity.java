package com.epicodus.weatherpit.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.weatherpit.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SevenDayForecastActivity extends AppCompatActivity {
    @Bind(R.id.forecastLinkTextView) TextView mForecastLinkTextView;
    @Bind(R.id.forecastListView) ListView mForecastListView;
    private String[] forecast = new String[] {"Monday - ICON, 56°F - 78°F", "Tuesday - ICON, 52°F - 76°F", "Wednesday - ICON, 48°F - 75°F", "Thursday - ICON, 58°F - 82°F", "Friday - ICON, 64°F - 88°F", "Saturday - ICON, 62°F - 84°F", "Sunday - ICON, 60°F - 78°F"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven_day_forecast);
        ButterKnife.bind(this);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, forecast);
        mForecastListView.setAdapter(adapter);
        mForecastLinkTextView.setText(Html.fromHtml("Powered by Forecast<a href=\"http://forecast.io/\"></a>"));

    }
}
