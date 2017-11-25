package com.tec.data.weather.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.tec.data.weather.model.weather.Weather;

@SuppressWarnings("serial")
public class TableModel extends AbstractTableModel {

    protected Weather weather;
    protected int hourNameIndex;
    protected int countDayIndex;
    protected int columnLimit;
    private int dayIndex;
    private int hourIndex;
    private int tempStep;
    private List<WeatherIndex> weatherIndexs;   

    
    public void setWeather(Weather weather, int firstDayIndex, int countDayIndex) {
        this.weather = weather;
        this.dayIndex = firstDayIndex;
        this.countDayIndex = countDayIndex;
        this.columnLimit = 24 / countDayIndex;
        initList();
    }
    
    private void initList() {
        hourIndex = 1;
        tempStep = 1;
        weatherIndexs = new ArrayList<>();
        System.out.println(dayIndex);
        for (int i = 0; i < 25; i++) {
            if (i > (columnLimit * tempStep)) {
                dayIndex++;
                hourIndex += columnLimit;
                tempStep++;
            }
            weatherIndexs.add(new WeatherIndex(dayIndex, hourIndex));
        }
        System.out.println(weatherIndexs);
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
            weatherIndexs.get(columnIndex);
            if(columnIndex != 0) {
                    switch (rowIndex) {
                    case 0: return (hourNameIndex = (columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex) < 10 ? "0" + hourNameIndex + ":00" : hourNameIndex + ":00";
                    case 1: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getCloud();
                    case 2: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getCondition().getIcon();
                    case 3: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getPrecip_mm();
                    case 4: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getTemp_c();
                    case 5: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getFeelslike_c();
                    case 6: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getPressure_mb();
                    case 7: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getWind_kph();
                    case 8: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getWind_dir();
                    case 9: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getHumidity();
                    case 10: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getVis_km();
                    case 11: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getWindchill_c();
                    case 12: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getHeatindex_c();
                    case 13: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getDewpoint_c();
                    case 14: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getChance_of_rain();
                    case 15: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getChance_of_snow();
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

class WeatherIndex {
    
    public int dayIndex;
    public int hourIndex;
    
    public WeatherIndex(int dayIndex, int hourIndex) {
        this.dayIndex = dayIndex;
        this.hourIndex = hourIndex;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("WeatherIndex [dayIndex=");
        builder.append(dayIndex);
        builder.append(", hourIndex=");
        builder.append(hourIndex);
        builder.append("]");
        return builder.toString();
    }
    
}
