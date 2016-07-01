package com.epicodus.weatherpit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CurrentHistoricWeatherGraphs extends AppCompatActivity {
    private TextView mLocationTextView;
    private Button mSevenDayForecastButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_historic_weather_graphs);
        mLocationTextView = (TextView) findViewById(R.id.locationTextView);
        mSevenDayForecastButton = (Button) findViewById(R.id.sevenDayForecastButton);
        Intent intent = getIntent();
        String cityState = intent.getStringExtra("cityState");
        mLocationTextView.setText(cityState);
        mSevenDayForecastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CurrentHistoricWeatherGraphs.this, SevenDayForecastActivity.class);
                startActivity(intent);
            }
        });
    }
}
