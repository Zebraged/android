package com.example.android_project_weatherapp.Interfaces;

public interface IDataSuplier {

    int getSize();

    String getName(int i);

    String getTemp(int i);

    String getHumid(int i);

    String getWindspeed(int i);

    String getCountry(int i);

    String getWeather(int i);

    boolean removeWeather(int i);
}
