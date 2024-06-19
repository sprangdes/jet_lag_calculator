package com.jerry.jet_lag_calculator.controller;

import com.jerry.jet_lag_calculator.entity.CityTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class JetLagCalculatorController {

    private String myCityTimeZone;
    private String myStartTime;
    private String myEndTime;
    private String yourCityTimeZone;
    private String yourStartTime;
    private String yourEndTime;

    @Autowired
    private CityTimeZoneController cityTimeZoneController;

    @GetMapping("/start")
    public String start(){
        return "start";
    }

    @PostMapping("/me")
    public String me(Model model) {
        ResponseEntity<List<CityTimeZone>> cityTimeZones = cityTimeZoneController.getAllCityTimeZones();
        model.addAttribute("cityTimeZones", cityTimeZones.getBody());
        return "me";
    }

    @PostMapping("/you")
    public String you(@RequestParam String myCityTimeZone,
                      @RequestParam String myStartTime,
                      @RequestParam String myEndTime,
                      Model model) {
        ResponseEntity<List<CityTimeZone>> cityTimeZones = cityTimeZoneController.getAllCityTimeZones();
        model.addAttribute("cityTimeZones", cityTimeZones.getBody());
        this.myCityTimeZone = myCityTimeZone;
        this.myStartTime = myStartTime;
        this.myEndTime = myEndTime;
        return "you";
    }

    @PostMapping("/result")
    public String result(@RequestParam String yourCityTimeZone,
                         @RequestParam String yourStartTime,
                         @RequestParam String yourEndTime,
                         Model model) {
        this.yourCityTimeZone = yourCityTimeZone;
        this.yourStartTime = yourStartTime;
        this.yourEndTime = yourEndTime;
        model.addAttribute("myCityTimeZone", this.myCityTimeZone);
        model.addAttribute("myStartTime", this.myStartTime);
        model.addAttribute("myEndTime", this.myEndTime);
        model.addAttribute("yourCityTimeZone", this.yourCityTimeZone);
        model.addAttribute("yourStartTime", this.yourStartTime);
        model.addAttribute("yourEndTime", this.yourEndTime);
        return "result";
    }
}
