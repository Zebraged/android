package com.example.android_project_weatherapp.Api;

import android.util.Log;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.android_project_weatherapp.Entity.Weather;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class JsonReq {
    //    handles creation of Json requests
    private Weather w;
    private ArrayList<Weather> weathers;
    private void pullGenInfo(JSONObject response) throws JSONException {
        JSONArray weather = response.getJSONArray("weather");
        String weather1 = "";
        String weatherDescription = "";
        for (int i = 0; i < weather.length(); i++) {
            JSONObject weahterI = weather.getJSONObject(i);
            w.setWeather(weahterI.getString("main"));
            w.setWeatherDescription(weahterI.getString("description"));
        }
    }
    private void pullActualIInfo(JSONObject response) throws JSONException {
        JSONObject main = response.getJSONObject("main");
        w.setTemp(main.getDouble("temp"));
        w.setHumidity(main.getInt("humidity"));
    }
    private void pullWindInfo(JSONObject response) throws JSONException {
        JSONObject wind = response.getJSONObject("wind");
        w.setWindspeed(wind.getDouble("speed"));
        w.setWindDeg(wind.getInt("deg"));
    }
    private void pullAPIInfo(JSONObject response) throws JSONException {
        JSONObject sys = response.getJSONObject("sys");
        w.setCountry(sys.getString("country"));
        w.setId(response.getInt("id"));
        w.setName(response.getString("name"));
    }
    public JsonObjectRequest createRequest(String url) {
        w = new Weather();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //general information
                            pullGenInfo(response);
                            pullActualIInfo(response);
                            pullWindInfo(response);
                            pullAPIInfo(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        return request;
    }
    public JsonObjectRequest createBulkRequest(String url) {
        weathers = new ArrayList<>();
        weathers.clear();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray destinations = response.getJSONArray("list");
                            JSONObject current;
                            for (int i = 0; i < destinations.length(); i++) {
                                current = destinations.getJSONObject(i);
                                w = new Weather();
                                pullGenInfo(current);
                                pullActualIInfo(current);
                                pullWindInfo(current);
                                pullAPIInfo(current);
                                weathers.add(new Weather(w.getWeather(), w.getWeatherDescription(), w.getTemp(), w.getHumidity(), w.getWindspeed(), w.getWindDeg(), w.getName(), w.getId(), w.getCountry()));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        return request;
    }
    public Weather getWeather() {
        return w;
    }
    public Weather[] getWeaters() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        for (int i = 0; i < stackTraceElements.length; i++) {
            Log.d("Test", stackTraceElements[i].getMethodName() + ": " + stackTraceElements[i].getLineNumber() + ":" + stackTraceElements[i].getClassName());
        }
        Weather[] wets = new Weather[weathers.size()];
        for (int i = 0; i < weathers.size(); i++) {
            wets[i] = weathers.get(i);
        }
        return wets;
    }
}
