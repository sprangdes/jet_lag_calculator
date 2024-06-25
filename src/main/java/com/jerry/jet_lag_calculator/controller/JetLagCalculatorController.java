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

import java.util.List;

@Controller
public class JetLagCalculatorController {

    private String myCityTimeZone, myStartTime, myEndTime,
                   yourCityTimeZone, yourStartTime, yourEndTime;

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
        List<Integer> interactTimeList = jetLagCalculatorService.interactTimeCalculator(
                this.myStartTime,
                this.myEndTime,
                this.yourStartTime,
                this.yourEndTime,
                (int)(Math.round(Double.parseDouble(this.myCityTimeZone))),
                (int)(Math.round(Double.parseDouble(this.yourCityTimeZone))));
        List<int[]> ranges = jetLagCalculatorService.findContinuousRanges(interactTimeList);
        model.addAttribute("ranges", ranges);
        return "result";
    }
}
