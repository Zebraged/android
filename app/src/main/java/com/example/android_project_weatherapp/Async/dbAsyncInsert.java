package com.example.android_project_weatherapp.Async;

import android.os.AsyncTask;

import com.example.android_project_weatherapp.Interfaces.IPostAsyncListener;
import com.example.android_project_weatherapp.Interfaces.IRepos;
import com.example.android_project_weatherapp.Objects.Repos;
import com.example.android_project_weatherapp.RoomDB.Destination;
import java.util.ArrayList;

public class dbAsyncInsert extends AsyncTask<Destination, Void, Boolean> {

    private IRepos repos;
    private IPostAsyncListener<Boolean> postTaskListener;
    public dbAsyncInsert(IPostAsyncListener postTaskListener) {
        this.postTaskListener = postTaskListener;
    }
    @Override
    protected void onPreExecute() {
        repos = new Repos();
    }
    @Override
    protected Boolean doInBackground(Destination... destinations) {
        ArrayList<Destination> arrayList = new ArrayList();
        for (int i = 0; i < destinations.length; i++) {
            repos.insert(destinations[i]);
            arrayList.add(destinations[i]);
        }
        if (repos.getListDest().containsAll(arrayList)) {
            return true;
        }
        return false;
    }
    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if (postTaskListener != null) {
            postTaskListener.onPostTask(aBoolean);
        }
    }
}