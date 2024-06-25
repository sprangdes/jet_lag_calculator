package com.jerry.jet_lag_calculator;

import com.google.gson.Gson;
import com.jerry.jet_lag_calculator.entity.CityTimeZone;
import com.jerry.jet_lag_calculator.service.CityTimeZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;

@Component
public class ReadJson {

    @Autowired
    CityTimeZoneService cityTimeZoneService;

//    @PostConstruct
    public void readJsonAndWriteToDB() {
        Gson gson = new Gson();
        FileReader fileReader;
        {
            try {
                fileReader = new FileReader("/Users/machi/IntelliJ/jet_lag_calculator/src/main/resources/static/data/data.json");
                CityList cityList = gson.fromJson(fileReader, CityList.class);
                for(int i = 0; i < cityList.getCities().toArray().length; i++){

                    CityTimeZone cityTimeZone = new CityTimeZone();
                    cityTimeZone.setCity(cityList.getCities().get(i).getCity());
                    cityTimeZone.setTimeZone(cityList.getCities().get(i).getTime_zone());
                    cityTimeZone.setSummerTime(cityList.getCities().get(i).isSummer_time());

                    cityTimeZoneService.addCityTimeZone(cityTimeZone);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
