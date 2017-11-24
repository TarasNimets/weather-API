package com.tec.data.weather.model.weather;

public final class Hour {

    private long time_epoch;
    private double temp_c;
    private Condition condition;
    private double wind_kph;
    private String wind_dir;
    private double pressure_mb;
    private double precip_mm;
    private long humidity;
    private long cloud;
    private double feelslike_c;
    private double windchill_c;
    private double heatindex_c;
    private double dewpoint_c;
    private int will_it_rain;
    private int chance_of_rain;
    private int will_it_snow;
    private int chance_of_snow;
    private double vis_km;

    public long getTime_epoch() {
        return time_epoch;
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

    public String getWind_dir() {
        return wind_dir;
    }

    public int getPressure_mb() {
        return (int) (pressure_mb * 0.75006);
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

    public int getWill_it_rain() {
        return will_it_rain;
    }

    public int getChance_of_rain() {
        return chance_of_rain;
    }

    public int getWill_it_snow() {
        return will_it_snow;
    }

    public int getChance_of_snow() {
        return chance_of_snow;
    }

    public double getVis_km() {
        return vis_km;
    }

    public double getWindchill_c() {
        return windchill_c;
    }

    public double getHeatindex_c() {
        return heatindex_c;
    }

    public double getDewpoint_c() {
        return dewpoint_c;
    }

    private Hour() {
    }

    @Override
    public String toString() {
        return "Hour [time_epoch=" + time_epoch + ", temp_c=" + temp_c + ", condition=" + condition + ", wind_kph="
                + wind_kph + ", wind_dir=" + wind_dir + ", pressure_mb=" + pressure_mb + ", precip_mm=" + precip_mm
                + ", humidity=" + humidity + ", cloud=" + cloud + ", feelslike_c=" + feelslike_c + ", windchill_c="
                + windchill_c + ", heatindex_c=" + heatindex_c + ", dewpoint_c=" + dewpoint_c + ", will_it_rain="
                + will_it_rain + ", chance_of_rain=" + chance_of_rain + ", will_it_snow=" + will_it_snow
                + ", chance_of_snow=" + chance_of_snow + ", vis_km=" + vis_km + "]";
    }

}
