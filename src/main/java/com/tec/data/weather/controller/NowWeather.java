package com.tec.data.weather.controller;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import com.tec.data.weather.model.NowWeatherModel;
import com.tec.data.weather.model.weather.Current;

public final class NowWeather {

    private static long humidity;
    private static double wind;
    
    public static final void setNowWeather(Current current, NowWeatherModel model) {
        try {
            URL url = new URL("http:" + current.getCondition().getIcon());
            ImageIcon imageIcon = new ImageIcon(url);
            model.getIconLabel().setIcon(imageIcon);
            model.getTemperatureLabel().setText(" " + current.getTemp_c() + " °C  ");
            model.getFeelslikeTextLabel().setVisible(true);
            model.getFeelslikeLabel().setText(current.getFeelslike_c() + " °C    ");
            humidity = current.getHumidity();
            wind = current.getWind_kph();

            StringBuilder builder = new StringBuilder();
            
            if (current.getPrecip_mm() != 0) {
                builder.append(current.getCondition().getText()).append(" (precipitation ");
                builder.append(current.getPrecip_mm()).append(" mm), ");
            }else builder.append(current.getCondition().getText()).append(", ");
            if (humidity < 71) builder.append("low humidity (").append(humidity).append(" %), ");
            else if (humidity >= 71 && humidity < 86) builder.append("normal humidity (").append(humidity).append(" %), ");
            else if (humidity >= 86 ) builder.append("high humidity (").append(humidity).append(" %), ");
            if (wind < 2.8) builder.append("calm (").append(wind).append(" m/s)");
            else if (wind >= 2.8 && wind < 8.4) builder.append("quiet wind (").append(wind).append(" m/s)");
            else if (wind >= 8.4 && wind < 16.8) builder.append("light breeze (").append(wind).append(" m/s)");
            else if (wind >= 16.8 && wind < 28) builder.append("weak wind (").append(wind).append(" m/s)");
            else if (wind >= 28 && wind < 44.8) builder.append("moderate wind (").append(wind).append(" m/s)");
            else if (wind >= 44.8 && wind < 58.8) builder.append("fresh wind (").append(wind).append(" m/s)");
            else if (wind >= 58.8 && wind < 75.6) builder.append("strong wind (").append(wind).append(" m/s)");
            else if (wind >= 75.6) builder.append("storm wind (").append(wind).append(" m/s)");
            model.getOtherTextArea().setText(builder.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
