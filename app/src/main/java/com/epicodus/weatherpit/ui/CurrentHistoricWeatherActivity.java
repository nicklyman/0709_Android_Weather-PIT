package com.epicodus.weatherpit.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.weatherpit.Constants;
import com.epicodus.weatherpit.R;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CurrentHistoricWeatherActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.forecastLinkTextView) TextView mAPILink;
    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.sevenDayForecastButton) Button mSevenDayForecastButton;

//    private SharedPreferences mSharedPreferences;
//    private String mRecentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_historic_weather);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String cityState = intent.getStringExtra("cityState");
        mLocationTextView.setText(cityState);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mRecentLocation = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
//        if (mRecentLocation != null) {
//
//        }

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
}

