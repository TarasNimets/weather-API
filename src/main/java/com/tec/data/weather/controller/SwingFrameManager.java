package com.tec.data.weather.controller;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.tec.data.weather.model.NowWeatherModel;
import com.tec.data.weather.model.TableModel;
import com.tec.data.weather.model.weather.Day;
import com.tec.data.weather.model.weather.Weather;
import com.tec.data.weather.utils.Error;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class SwingFrameManager {

    private static Logger LOG = Logger.getLogger(SwingFrameManager.class.getName());

    public static final int DEFAULT_WIDTH_FRAME = 1366;
    public static final int DEFAULT_HEIGHT_FRAME = 768;
    public static final String ENTER_NAME_OF_CITY = "Enter name of city";
    public static final String SIX_DAY = "SixDay";
    public static final String TREE_DAY = "TreeDay";
    public static final String NEXT_DAY = "nextDay";
    public static final String ONE_DAY = "oneDay";
    public static final String ANY_PERIOD = "anyPeriod";
    public static final TableModel DEFAULT_TABLE_MODEL = new TableModel(); 

    private static TableColumnModel columnModel;
    private static boolean previousResize = false;
    private JTable table;
    private static Weather weather;
    private int currentWidth;
    public final TableModel nextDayTableModel = new TableModel(); 
    public final TableModel treeDaysTableModel = new TableModel(); 
    public final TableModel sixDaysTableModel = new TableModel();
    public final TableModel anyPeriodTableModel = new TableModel(); 

    
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
        table.setRowHeight(3, 65);
    }

    public final void searchButtonPressed(JTextField cityNameField, NowWeatherModel model, JTextArea generalWTextArea,
            String previosButtonSelected) {
        String cityName = cityNameField.getText();
        if (cityName.contains(ENTER_NAME_OF_CITY) || cityName.isEmpty()) {
            Error.errorMessage("The city must be filled");
            return;
        }
        LOG.log(Level.INFO, "City - {0}", cityName);
        weather = WeatherSearcher.getWeather(cityName);
        System.out.println(weather);
        if (weather == null)
            weather = WeatherSearcher.getWeather(CitySearcher.getCityName(cityName));
        if (weather == null)
            weather = WeatherSearcher.getWeather(CitySearcher.getCityGeometry());
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
            table.setModel(nextDayTableModel);
            if (weather != null)
                nextDayTableModel.setWeather(weather, 1, 1);
        } else if (previosButtonSelected.equals(ONE_DAY)) {
            table.setModel(DEFAULT_TABLE_MODEL);
            if (weather != null)
                DEFAULT_TABLE_MODEL.setWeather(weather, 0, 1);
        } else if (previosButtonSelected.equals(TREE_DAY)) {
            table.setModel(treeDaysTableModel);
            if (weather != null)
                treeDaysTableModel.setWeather(weather, 0, 3);
        } else if (previosButtonSelected.equals(SIX_DAY)) {
            table.setModel(sixDaysTableModel);
            if (weather != null)
                sixDaysTableModel.setWeather(weather, 0, 6);
        } else if (previosButtonSelected.equals(ANY_PERIOD)) {
            Error.errorMessage("not working yet");
//            table.setModel(anyPeriodTableModel);
//            if (weather != null)
//                anyPeriodTableModel.setWeather(weather, 0, 3);
        }
        resizeTable(DEFAULT_WIDTH_FRAME <= currentWidth, currentWidth);
        table.updateUI();
    }

}
