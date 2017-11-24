package com.tec.data.weather.model;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public final class NowWeatherModel {

    private JLabel temperatureLabel;
    private JLabel feelslikeLabel;
    private JLabel feelslikeTextLabel;
    private JLabel iconLabel;
    private JTextArea otherTextArea;

    public NowWeatherModel(JLabel temperatureLabel, JLabel feelslikeLabel, JLabel feelslikeTextLabel, JLabel iconLabel,
            JTextArea otherTextArea) {
        this.temperatureLabel = temperatureLabel;
        this.feelslikeLabel = feelslikeLabel;
        this.feelslikeTextLabel = feelslikeTextLabel;
        this.iconLabel = iconLabel;
        this.otherTextArea = otherTextArea;
    }

    public JLabel getTemperatureLabel() {
        return temperatureLabel;
    }

    public JLabel getFeelslikeLabel() {
        return feelslikeLabel;
    }

    public JLabel getFeelslikeTextLabel() {
        return feelslikeTextLabel;
    }

    public JLabel getIconLabel() {
        return iconLabel;
    }

    public JTextArea getOtherTextArea() {
        return otherTextArea;
    }

}
