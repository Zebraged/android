package com.example.android_project_weatherapp.Entity;

public class Weather {
    private String weather;
    private String weatherDescription;
    private double temp;
    private int humidity;
    private double windspeed;
    private int windDeg;
    private String name;
    private int id;
    private String country;

    public Weather(String weather, String weatherDescription, double temp, int humidity, double windspeed, int windDeg, String name, int id, String country) {
        this.weather = weather;
        this.weatherDescription = weatherDescription;
        this.temp = temp;
        this.humidity = humidity;
        this.windspeed = windspeed;
        this.windDeg = windDeg;
        this.name = name;
        this.id = id;
        this.country = country;
    }
    public Weather() {

    }
    public String getWeather() {
        return weather;
    }
    public String getWeatherDescription() {
        return weatherDescription;
    }
    public double getTemp() {
        return temp;
    }
    public int getHumidity() {
        return humidity;
    }
    public double getWindspeed() {
        return windspeed;
    }
    public int getWindDeg() {
        return windDeg;
    }
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public String getCountry() {
        return country;
    }
    public void setWeather(String weather) {
        this.weather = weather;
    }
    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }
    public void setTemp(double temp) {
        this.temp = temp;
    }
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
    public void setWindspeed(double windspeed) {
        this.windspeed = windspeed;
    }
    public void setWindDeg(int windDeg) {
        this.windDeg = windDeg;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String toString() {
        String s = "" + weather + ", " + weatherDescription + ", " + temp + ", " + humidity + ", " + windspeed + ", " + windDeg + ", " + name + ", " + id + ", " + country + "";
        return s;
    }
}
