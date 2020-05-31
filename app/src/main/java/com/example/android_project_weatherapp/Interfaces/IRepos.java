package com.example.android_project_weatherapp.Interfaces;

import com.example.android_project_weatherapp.Entity.Weather;
import com.example.android_project_weatherapp.RoomDB.Destination;
import java.util.ArrayList;

public interface IRepos {
    boolean insert(Weather weather);
    boolean insert(Destination destination);
    void delete(Weather weather);
    Destination[] getDestinations();
    ArrayList<Destination> getListDest();
    Integer getCount();
}
