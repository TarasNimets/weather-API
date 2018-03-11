package com.tec.data.weather.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public final class CitySearcher {

    private static final String OK = "OK";
    private static Logger LOG = Logger.getLogger(CitySearcher.class.getName());

    private static final String STARTED_URL = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    private static final String KEY = "AIzaSyDc_-Hqs03hxRwxl4EGWduMq_WdpWsiV7o";
    private static final String LANGUAGE = "en";
    private static JSONObject jsonObject;
    private static String statusQuery;
    @SuppressWarnings("serial")
    private static final List<String> PATTERN_LIST_TYPES = new ArrayList<String>() {
        {
            add("locality");
            add("political");
        }
    };

    private CitySearcher() {
    }

    @SuppressWarnings("unchecked")
    public static String getCityName(String enteredCity) {
        retriveJsonObject(enteredCity);
        if (statusQuery == null || !statusQuery.equals(OK)) return "";
        JSONObject object = (JSONObject) ((JSONArray) jsonObject.get("results")).get(0);
        JSONArray jsonAddress = (JSONArray) object.get("address_components");
        for (Object iterator : jsonAddress) {
            JSONObject iterationObject = (JSONObject) iterator;
            JSONArray types = (JSONArray) iterationObject.get("types");
            if (types.containsAll(PATTERN_LIST_TYPES))
                return (String) iterationObject.get("long_name");
        }
        statusQuery = null;
        return "";
    }

    public static String getCityGeometry() {
        if (statusQuery == null || !statusQuery.equals(OK))
            return "";
        JSONObject object = (JSONObject) ((JSONArray) jsonObject.get("results")).get(0);
        JSONObject geometry = (JSONObject) object.get("geometry");
        JSONObject location = (JSONObject) geometry.get("location");
        double lat = (Double) location.get("lat");
        double lng = (Double) location.get("lng");
        int latTemp = (int) (lat * 10000);
        lat = latTemp / 100;
        lat /= 100;
        int lngTemp = (int) (lng * 10000);
        lng = lngTemp / 100;
        lng /= 100;
        return lat + "," + lng;
    }

    private static void retriveJsonObject(String enteredCity) {
        try {
            URL url = new URL(STARTED_URL + enteredCity + "&language=" + LANGUAGE + "&key=" + KEY);
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder stringBuilder = new StringBuilder();
            while (scanner.hasNext()) {
                stringBuilder.append(scanner.nextLine());
            }
            scanner.close();
            createJsonObject(stringBuilder.toString());
        } catch (IOException e) {
            LOG.log(Level.INFO, "{0}", e.getMessage());
        }
    }

    private static void createJsonObject(String response){
        try {
            JSONParser parser = new JSONParser();
            jsonObject = (JSONObject) parser.parse(response);
            statusQuery = (String) jsonObject.get("status");
        } catch (ParseException e){
            LOG.log(Level.INFO, "{0}", e.getMessage());
        }
    }
}
