package com.epicodus.weatherpit.models;


import android.util.Log;

import org.parceler.Parcel;

import java.util.Calendar;

@Parcel
public class Forecast {
    private int mTime;
    private String mSummary;
    private String mIcon;
    private double mMinTemp;
    private double mMaxTemp;

    public Forecast() {}

public Forecast(int time, String summary, String icon, double minTemp, double maxTemp) {
    this.mTime = time;
    this.mSummary = summary;
    this.mIcon = icon;
    this.mMinTemp = minTemp;
    this.mMaxTemp = maxTemp;
    }

    public int getUnixTime() {
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
}
