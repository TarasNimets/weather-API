package com.tec.data.weather.model.weather;

public final class Current {

    private double temp_c;
    private Condition condition;
    private double wind_kph;
    private long wind_degree;
    private String wind_dir;
    private double pressure_mb;
    private double precip_mm;
    private long humidity;
    private long cloud;
    private double feelslike_c;
    private double vis_km;

    private Current() {
    }

    public double getTemp_c() {
        return temp_c;
    }

    public Condition getCondition() {
        return condition;
    }

    public double getWind_kph() {
        int temp = (int) ((wind_kph / 3.6) * 10);
        return temp / 10.0;
    }

    public long getWind_degree() {
        return wind_degree;
    }

    public String getWind_dir() {
        return wind_dir;
    }

    public int getPressure_mb() {
        return (int) (pressure_mb * 0.750063);
    }

    public double getPrecip_mm() {
        return precip_mm;
    }

    public long getHumidity() {
        return humidity;
    }

    public long getCloud() {
        return cloud;
    }

    public double getFeelslike_c() {
        return feelslike_c;
    }

    public double getVis_km() {
        return vis_km;
    }

    @Override
    public String toString() {
        return "Current [temp_c=" + temp_c + ", condition=" + condition
                + ", wind_kph=" + wind_kph + ", wind_degree=" + wind_degree + ", wind_dir=" + wind_dir
                + ", pressure_mb=" + pressure_mb + ", precip_mm=" + precip_mm + ", humidity=" + humidity + ", cloud="
                + cloud + ", feelslike_c=" + feelslike_c + ", vis_km=" + vis_km + "]";
    }

}
