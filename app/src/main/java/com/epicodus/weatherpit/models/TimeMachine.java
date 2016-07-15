package com.epicodus.weatherpit.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeMachine {
    private static final String TAG = Forecast.class.getSimpleName();
    private long mTime;
    private String mSummary;
    private String mIcon;
    private double mMinTemp;
    private double mMaxTemp;

    public TimeMachine() {}

    public TimeMachine(long time, String summary, String icon, double minTemp, double maxTemp) {
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


    public String getRandomYear() {
        long sevenHours = 25200;
        long unixSeconds =  (mTime + sevenHours) * 1000L;
        DateFormat year = new SimpleDateFormat("YYYY");
        String randomYear = year.format(new Date(unixSeconds));
        return randomYear;
    }
}
