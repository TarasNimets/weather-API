package com.tec.data.weather.model.weather;

import java.util.Arrays;

public final class Forecastday {

    private long date_epoch;
    private Day day;
    private Hour[] hour;

    private Forecastday() {
    }

    public long getDate_epoch() {
        return date_epoch;
    }

    public Day getDay() {
        return day;
    }

    public Hour[] getHour() {
        return hour;
    }

    public Hour getHour(int index) {
        if (index < 0 || index > 25) {
            System.out.println("Incorrect hour index");
            return null;
        }
        return hour[index];
    }

    @Override
    public String toString() {
        return "Forecastday [date_epoch=" + date_epoch + ", day=" + day + ", hour=" + Arrays.toString(hour) + "]";
    }

}
