package com.epicodus.weatherpit.models;


public class Forecast {
    private String mHourly;
    private String mDaily;

public Forecast(String hourly, String daily) {
    this.mDaily = daily;
    this.mHourly = hourly;
    }

    public String getHourlyTemps() {
        return mHourly;
    }
    public String getDailyTemps() {
        return mDaily;
    }
}
