package com.tec.data.weather.model.weather;

import java.util.Arrays;

public final class Forecast {

    private Forecastday[] forecastday;

    private Forecast() {
    }

    public Forecastday[] getForecastday() {
        return forecastday;
    }

    public Forecastday getForecastday(int index) {
        if (index < 0 || index > 6) {
            System.out.println("Incorrect forecastday index");
            return null;
        }
        return forecastday[index];
    }

    @Override
    public String toString() {
        return "Forecast [forecastday=" + Arrays.toString(forecastday) + "]";
    }

}
