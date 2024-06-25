package com.jerry.jet_lag_calculator.service;

import com.jerry.jet_lag_calculator.TimeZone;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JetLagCalculatorService {

    public List<Integer> interactTimeCalculator(String myStartTime,
                                                String myEndTime,
                                                String yourStartTime,
                                                String yourEndTime,
                                                int myCityTimeZone,
                                                int yourCityTimeZone){

        int myWakingTimeStart = Integer.parseInt(myStartTime.substring(0, 2));
        int myWakingTimeEnd = Integer.parseInt(myEndTime.substring(0, 2));
        int yourWakingTimeStart = Integer.parseInt(yourStartTime.substring(0, 2));
        int yourWakingTimeEnd = Integer.parseInt(yourEndTime.substring(0, 2));
        TimeZone myCity = new TimeZone(myCityTimeZone);
        TimeZone yourCity = new TimeZone(yourCityTimeZone);

        myCity.updateWakingTime(myWakingTimeStart, myWakingTimeEnd);
        yourCity.updateWakingTime(yourWakingTimeStart, yourWakingTimeEnd);

        ArrayList<Integer> result = TimeZone.compareTimeZones(myCity, yourCity);
        return result;
    }

    public List<int[]> findContinuousRanges(List<Integer> interactionList){
        List<int[]> ranges = new ArrayList<>();
        if(interactionList.isEmpty()){
            return ranges;
        }
        interactionList.sort(Integer::compareTo);
        List<Integer> extendedList = new ArrayList<>(interactionList);

        if(interactionList.contains(23) && interactionList.contains(0)){
            extendedList.add(24);
        }

        int start = extendedList.get(0);
        int end = start;

        for(int i = 1; i < interactionList.size(); i++){
            int current = interactionList.get(i);
            if(current == end + 1 || (end == 23 && current == 0)){
                end = current == 24 ? 0 : current;
            }else{
                ranges.add(new int[]{start, end});
                start = current;
                end = start;
            }
        }
        ranges.add(new int[]{start, end});
        return ranges;
    }
}
