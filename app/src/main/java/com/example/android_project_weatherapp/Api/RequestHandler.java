package com.example.android_project_weatherapp.Api;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestHandler {
    //    handles request queue
    private RequestQueue requestQueue;
    private static RequestHandler instance;
    private RequestHandler(Context context) {
        this.requestQueue = Volley.newRequestQueue(context);
    }
    public static synchronized RequestHandler getInstance(Context context) {
        if (instance == null) {
            instance = new RequestHandler(context);
        }
        return instance;
    }
    public RequestQueue getQueue() {
        return requestQueue;
    }
}
