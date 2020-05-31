package com.example.android_project_weatherapp.RoomDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DestinationDao {


    @Query("SELECT * FROM destination")
    List<Destination> getAll();
    @Query("SELECT count (*) FROM destination")
    int getCount();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(Destination... destinations);
    @Delete
    void delete(Destination destination);
}
