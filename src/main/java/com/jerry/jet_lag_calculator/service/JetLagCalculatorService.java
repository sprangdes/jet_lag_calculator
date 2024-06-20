package com.jerry.jet_lag_calculator.service;

import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class JetLagCalculatorService {

    public int jetLag(String myCitTimeZone, String yourCitTimeZone) {
        return (int)(Double.parseDouble(yourCitTimeZone) - Double.parseDouble(myCitTimeZone));
    }

    public List<String> interactTimeCalculator(LocalTime myStartTime,
                                     LocalTime myEndTime,
                                     LocalTime yourStartTime,
                                     LocalTime yourEndTime,
                                     int jetLag){
        List<String> interactTimeList = new ArrayList<>();
        LocalTime yourStartTimeToMe, yourEndTimeToMe, interactStartTime, interactEndTime;
        if(jetLag >= 0){
            yourStartTimeToMe = yourStartTime.minusHours(jetLag);
            yourEndTimeToMe = yourEndTime.minusHours(jetLag);
        }else{
            yourStartTimeToMe = yourStartTime.minusHours(jetLag);
            yourEndTimeToMe = yourEndTime.minusHours(jetLag);
        }
        interactStartTime = yourStartTimeToMe.isAfter(myStartTime)? yourStartTimeToMe: myStartTime;
        interactEndTime = yourEndTimeToMe.isBefore(myEndTime)? yourEndTimeToMe: myEndTime;
        interactTimeList.add(interactStartTime.toString());
        interactTimeList.add(interactEndTime.toString());
        return interactTimeList;
    }
}
