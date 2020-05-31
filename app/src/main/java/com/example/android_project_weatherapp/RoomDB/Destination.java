package com.example.android_project_weatherapp.RoomDB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Destination {
    @Ignore
    public Destination(int id, String name, String country) {
        this.country = country;
        this.id = id;
        this.name = name;
    }
    public Destination() {

    }
    @PrimaryKey
    public
    int id;
    @ColumnInfo(name = "name")
    public
    String name;
    @ColumnInfo(name = "country")
    public
    String country;
}
