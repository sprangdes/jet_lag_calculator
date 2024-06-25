package com.jerry.jet_lag_calculator;

import java.util.ArrayList;

public class CityList {

    private ArrayList<City> citys;

    public ArrayList<City> getCities() {
        return citys;
    }

    public void setCities(ArrayList<City> cities) {
        this.citys = cities;
    }

    @Override
    public String toString() {
        return "CityList{" +
                "cities=" + citys +
                '}';
    }
}
