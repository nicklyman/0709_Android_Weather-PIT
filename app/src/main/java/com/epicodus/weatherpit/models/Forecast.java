package com.epicodus.weatherpit.models;


import android.util.Log;

import com.epicodus.weatherpit.ui.SevenDayForecastActivity;

import org.parceler.Parcel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Parcel
public class Forecast {
    private static final String TAG = Forecast.class.getSimpleName();
    private long mTime;
    private String mDayOfWeek;
    private String mSummary;
    private String mIcon;
    private double mMinTemp;
    private double mMaxTemp;

    public Forecast() {}

public Forecast(long time, String summary, String icon, double minTemp, double maxTemp) {
    this.mTime = time;
    this.mSummary = summary;
    this.mIcon = icon;
    this.mMinTemp = minTemp;
    this.mMaxTemp = maxTemp;
    }

    public long getUnixTime() {
        return mTime;
    }

    public String getDailySummary() {
        return mSummary;
    }

    public String getDailyIcon() {
        return mIcon;
    }

    public double getDailyMinTemp() {
        return mMinTemp;
    }

    public double getDailyMaxTemp() {
        return mMaxTemp;
    }


    public String getDayOfWeek() {
        String timeStamp = String.valueOf(mTime);
        long unixSeconds = Long.parseLong(timeStamp) * 1000L;
        DateFormat date = new SimpleDateFormat("EEEE");
        String dayOfWeek = date.format(new Date());
        Log.v(TAG, dayOfWeek);
        return dayOfWeek;
    }
}





