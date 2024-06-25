package com.jerry.jet_lag_calculator;

import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("city")
    private String city;

    @SerializedName("time_zone")
    private String time_zone;

    @SerializedName("summer_time")
    private boolean summer_time;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTime_zone() {
        return time_zone;
    }

    public void setTime_zone(String time_zone) {
        this.time_zone = time_zone;
    }

    public boolean isSummer_time() {
        return summer_time;
    }

    public void setSummer_time(boolean summer_time) {
        this.summer_time = summer_time;
    }

    @Override
    public String toString() {
        return "City{" +
                "city='" + city + '\'' +
                ", time_zone='" + time_zone + '\'' +
                ", summer_time=" + summer_time +
                '}';
    }
}
