package com.example.android_project_weatherapp.Util;

import com.example.android_project_weatherapp.Entity.Weather;

public class TextPrep {
    private String temp;
    private String windspeed;
    private String name;
    private String country;
    private String humidtiy;
    private String windDirection;
    private String weather;

    public TextPrep() {
    }
    public void prepWeather(Weather weather) {
        country = weather.getCountry();
        humidtiy = ""+weather.getHumidity();
        name = weather.getName();
        this.weather = weather.getWeatherDescription().substring(0,1).toUpperCase()+weather.getWeatherDescription().substring(1);
        windDirection = ConversionUtil.windDegToCode(weather.getWindDeg());
        windspeed = ""+weather.getWindspeed();
        temp = ConversionUtil.convertTempToCelcius(weather.getTemp());
    }
    public String getName() {
        return name;
    }
    public String getWeather() {
        return weather;
    }
    public String getNameCountry() {
        return name + ", "+country;
    }
    public String getInfo() {
        String s = "Current Weather: "+ weather+ "\nTemperature: "+  temp+ "\nWind Direction: "+windDirection+"\nWindspeed: " +windspeed + " m/s";
        return s;
    }
}
