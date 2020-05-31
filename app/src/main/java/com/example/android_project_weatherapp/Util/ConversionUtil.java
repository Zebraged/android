package com.example.android_project_weatherapp.Util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConversionUtil {
    public static String convertTempToCelcius(double temp) {
        double c = temp - 273.15;
        c = round(c);
        return c + " C\u00B0";
    }
//    public static String convertTempToFahrenheit(double temp) {
//        double fahr = temp * 9 / 5 - 459.67;
//        fahr = round(fahr);
//        return fahr + "F\u00B0";
//    }
    private static double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    public static String windDegToCode(int deg) {

        String[] directions = {"N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW"};
        int part = (int) ((deg / 22.5) + 0.5);
        return directions[part % 16];
    }

}
