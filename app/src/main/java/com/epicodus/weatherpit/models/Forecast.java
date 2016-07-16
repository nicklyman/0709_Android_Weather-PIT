package com.epicodus.weatherpit.models;


import android.content.res.AssetManager;
import android.util.Log;

import com.epicodus.weatherpit.ui.SevenDayForecastActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Parcel
public class Forecast {
    private static final String TAG = Forecast.class.getSimpleName();
    private long mTime;
    private String mDayOfWeek;
    private String mSummary;
//    private String mHourlySummary;
    private String mIcon;
    private double mMinTemp;
    private double mMaxTemp;
    private long mTimeOffset;
    private double mLatitude;
    private double mLongitude;

    public Forecast() {}

public Forecast(long time, String summary, String icon, double minTemp, double maxTemp, long timeOffset, double latitude, double longitude) {
    this.mTime = time;
    this.mSummary = summary;
//    this.mHourlySummary = hourlySummary;
    this.mIcon = icon;
    this.mMinTemp = minTemp;
    this.mMaxTemp = maxTemp;
    this.mTimeOffset = timeOffset;
    this.mLatitude = latitude;
    this.mLongitude = longitude;
    }

    public long getUnixTime() {
        return mTime;
    }

    public String getDailySummary() {
        return mSummary;
    }

//    public String getHourlySummary() { return mHourlySummary; }

    public String getDailyIcon() {
        return mIcon;
    }

    public double getDailyMinTemp() {
        return mMinTemp;
    }

    public double getDailyMaxTemp() {
        return mMaxTemp;
    }

    public long getTimeOffset() { return mTimeOffset; }

    public double getLatitude() { return mLatitude; }

    public double getLongitude() { return mLongitude; }


    public String getDayOfWeek() {
        long timeOffset = mTimeOffset * (-1);
        long timeOffsetSeconds = timeOffset * 3600;
        long unixSeconds =  (mTime + timeOffsetSeconds) * 1000L;
        DateFormat day = new SimpleDateFormat("EEEE");
        String dayOfWeek = day.format(new Date(unixSeconds));
        return dayOfWeek;
    }
}





