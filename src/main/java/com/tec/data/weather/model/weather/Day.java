package com.tec.data.weather.model.weather;

public final class Day {

    private double maxtemp_c;
    private double mintemp_c;
    private double avgtemp_c;
    private double maxwind_kph;
    private double totalprecip_mm;
    private double avgvis_km;
    private double avghumidity;
    private Condition condition;

    private Day() {
    }

    public double getMaxtemp_c() {
        return maxtemp_c;
    }

    public double getMintemp_c() {
        return mintemp_c;
    }

    public double getAvgtemp_c() {
        return avgtemp_c;
    }

    public double getMaxwind_kph() {
        int temp = (int) ((maxwind_kph / 3.6) * 10);
        return temp / 10.0;
    }

    public double getTotalprecip_mm() {
        return totalprecip_mm;
    }

    public double getAvgvis_km() {
        return avgvis_km;
    }

    public double getAvghumidity() {
        return avghumidity;
    }

    public Condition getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return "Day [maxtemp_c=" + maxtemp_c + ", mintemp_c=" + mintemp_c + ", avgtemp_c=" + avgtemp_c
                + ", maxwind_kph=" + maxwind_kph + ", totalprecip_mm=" + totalprecip_mm + ", avgvis_km=" + avgvis_km
                + ", avghumidity=" + avghumidity + ", condition=" + condition + "]";
    }

}
