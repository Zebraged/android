package com.example.android_project_weatherapp.Async;

import android.os.AsyncTask;
import com.example.android_project_weatherapp.Entity.Weather;
import com.example.android_project_weatherapp.Interfaces.IPostAsyncListener;
import com.example.android_project_weatherapp.Interfaces.IRepos;
import com.example.android_project_weatherapp.Objects.Repos;


public class dbAsyncDelete extends AsyncTask<Weather, Void, Void> {

    private IRepos repos;
    private IPostAsyncListener<Boolean> postTaskListener;

    public dbAsyncDelete(IPostAsyncListener<Boolean> postTaskListener) {
        this.postTaskListener = postTaskListener;
    }

    @Override
    protected void onPreExecute() {
        repos = new Repos();
    }
    @Override
    protected Void doInBackground(Weather... weathers) {
        for (int i = 0; i < weathers.length; i++) {
            repos.delete(weathers[i]);
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        if (postTaskListener != null) {
            postTaskListener.onPostTask(true);
        }
    }
}
