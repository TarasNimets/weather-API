package com.tec.data.weather.model.weather;

public final class Weather {

    private Location location;
    private Current current;
    private Forecast forecast;

    private Weather() {
    }

    public Location getLocation() {
        return location;
    }

    public Current getCurrent() {
        return current;
    }

    public Forecast getForecast() {
        return forecast;
    }

    @Override
    public String toString() {
        return "Weather [location=" + location + ", current=" + current + ", forecast=" + forecast + "]";
    }

}
