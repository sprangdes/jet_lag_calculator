package com.jerry.jet_lag_calculator.entity;

import jakarta.persistence.*;

@Entity
public class CityTimeZone {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String city;
    private String timeZone;
    private boolean summerTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public boolean isSummerTime() {
        return summerTime;
    }

    public void setSummerTime(boolean summerTime) {
        this.summerTime = summerTime;
    }
}
