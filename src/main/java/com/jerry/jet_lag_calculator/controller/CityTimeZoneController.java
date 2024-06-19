package com.jerry.jet_lag_calculator.controller;

import com.jerry.jet_lag_calculator.entity.CityTimeZone;
import com.jerry.jet_lag_calculator.service.CityTimeZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityTimeZoneController {

    @Autowired
    private CityTimeZoneService cityTimeZoneService;

    @GetMapping("/city-time-zone")
    public ResponseEntity<List<CityTimeZone>> getAllCityTimeZones(){
        List<CityTimeZone> cityTimeZones = cityTimeZoneService.getAllCityTimeZone();
        if(!cityTimeZones.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(cityTimeZones);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/city-time-zone/{id}")
    public ResponseEntity<CityTimeZone> getCityTimeZoneById(@PathVariable Long id) {
        CityTimeZone cityTimeZone = cityTimeZoneService.getCityTimeZoneById(id);
        if(cityTimeZone != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cityTimeZone);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/city-time-zone")
    public ResponseEntity<CityTimeZone> createCityTimeZone(@RequestBody CityTimeZone cityTimeZone){
        CityTimeZone cityTimeZoneCreated = cityTimeZoneService.addCityTimeZone(cityTimeZone);
        if(cityTimeZoneCreated != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(cityTimeZoneCreated);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/city-time-zone/{id}")
    public ResponseEntity<CityTimeZone> updateCityTimeZone(@PathVariable Long id, @RequestBody CityTimeZone newCityTimeZone){
        CityTimeZone cityTimeZoneUpdated = cityTimeZoneService.updateCityTimeZone(id, newCityTimeZone);
        if(cityTimeZoneUpdated != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cityTimeZoneUpdated);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/city-time-zone/{id}")
    public ResponseEntity<?> deleteCityTimeZone(Long id){
        cityTimeZoneService.deleteCityTimeZone(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
