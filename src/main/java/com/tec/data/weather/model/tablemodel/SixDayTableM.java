package com.tec.data.weather.model.tablemodel;

import com.tec.data.weather.model.weather.Weather;

public final class SixDayTableM extends TodayTableM {

    private static final SixDayTableM GET_INSTANCE = new SixDayTableM();
    private int dayIndex;
    private int hourIndex;

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    private SixDayTableM() {
    }
    
    public static final SixDayTableM getInstance() {
        return GET_INSTANCE;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) return rowHeaders(rowIndex);
        if (weather != null) {

            if (columnIndex < 5) {
                hourIndex = 1;
                dayIndex = 0;
            }
            if (columnIndex >= 5 && columnIndex < 9) {
                hourIndex = 5;
                dayIndex = 1;
            }
            if (columnIndex >= 9 && columnIndex < 13) {
                hourIndex = 9;
                dayIndex = 2;
            }
            if (columnIndex >= 13 && columnIndex < 17) {
                hourIndex = 13;
                dayIndex = 3;
            }
            if (columnIndex >= 17 && columnIndex < 21) {
                hourIndex = 17;
                dayIndex = 4;
            }
            if (columnIndex >= 21) {
                hourIndex = 21;
                dayIndex = 5;
            }
            if(columnIndex != 0) {
                    switch (rowIndex) {
                    case 0: return (hourNameIndex = (columnIndex - hourIndex) * 6) < 10 ? "0" + hourNameIndex + ":00" : hourNameIndex + ":00";
                    case 1: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 6).getCloud();
                    case 2: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 6).getCondition().getIcon();
                    case 3: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 6).getPrecip_mm();
                    case 4: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 6).getTemp_c();
                    case 5: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 6).getFeelslike_c();
                    case 6: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 6).getPressure_mb();
                    case 7: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 6).getWind_kph();
                    case 8: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 6).getWind_dir();
                    case 9: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 6).getHumidity();
                    case 10: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 6).getVis_km();
                    case 11: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 6).getWindchill_c();
                    case 12: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 6).getHeatindex_c();
                    case 13: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 6).getDewpoint_c();
                    case 14: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 6).getChance_of_rain();
                    case 15: return weather.getForecast().getForecastday(dayIndex).getHour((columnIndex - hourIndex) * 6).getChance_of_snow();
                    }
                }
            }
        return "";
    }
  
}
