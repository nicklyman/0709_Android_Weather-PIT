package com.epicodus.weatherpit.models;


public class Forecast {
    private String mWeekSummary;

public Forecast(String weekSummary) {
    this.mWeekSummary = weekSummary;
    }

    public String getDailySummary() {
        return mWeekSummary;
    }
}
