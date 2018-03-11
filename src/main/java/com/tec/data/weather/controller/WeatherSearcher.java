package com.tec.data.weather.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tec.data.weather.model.weather.Weather;

public class WeatherSearcher {

    private static Logger LOG = Logger.getLogger(WeatherSearcher.class.getName());

    private static final String MY_KEY = "28f71b2a28544ad7b19161553172410";
    private static final String STARTED_URL = "http://api.apixu.com/v1/forecast.json?key=";

    public static Weather getWeather(String city) {
        try {
            URL url = new URL(STARTED_URL + MY_KEY + "&q=" + city + "&days=10");
            System.out.println(url);
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder stringBuilder = new StringBuilder();
            while (scanner.hasNext()) {
                stringBuilder.append(scanner.nextLine());
            }
            scanner.close();
            return parseJSON(stringBuilder.toString());
        } catch (IOException e) {
            LOG.log(Level.INFO, "{0}", e.getMessage());
            return null;
        }
    }

    private static Weather parseJSON(String json) {
        if (json == null) return null;
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, Weather.class);
    }

}
