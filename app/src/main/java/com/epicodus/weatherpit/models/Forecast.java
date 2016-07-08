package com.epicodus.weatherpit.models;


public class Forecast {
    private String mSummary;
    private String mIcon;
    private double mMinTemp;
    private double mMaxTemp;

public Forecast(String summary, String icon, double minTemp, double maxTemp) {
    this.mSummary = summary;
    this.mIcon = icon;
    this.mMinTemp = minTemp;
    this.mMaxTemp = maxTemp;
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
