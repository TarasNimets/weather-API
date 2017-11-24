package com.tec.data.weather.controller;

import com.tec.data.weather.model.weather.Day;

public final class GeneralWeather {

    private GeneralWeather() {
    }

    public static String getGeneralWeather(Day generalWeatherToday, Day generalWeatherTomorrow) {

        StringBuilder dayBuilder = new StringBuilder();
        dayBuilder.append("    Today is expected to be ");
        dayBuilder.append(generalWeatherToday.getMintemp_c()).append(" - ");
        dayBuilder.append(generalWeatherToday.getMaxtemp_c()).append(" ° C, ");
        dayBuilder.append(generalWeatherToday.getCondition().getText()).append(", ");
        if (generalWeatherToday.getTotalprecip_mm() != 0) {
            dayBuilder.append("precipitation up to ").append(generalWeatherToday.getTotalprecip_mm()).append(" mm, ");
        }
        dayBuilder.append("wind gusts up to ").append(generalWeatherToday.getMaxwind_kph()).append(" m/s, humidity ");
        dayBuilder.append(generalWeatherToday.getAvghumidity()).append(" %.");

        dayBuilder.append(" Tomorrow: ");
        dayBuilder.append(generalWeatherTomorrow.getMintemp_c()).append(" - ");
        dayBuilder.append(generalWeatherTomorrow.getMaxtemp_c()).append(" ° C, ");
        dayBuilder.append(generalWeatherTomorrow.getCondition().getText()).append(", ");
        if (generalWeatherTomorrow.getTotalprecip_mm() != 0) {
            dayBuilder.append("precipitation up to ").append(generalWeatherTomorrow.getTotalprecip_mm())
                    .append(" mm, ");
        }
        dayBuilder.append("wind gusts up to ").append(generalWeatherTomorrow.getMaxwind_kph())
                .append(" m/s, humidity ");
        dayBuilder.append(generalWeatherTomorrow.getAvghumidity()).append(" %.");

        return dayBuilder.toString();
    }

}
