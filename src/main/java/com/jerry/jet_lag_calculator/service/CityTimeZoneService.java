package com.jerry.jet_lag_calculator.service;

import com.jerry.jet_lag_calculator.entity.CityTimeZone;
import com.jerry.jet_lag_calculator.repository.CityTimeZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityTimeZoneService {

    @Autowired
    private CityTimeZoneRepository cityTimeZoneRepository;

    public List<CityTimeZone> getAllCityTimeZone(){
        return cityTimeZoneRepository.findAll();
    }

    public CityTimeZone getCityTimeZoneById(Long id){
        return cityTimeZoneRepository.findById(id).orElse(null);
    }

    public CityTimeZone addCityTimeZone(CityTimeZone cityTimeZone){
        return cityTimeZoneRepository.save(cityTimeZone);
    }

    public CityTimeZone updateCityTimeZone(Long id, CityTimeZone cityTimeZone){
        CityTimeZone targetCityTimeZone = getCityTimeZoneById(id);
        if(targetCityTimeZone != null){
            targetCityTimeZone.setContinent(cityTimeZone.getContinent());
            targetCityTimeZone.setCountry(cityTimeZone.getCountry());
            targetCityTimeZone.setCity(cityTimeZone.getCity());
            targetCityTimeZone.setTimeZone(cityTimeZone.getTimeZone());
            return cityTimeZoneRepository.save(targetCityTimeZone);
        }else{
            return null;
        }
    }
    public void deleteCityTimeZone(Long id){
        cityTimeZoneRepository.deleteById(id);
    }
}
