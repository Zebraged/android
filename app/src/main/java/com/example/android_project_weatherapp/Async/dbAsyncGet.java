package com.example.android_project_weatherapp.Async;

import android.os.AsyncTask;
import com.example.android_project_weatherapp.Interfaces.IPostAsyncListener;
import com.example.android_project_weatherapp.Interfaces.IRepos;
import com.example.android_project_weatherapp.Objects.Repos;
import com.example.android_project_weatherapp.RoomDB.Destination;


public class dbAsyncGet extends AsyncTask<Void, Void, Destination[]> {

    private IRepos repos;
    private IPostAsyncListener<Destination[]> postTaskListener;

    public dbAsyncGet(IPostAsyncListener<Destination[]> postTaskListener) {
        this.postTaskListener = postTaskListener;
    }

    @Override
    protected void onPreExecute() {
        repos = new Repos();
    }
    protected Destination[] doInBackground(Void... Void) {
        Destination[] dest = repos.getDestinations();
        return dest;
    }
    @Override
    protected void onPostExecute(Destination[] destinations) {
        super.onPostExecute(destinations);
        if (destinations != null && postTaskListener != null) {
            postTaskListener.onPostTask(destinations);
        }
    }
}
