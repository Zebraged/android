package com.example.android_project_weatherapp.Objects;

import com.example.android_project_weatherapp.Entity.Weather;
import com.example.android_project_weatherapp.Interfaces.IRepos;
import com.example.android_project_weatherapp.RoomDB.AppDatabase;
import com.example.android_project_weatherapp.RoomDB.Destination;
import com.example.android_project_weatherapp.RoomDB.DestinationDao;

import java.util.ArrayList;

public class Repos implements IRepos {
    //    This class handles insertion deletion and retrieval of data from db.
    DestinationDao dao;
    public Repos() {
        dao = AppDatabase.getInstance().destinationDao();
    }
    @Override
    public boolean insert(Weather weather) {
        if (dao.getCount() < 20) {
            dao.insertAll(new Destination(weather.getId(), weather.getName(), weather.getCountry()));
            return true;
        }
        return false;
    }
    @Override
    public boolean insert(Destination destination) {
        dao.insertAll(destination);
        return true;
    }
    @Override
    public void delete(Weather weather) {
        dao.delete(new Destination(weather.getId(), weather.getName(), weather.getCountry()));
    }
    @Override
    public Destination[] getDestinations() {
        ArrayList<Destination> arrayList = (ArrayList<Destination>) dao.getAll();
        Destination[] dest = new Destination[dao.getAll().size()];
        for (int i = 0; i < dest.length; i++) {
            dest[i] = arrayList.get(i);

        }
        return dest;
    }
    @Override
    public ArrayList<Destination> getListDest() {
        return (ArrayList<Destination>) dao.getAll();
    }
    @Override
    public Integer getCount() {
        return dao.getCount();
    }
}
