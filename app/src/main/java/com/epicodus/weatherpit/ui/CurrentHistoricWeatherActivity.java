package com.epicodus.weatherpit.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.weatherpit.R;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CurrentHistoricWeatherActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.forecastLinkTextView) TextView mForecastLinkTextView;
    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.sevenDayForecastButton) Button mSevenDayForecastButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_historic_weather);
        ButterKnife.bind(this);
        mForecastLinkTextView.setText(Html.fromHtml("Powered by Forecast<a href=\"http://forecast.io/\"></a>"));
        Intent intent = getIntent();
        String cityState = intent.getStringExtra("cityState");
        mLocationTextView.setText(cityState);
        mSevenDayForecastButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == mSevenDayForecastButton) {
            Intent intent = new Intent(CurrentHistoricWeatherActivity.this, SevenDayForecastActivity.class);
            startActivity(intent);
        }
    }
}

