package com.epicodus.weatherpit.models;


public class Forecast {
    private String mDaySummary;

public Forecast(String daySummary) {
    this.mDaySummary = daySummary;
    }

    public String getHourlySummary() {
        return mDaySummary;
    }
}
