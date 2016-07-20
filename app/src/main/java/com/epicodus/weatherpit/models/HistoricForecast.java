package com.epicodus.weatherpit.models;


import android.content.Intent;
import android.content.res.AssetManager;
import android.util.Log;

import com.epicodus.weatherpit.services.HistoricForecastService;
import com.epicodus.weatherpit.ui.CurrentHistoricWeatherActivity;
import com.epicodus.weatherpit.ui.MainActivity;

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
public class HistoricForecast {
    private static final String TAG = HistoricForecast.class.getSimpleName();
    private long mHistoricTime;
    private String mHistoricDayOfWeek;
    private String mHistoricDailySummary;
    //    private String mHistoricHourlySummary;
    private double mHistoricMinTemp;
    private double mHistoricMaxTemp;
    private long mHistoricTimeOffset;
    private double mLatitude;
    private double mLongitude;

    public HistoricForecast() {}

    public HistoricForecast(long historicTime, String historicDailySummary, double minHistoricTemp, double maxHistoricTemp, long historicTimeOffset, double latitude, double longitude) {
        this.mHistoricTime = historicTime;
        this.mHistoricDailySummary = historicDailySummary;
//    this.mHistoricHourlySummary = historicHourlySummary;
        this.mHistoricMinTemp = minHistoricTemp;
        this.mHistoricMaxTemp = maxHistoricTemp;
        this.mHistoricTimeOffset = historicTimeOffset;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
    }

    public long getHistoricUnixTime() {
        return mHistoricTime;
    }

    public String getHistoricDailySummary() {
        return mHistoricDailySummary;
    }

//    public String getHistoricHourlySummary() { return mHistoricHourlySummary; }

    public double getHistoricDailyMinTemp() {
        return mHistoricMinTemp;
    }

    public double getHistoricDailyMaxTemp() {
        return mHistoricMaxTemp;
    }

    public long getHistoricTimeOffset() { return mHistoricTimeOffset; }

    public double getLatitude() { return mLatitude; }

    public double getLongitude() { return mLongitude; }




    public long getRandomYear() {
        Date currentDate = new Date(System.currentTimeMillis() * 1000L);
        long currentSeconds = currentDate.getTime();

        long timeOffset = mHistoricTimeOffset * (-1);
        long timeOffsetSeconds = timeOffset * 3600;
        long unixSeconds = (currentSeconds + timeOffsetSeconds) * 1000L; //trying with system time
//        long unixSeconds =  (mHistoricTime + timeOffsetSeconds) * 1000L;
        long randomYearMath = (long) ((Math.random()*59)+1) * 31536000;
        long randomYear = unixSeconds - randomYearMath;
        return randomYear;
    }
}





