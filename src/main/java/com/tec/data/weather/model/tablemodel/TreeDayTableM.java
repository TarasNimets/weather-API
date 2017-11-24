package com.tec.data.weather.model.tablemodel;

import com.tec.data.weather.model.weather.Weather;

public final class TreeDayTableM extends TodayTableM {

    private static final TreeDayTableM GET_INSTANCE = new TreeDayTableM();
    private int dayIndex;
    private int hourIndex;
    
    private TreeDayTableM() {
    }

    public static final TreeDayTableM getInstance() {
        return GET_INSTANCE;
    }
    
    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) return rowHeaders(rowIndex);
        if (weather != null) {

            if (columnIndex < 9) {
                hourIndex = 1;
                dayIndex = 0;
            }
            if (columnIndex > 8 && columnIndex < 17) {
                hourIndex = 9;
                dayIndex = 1;
            }
            if (columnIndex >= 17) {
                hourIndex = 17;
                dayIndex = 2;
            }
            if(columnIndex != 0) {
                    switch (rowIndex) {
                    case 0: return (hourNameIndex = (columnIndex - hourIndex) * 3) < 10 ? "0" + hourNameIndex + ":00" : hourNameIndex + ":00";
                    case 1: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 3).getCloud();
                    case 2: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 3).getCondition().getIcon();
                    case 3: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 3).getPrecip_mm();
                    case 4: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 3).getTemp_c();
                    case 5: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 3).getFeelslike_c();
                    case 6: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 3).getPressure_mb();
                    case 7: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 3).getWind_kph();
                    case 8: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 3).getWind_dir();
                    case 9: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 3).getHumidity();
                    case 10: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 3).getVis_km();
                    case 11: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 3).getWindchill_c();
                    case 12: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 3).getHeatindex_c();
                    case 13: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 3).getDewpoint_c();
                    case 14: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 3).getChance_of_rain();
                    case 15: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 3).getChance_of_snow();
                    }
                }
            }
        return "";
    }

}
