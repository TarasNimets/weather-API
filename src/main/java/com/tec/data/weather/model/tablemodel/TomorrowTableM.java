package com.tec.data.weather.model.tablemodel;

public final class TomorrowTableM extends TodayTableM {
    
    private static final TomorrowTableM GET_INSTANCE = new TomorrowTableM();

    private TomorrowTableM() {
    }

    public static final TomorrowTableM getInstance() {
        return GET_INSTANCE;
    }
}
