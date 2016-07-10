package com.epicodus.weatherpit.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.weatherpit.R;
import com.epicodus.weatherpit.models.Forecast;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;


public class DailyForecastDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.dayOfWeekTextView) TextView mDayOfWeek;
    @Bind(R.id.dailySummaryTextView) TextView mDailySummary;
    @Bind(R.id.iconTextView) TextView mIcon;
    @Bind(R.id.weatherImageView) ImageView mWeatherIconPlaceholder;
    @Bind(R.id.highTemperatureTextView) TextView mHighTemperature;
    @Bind(R.id.lowTemperatureTextView) TextView mLowTemperature;
    @Bind(R.id.forecastLinkTextView) TextView mAPILink;

    private Forecast mForecast;


    public static DailyForecastDetailFragment newInstance(Forecast forecast) {
        DailyForecastDetailFragment dailyForecastDetailFragment = new DailyForecastDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("forecast", Parcels.wrap(forecast));
        dailyForecastDetailFragment.setArguments(args);
        return dailyForecastDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mForecast = Parcels.unwrap(getArguments().getParcelable("forecast"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_forecast_detail, container, false);
        ButterKnife.bind(this, view);

//        mDayOfWeek.setText(mForecast.getDailyTime());
        mDailySummary.setText(mForecast.getDailySummary());
        mIcon.setText(mForecast.getDailyIcon());
        Picasso.with(view.getContext()).load(R.drawable.icon_placeholder).into(mWeatherIconPlaceholder);
        mHighTemperature.setText(Double.toString(mForecast.getDailyMaxTemp()) + "° F");
        mLowTemperature.setText(Double.toString(mForecast.getDailyMinTemp()) + "° F");

        mAPILink.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == mAPILink) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://forecast.io/"));
            startActivity(webIntent);
        }
    }

}
