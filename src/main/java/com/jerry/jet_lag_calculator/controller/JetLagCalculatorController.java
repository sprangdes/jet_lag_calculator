package com.jerry.jet_lag_calculator.controller;

import com.jerry.jet_lag_calculator.entity.CityTimeZone;
import com.jerry.jet_lag_calculator.service.JetLagCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class JetLagCalculatorController {

    private String myCityTimeZone;
    private LocalTime myStartTime;
    private LocalTime myEndTime;
    private String yourCityTimeZone;
    private LocalTime yourStartTime;
    private LocalTime yourEndTime;

    @Autowired
    private CityTimeZoneController cityTimeZoneController;

    @Autowired
    private JetLagCalculatorService jetLagCalculatorService;

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
        this.myStartTime = LocalTime.parse(myStartTime, DateTimeFormatter.ofPattern("HH:mm"));
        this.myEndTime = LocalTime.parse(myEndTime, DateTimeFormatter.ofPattern("HH:mm"));
        return "you";
    }

    @PostMapping("/result")
    public String result(@RequestParam String yourCityTimeZone,
                         @RequestParam String yourStartTime,
                         @RequestParam String yourEndTime,
                         Model model) {
        this.yourCityTimeZone = yourCityTimeZone;
        this.yourStartTime = LocalTime.parse(yourStartTime, DateTimeFormatter.ofPattern("HH:mm"));
        this.yourEndTime = LocalTime.parse(yourEndTime, DateTimeFormatter.ofPattern("HH:mm"));
        int jetLag = jetLagCalculatorService.jetLag(this.myCityTimeZone, this.yourCityTimeZone);
        List<String> interactTimeList = jetLagCalculatorService.interactTimeCalculator(
                this.myStartTime,
                this.myEndTime,
                this.yourStartTime,
                this.yourEndTime,
                jetLag);
        model.addAttribute("interactStartTime", interactTimeList.get(0));
        model.addAttribute("interactEndTime", interactTimeList.get(1));
        return "result";
    }
}
