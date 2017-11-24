package com.tec.data.weather.model.weather;

public final class Location {

    private String name;
    private String country;
    private String tz_id;

    private Location() {
    }

    @Override
    public String toString() {
        return "Location [name=" + name + ", country=" + country + ", tz_id=" + tz_id + "]";
    }

}
