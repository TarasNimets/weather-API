package com.tec.data.weather.model.tablemodel;

import javax.swing.table.AbstractTableModel;

import com.tec.data.weather.model.weather.Forecastday;
import com.tec.data.weather.model.weather.Weather;

@SuppressWarnings("serial")
public class TodayTableM extends AbstractTableModel {

    private static final TodayTableM GET_INSTANCE = new TodayTableM();
    protected Weather weather;
    protected Forecastday forecastday;
    protected int hourNameIndex;
    
    protected TodayTableM() {
    }
    
    public static TodayTableM getInstance() {
        return GET_INSTANCE;
    }
    
    public void setWeather(Weather weather, int dayIndex) {
        this.weather = weather;
        forecastday = weather.getForecast().getForecastday(dayIndex);
    }

    @Override
    public final int getColumnCount() {
        return 25;
    }

    @Override
    public final int getRowCount() {
        return 16;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) return rowHeaders(rowIndex);
        if (weather != null) {
            if(columnIndex != 0) {
                    switch (rowIndex) {
                    case 0: return (hourNameIndex = columnIndex - 1) < 10 ? "0" + hourNameIndex + ":00" : hourNameIndex + ":00";
                    case 1: return forecastday.getHour(columnIndex - 1).getCloud();
                    case 2: return forecastday.getHour(columnIndex - 1).getCondition().getIcon();
                    case 3: return forecastday.getHour(columnIndex - 1).getPrecip_mm();
                    case 4: return forecastday.getHour(columnIndex - 1).getTemp_c();
                    case 5: return forecastday.getHour(columnIndex - 1).getFeelslike_c();
                    case 6: return forecastday.getHour(columnIndex - 1).getPressure_mb();
                    case 7: return forecastday.getHour(columnIndex - 1).getWind_kph();
                    case 8: return forecastday.getHour(columnIndex - 1).getWind_dir();
                    case 9: return forecastday.getHour(columnIndex - 1).getHumidity();
                    case 10: return forecastday.getHour(columnIndex - 1).getVis_km();
                    case 11: return forecastday.getHour(columnIndex - 1).getWindchill_c();
                    case 12: return forecastday.getHour(columnIndex - 1).getHeatindex_c();
                    case 13: return forecastday.getHour(columnIndex - 1).getDewpoint_c();
                    case 14: return forecastday.getHour(columnIndex - 1).getChance_of_rain();
                    case 15: return forecastday.getHour(columnIndex - 1).getChance_of_snow();
                    }
                }
            }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        return "";
    }
    
    protected final Object rowHeaders(int rowIndex) {
        switch (rowIndex) {
        case 0: return "Time, in hour  ";
        case 1: return "Cloudiness, %  ";
        case 2: return "Phenomenon  ";
        case 3: return "Precipitation, mm  ";
        case 4: return "Temperature, °C  ";
        case 5: return "Feelslike, °C  ";
        case 6: return "Pressure, mm Hg  ";
        case 7: return "Wind power, m/s  ";
        case 8: return "Direction  ";
        case 9: return "Humidity, %  ";
        case 10: return "Visibility, km  ";
        case 11: return "Wind chill, °C  ";
        case 12: return "Heat index, °C  ";
        case 13: return "Dew point, °C  ";
        case 14: return "Chance of rain, %  ";
        case 15: return "Chance of snow, %  ";
        }
        return "";
    }
}
