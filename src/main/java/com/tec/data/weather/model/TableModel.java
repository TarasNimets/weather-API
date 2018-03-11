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

    private static final String EMPTY_VALUE = "";
	private static final int CHANCE_OF_SNOW = 16;
	private static final int CHANCE_OF_RAIN = 15;
	private static final int DEW_POINT = 14;
	private static final int HEAT_INDEX = 13;
	private static final int WIND_CHILL = 12;
	private static final int VISIBILITY = 11;
	private static final int HUMIDITY = 10;
	private static final int DIRECTION = 9;
	private static final int WIND_POWER = 8;
	private static final int PRESSURE = 7;
	private static final int FEELSLIKE = 6;
	private static final int TEMPERATURE = 5;
	private static final int PRECIPITATION = 4;
	private static final int PHENOMENON = 3;
	private static final int CLOUDINESS = 2;
	private static final int TIME = 1;
	private static final int DAY = 0;
	private static final int ROW_NUMBER = 17;
	private static final int COLUMN_NUMBER = 25;
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
        for (int i = 0; i < COLUMN_NUMBER; i++) {
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
        return COLUMN_NUMBER;
    }

    @Override
    public final int getRowCount() {
        return ROW_NUMBER;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) return rowHeaders(rowIndex);
        if (weather != null) {
                switch (rowIndex) {
                case 0: {
                    if (previousDayIndex == -1) {
                        previousDayIndex = weatherIndexs.get(columnIndex).dayIndex;
                        return getWeekDay(previousDayIndex);
                    }
                    if (previousDayIndex == weatherIndexs.get(columnIndex).dayIndex) return EMPTY_VALUE;
                    else {
                        previousDayIndex = weatherIndexs.get(columnIndex).dayIndex;
                        return getWeekDay(previousDayIndex);
                    }
                }
                case TIME: return (hourNameIndex = (columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex) < 10 ? "0" + hourNameIndex + ":00" : hourNameIndex + ":00";
                case CLOUDINESS: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getCloud();
                case PHENOMENON: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getCondition().getIcon();
                case PRECIPITATION: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getPrecip_mm();
                case TEMPERATURE: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getTemp_c();
                case FEELSLIKE: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getFeelslike_c();
                case PRESSURE: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getPressure_mb();
                case WIND_POWER: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getWind_kph();
                case DIRECTION: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getWind_dir();
                case HUMIDITY: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getHumidity();
                case VISIBILITY: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getVis_km();
                case WIND_CHILL: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getWindchill_c();
                case HEAT_INDEX: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getHeatindex_c();
                case DEW_POINT: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getDewpoint_c();
                case CHANCE_OF_RAIN: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getChance_of_rain();
                case CHANCE_OF_SNOW: return weather.getForecast().getForecastday(weatherIndexs.get(columnIndex).dayIndex).getHour((columnIndex - weatherIndexs.get(columnIndex).hourIndex) * countDayIndex).getChance_of_snow();
                }
            }
        return EMPTY_VALUE;
    }

    private String getWeekDay(int dayIndex) {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_WEEK, dayIndex);
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.forLanguageTag("en"));
    }

    @Override
    public String getColumnName(int column) {
        return EMPTY_VALUE;
    }
    
    protected final Object rowHeaders(int rowIndex) {
        switch (rowIndex) {
        case DAY: return "Day  ";
        case TIME: return "Time, in hour  ";
        case CLOUDINESS: return "Cloudiness, %  ";
        case PHENOMENON: return "Phenomenon  ";
        case PRECIPITATION: return "Precipitation, mm  ";
        case TEMPERATURE: return "Temperature, °C  ";
        case FEELSLIKE: return "Feelslike, °C  ";
        case PRESSURE: return "Pressure, mm Hg  ";
        case WIND_POWER: return "Wind power, m/s  ";
        case DIRECTION: return "Direction  ";
        case HUMIDITY: return "Humidity, %  ";
        case VISIBILITY: return "Visibility, km  ";
        case WIND_CHILL: return "Wind chill, °C  ";
        case HEAT_INDEX: return "Heat index, °C  ";
        case DEW_POINT: return "Dew point, °C  ";
        case CHANCE_OF_RAIN: return "Chance of rain, %  ";
        case CHANCE_OF_SNOW: return "Chance of snow, %  ";
        }
        return EMPTY_VALUE;
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
