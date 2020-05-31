package com.example.android_project_weatherapp.Interfaces;

import android.content.Context;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public interface IPresenter {

    void insertNameDestination(String destination, int type, Context context);

    RecyclerView.Adapter getAdapter();

    void getStartData(Context context);

    void getCurrentWeather(double lat, double lon, TextView loc, TextView Info);

    void setDataContext(Context context);
}
