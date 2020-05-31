package com.example.android_project_weatherapp.Async;

import android.os.AsyncTask;

import com.example.android_project_weatherapp.Interfaces.IPostAsyncListener;
import com.example.android_project_weatherapp.Interfaces.IRepos;
import com.example.android_project_weatherapp.Objects.Repos;

public class dbAsyncCount extends AsyncTask<Void, Void, Integer> {
    private IRepos repos;
    private IPostAsyncListener<Integer> postTaskListener;

    public dbAsyncCount(IPostAsyncListener<Integer> postTaskListener) {
        this.postTaskListener = postTaskListener;
    }
    @Override
    protected void onPreExecute() {
        repos = new Repos();
    }
    protected Integer doInBackground(Void... Void) {
        Integer integer = repos.getCount();
        return integer;
    }
    @Override
    protected void onPostExecute(Integer Integer) {
        super.onPostExecute(Integer);
        if (postTaskListener != null) {
            postTaskListener.onPostTask(Integer);
        }
    }
}
