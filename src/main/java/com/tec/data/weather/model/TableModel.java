package com.tec.data.weather.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.swing.table.AbstractTableModel;

import com.tec.data.weather.model.weather.Weather;

@SuppressWarnings("serial")
public class TableModel extends AbstractTableModel {

    private Weather weather;
    private int hourNameIndex;
    private int countDayIndex;
    private int columnLimit;
    private int dayIndex;
    private int hourIndex;
    private List<WeatherIndex> weatherIndexs;
    private int previousDayIndex;

    
    public void setWeather(Weather weather, int firstDayIndex, int countDayIndex) {
        this.weather = weather;
        this.dayIndex = firstDayIndex;
        this.countDayIndex = countDayIndex;
        this.columnLimit = 24 / countDayIndex;
        initList();
        previousDayIndex = -1;
    }
    
    private void initList() {
        hourIndex = 1;
        int stepDay = 1;
        weatherIndexs = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            if (i > (columnLimit * stepDay)) {
                dayIndex++;
                hourIndex += columnLimit;
                stepDay++;
            }
            weatherIndexs.add(new WeatherIndex(dayIndex, hourIndex));
        }
    }

    @Override
    public final int getColumnCount() {
        return 25;
    }

    @Override
    public final int getRowCount() {
        return 17;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) return rowHeaders(rowIndex);
        if (weather != null) {
            if(columnIndex != 0) {
                    switch (rowIndex) {
                    case 0: { 
                        if (previousDayIndex == -1) {
                            previousDayIndex = weatherIndexs.get(columnIndex).dayIndex;
                            return getWeekDay(previousDayIndex);
                        }
                        if (previousDayIndex == weatherIndexs.get(columnIndex).dayIndex) return "";
                        else {
                            previousDayIndex = weatherIndexs.get(columnIndex).dayIndex;
                            return getWeekDay(previousDayIndex);
                        }
                    }
                    case 1: return (hourNameIndex = (columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex) < 10 ? "0" + hourNameIndex + ":00" : hourNameIndex + ":00";
                    case 2: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getCloud();
                    case 3: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getCondition().getIcon();
                    case 4: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getPrecip_mm();
                    case 5: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getTemp_c();
                    case 6: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getFeelslike_c();
                    case 7: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getPressure_mb();
                    case 8: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getWind_kph();
                    case 9: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getWind_dir();
                    case 10: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getHumidity();
                    case 11: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getVis_km();
                    case 12: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getWindchill_c();
                    case 13: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getHeatindex_c();
                    case 14: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getDewpoint_c();
                    case 15: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getChance_of_rain();
                    case 16: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getChance_of_snow();
                    }
                }
            }
        return "";
    }

    private String getWeekDay(int dayIndex) {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_WEEK, dayIndex);
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.forLanguageTag("en"));
    }

    @Override
    public String getColumnName(int column) {
        return "";
    }
    
    protected final Object rowHeaders(int rowIndex) {
        switch (rowIndex) {
        case 0: return "Day  ";
        case 1: return "Time, in hour  ";
        case 2: return "Cloudiness, %  ";
        case 3: return "Phenomenon  ";
        case 4: return "Precipitation, mm  ";
        case 5: return "Temperature, °C  ";
        case 6: return "Feelslike, °C  ";
        case 7: return "Pressure, mm Hg  ";
        case 8: return "Wind power, m/s  ";
        case 9: return "Direction  ";
        case 10: return "Humidity, %  ";
        case 11: return "Visibility, km  ";
        case 12: return "Wind chill, °C  ";
        case 13: return "Heat index, °C  ";
        case 14: return "Dew point, °C  ";
        case 15: return "Chance of rain, %  ";
        case 16: return "Chance of snow, %  ";
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
