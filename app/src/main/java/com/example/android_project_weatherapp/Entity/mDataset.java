package com.example.android_project_weatherapp.Entity;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.example.android_project_weatherapp.Async.dbAsyncDelete;
import com.example.android_project_weatherapp.Interfaces.IDataSuplier;
import com.example.android_project_weatherapp.Interfaces.IPostAsyncListener;
import com.example.android_project_weatherapp.R;
import com.example.android_project_weatherapp.Util.ConversionUtil;

import java.util.ArrayList;

public class mDataset implements IDataSuplier {
    private ArrayList<Weather> weathers;
    private RecyclerView.Adapter mAdapter;
    private Context context;

    public mDataset() {
        weathers = new ArrayList<>();
    }

    public void setmAdapter(RecyclerView.Adapter mAdapter) {
        this.mAdapter = mAdapter;
    }
    public void setContext(Context context) {
        this.context = context;
    }
    public void updateList(Weather[] ws) {
        weathers.clear();
        for (int i = 0; i < ws.length; i++) {
            weathers.add(ws[i]);
        }
    }
    public void addElement(Weather w) {
        if (!weathers.contains(w)) {
            weathers.add(w);
        }

    }
    @Override
    public int getSize() {
        return weathers.size();
    }
    @Override
    public String getName(int i) {
        return weathers.get(i).getName();
    }
    @Override
    public String getTemp(int i) {
        return "Temp: " + "\n" + ConversionUtil.convertTempToCelcius(weathers.get(i).getTemp());
    }
    @Override
    public String getHumid(int i) {
        return context.getResources().getString(R.string.humid) + "\n" + weathers.get(i).getHumidity() + "%";
    }
    @Override
    public String getWindspeed(int i) {
        return context.getResources().getString(R.string.wind) + "\n" + weathers.get(i).getWindspeed() + " m/s" + " : " + ConversionUtil.windDegToCode(weathers.get(i).getWindDeg());
    }
    @Override
    public String getCountry(int i) {
        return weathers.get(i).getCountry();
    }
    @Override
    public String getWeather(int i) {
        return context.getResources().getString(R.string.weather) + weathers.get(i).getWeatherDescription().substring(0, 1).toUpperCase() + weathers.get(i).getWeatherDescription().substring(1);
    }
    @Override
    public boolean removeWeather(final int i) {
        new dbAsyncDelete(new IPostAsyncListener<Boolean>() {
            @Override
            public void onPostTask(Boolean result) {
                weathers.remove(i);
                mAdapter.notifyItemRemoved(i);
            }
        }).execute(weathers.get(i));
        return true;
    }
}
