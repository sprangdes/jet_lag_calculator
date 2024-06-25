package com.jerry.jet_lag_calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TimeZone {

    ArrayList<Map<Integer, Boolean>> timeZone = new ArrayList<>(48);

    public TimeZone(int timeZoneCode) {
        if (timeZoneCode >= 0){
            for(int i = 0; i < 48; i++){
                Map<Integer, Boolean> map = new HashMap<>();
                map.put((timeZoneCode + i) % 24,false);
                timeZone.add(map);
            }
        }else{
            for(int i = 0; i < 48; i++){
                Map<Integer, Boolean> map = new HashMap<>();
                map.put((timeZoneCode + i + 24) % 24,false);
                timeZone.add(map);
            }
        }
    }

    public ArrayList<Map<Integer, Boolean>> getTimeZone() {
        return timeZone;
    }

    public void updateWakingTime(int startTime, int endTime){
        for(Map<Integer, Boolean> map : timeZone){
            for(Map.Entry<Integer, Boolean> entry : map.entrySet()){
                int key = entry.getKey();
                if(key >= startTime && key <= endTime){
                    map.put(key, true);
                }
            }
        }
    }

    public static ArrayList<Integer> compareTimeZones(TimeZone tz1, TimeZone tz2){
        ArrayList<Integer> result = new ArrayList<>();

        for(int i = 0; i < tz1.getTimeZone().size(); i++){
            Map<Integer, Boolean> map1 = tz1.getTimeZone().get(i);
            Map<Integer, Boolean> map2 = tz2.getTimeZone().get(i);

            int key1 = Integer.parseInt(map1.keySet().toString().replace("[", "").replace("]", ""));
            int key2 = Integer.parseInt(map2.keySet().toString().replace("[", "").replace("]", ""));

            if(map1.get(key1).equals(map2.get(key2)) && !result.contains(key1)){
                result.add(key1);
            }
        }
        return result;
    }
}
