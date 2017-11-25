package com.tec.data.weather.controller;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.tec.data.weather.model.NowWeatherModel;
import com.tec.data.weather.model.tablemodel.SixDayTableM;
import com.tec.data.weather.model.tablemodel.TodayTableM;
import com.tec.data.weather.model.tablemodel.TomorrowTableM;
import com.tec.data.weather.model.tablemodel.TreeDayTableM;
import com.tec.data.weather.model.weather.Day;
import com.tec.data.weather.model.weather.Weather;
import com.tec.data.weather.utils.Error;

public final class SwingFrameManager {

    public static final int DEFAULT_WIDTH_FRAME = 1366;
    public static final int DEFAULT_HEIGHT_FRAME = 768;
    public static final String ENTER_NAME_OF_CITY = "Enter name of city";
    public static final String SIX_DAY = "SixDay";
    public static final String TREE_DAY = "TreeDay";
    public static final String NEXT_DAY = "nextDay";
    public static final String ONE_DAY = "oneDay";

    private static TableColumnModel columnModel;
    private static boolean previousResize = false;
    private JTable table;
    private static Weather weather;
    private int currentWidth;

    public SwingFrameManager(JTable table) {
        this.table = table;
    }

    public final void resizeTable(boolean needResize, int currentWidth) {
        this.currentWidth = currentWidth;
        if (!needResize) {
            if (!previousResize) {
                columnModel = table.getColumnModel();
                for (int i = 1; i < table.getColumnCount(); i++) {
                    columnModel.getColumn(i).setPreferredWidth(52);
                }
                columnModel.getColumn(0).setPreferredWidth(119);
                previousResize = true;
            }
        } else {
            double temp = ((double) currentWidth) / DEFAULT_WIDTH_FRAME;
            for (int i = 1; i < table.getColumnCount(); i++) {
                columnModel.getColumn(i).setPreferredWidth((int) (52 * temp));
            }
            columnModel.getColumn(0).setPreferredWidth((int) (119 * temp));
            previousResize = false;
        }
        table.setRowHeight(25);
        table.setRowHeight(2, 65);
    }

    public final void searchButtonPressed(JTextField cityNameField, NowWeatherModel model, JTextArea generalWTextArea,
            String previosButtonSelected) {
        String cityName = cityNameField.getText();
        if (cityName.contains(ENTER_NAME_OF_CITY) || cityName.isEmpty()) {
            Error.errorMessage("The city must be filled");
            return;
        }
        System.out.println(cityName);
        weather = WeatherSearcher.getWeather(cityName);
        if (weather == null)
            weather = WeatherSearcher.getWeather(CitySearcher.getCityName(cityName));
        if (weather == null)
            weather = WeatherSearcher.getWeather(CitySearcher.getCityGeometry(cityName));
        if (weather == null) {
            Error.warningMessage("This city is not supported");
            table.setModel(new DefaultTableModel());
            return;
        }
        NowWeather.setNowWeather(weather.getCurrent(), model);
        Day generalWeatherToday = weather.getForecast().getForecastday(0).getDay();
        Day generalWeatherTomorrow = weather.getForecast().getForecastday(1).getDay();

        generalWTextArea.setText(GeneralWeather.getGeneralWeather(generalWeatherToday, generalWeatherTomorrow));
        TableRenderer.getInstance().setWeather(weather);
        changeTableModel(previosButtonSelected);
    }

    public final void changeTableModel(String previosButtonSelected) {
        if (previosButtonSelected.equals(NEXT_DAY)) {
            table.setModel(TomorrowTableM.getInstance());
            if (weather != null)
                TomorrowTableM.getInstance().setWeather(weather, 1);
        } else if (previosButtonSelected.equals(ONE_DAY)) {
            table.setModel(TodayTableM.getInstance());
            if (weather != null)
                TodayTableM.getInstance().setWeather(weather, 0);
        } else if (previosButtonSelected.equals(TREE_DAY)) {
            table.setModel(TreeDayTableM.getInstance());
            if (weather != null)
                TreeDayTableM.getInstance().setWeather(weather, 0);
        } else if (previosButtonSelected.equals(SIX_DAY)) {
            table.setModel(SixDayTableM.getInstance());
            if (weather != null)
                SixDayTableM.getInstance().setWeather(weather, 0);
        }
        resizeTable(DEFAULT_WIDTH_FRAME <= currentWidth, currentWidth);
        table.updateUI();
    }

}
