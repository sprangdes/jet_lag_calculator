package com.jerry.jet_lag_calculator;

import java.util.ArrayList;

public class CalculatorTest {

    public static void main(String[] args) {

        int myWakingTimeStart = 6;
        int myWakingTimeEnd = 23;
        int yourWakingTimeStart = 7;
        int yourWakingTimeEnd = 22;

        TimeZone taiwan = new TimeZone(8);
        TimeZone denver = new TimeZone(-6);

        taiwan.updateWakingTime(myWakingTimeStart, myWakingTimeEnd);
        denver.updateWakingTime(yourWakingTimeStart, yourWakingTimeEnd);

        ArrayList<Integer> result = TimeZone.compareTimeZones(taiwan, denver);
        System.out.println(result);
    }
}
