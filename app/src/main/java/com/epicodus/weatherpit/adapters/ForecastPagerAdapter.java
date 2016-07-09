package com.epicodus.weatherpit.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.weatherpit.models.Forecast;
import com.epicodus.weatherpit.ui.DailyForecastDetailFragment;

import java.util.ArrayList;

public class ForecastPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Forecast> mForecasts;

    public ForecastPagerAdapter(FragmentManager fm, ArrayList<Forecast> forecasts) {
        super(fm);
        mForecasts = forecasts;
    }

    @Override
    public Fragment getItem(int position) {
        return DailyForecastDetailFragment.newInstance(mForecasts.get(position));
    }

    @Override
    public int getCount() {
        return mForecasts.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mForecasts.get(position).getDailyIcon(); //Want to convert unix time to date and use day of week
    }

}
