package com.example.android_project_weatherapp.Objects;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.example.android_project_weatherapp.Api.JsonReq;
import com.example.android_project_weatherapp.Api.RequestHandler;
import com.example.android_project_weatherapp.Api.UrlGenerator;
import com.example.android_project_weatherapp.Async.dbAsyncCount;
import com.example.android_project_weatherapp.Async.dbAsyncGet;
import com.example.android_project_weatherapp.Async.dbAsyncInsert;
import com.example.android_project_weatherapp.Entity.Weather;
import com.example.android_project_weatherapp.Entity.mDataset;
import com.example.android_project_weatherapp.Interfaces.IPostAsyncListener;
import com.example.android_project_weatherapp.Interfaces.IPresenter;
import com.example.android_project_weatherapp.Interfaces.IRepos;
import com.example.android_project_weatherapp.Recycler.WeatherAdapter;
import com.example.android_project_weatherapp.RoomDB.AppDatabase;
import com.example.android_project_weatherapp.RoomDB.Destination;
import com.example.android_project_weatherapp.RoomDB.DestinationDao;
import com.example.android_project_weatherapp.Util.TextPrep;




public class Presenter implements IPresenter {

    private UrlGenerator url;
    private JsonReq json;
    private TextPrep txt;
    private DestinationDao dao;
    private IRepos repo;
    private mDataset data;
    private RecyclerView.Adapter mAdapter;

    public Presenter() {
        url = new UrlGenerator();
        json = new JsonReq();
        txt = new TextPrep();
        dao = AppDatabase.getInstance().destinationDao();
        repo = new Repos();
        data = new mDataset();
        mAdapter = new WeatherAdapter(data);
        data.setmAdapter(mAdapter);
        creatBulk();
        createSingleList();
    }


    @Override
    public void insertNameDestination(String destination, int type, final Context context) {
        String reg = destination;
        reg.replaceAll("[^0-9a-zA-ZæøåÆØÅ]+", "");
        final int tp = type;
        final String dest = reg;
        new dbAsyncCount(new IPostAsyncListener<Integer>() {
            @Override
            public void onPostTask(Integer result) {
                if (result < 20) {
                    RequestHandler.getInstance(context).getQueue().removeRequestFinishedListener(bulkList);
                    RequestHandler.getInstance(context).getQueue().removeRequestFinishedListener(locaListe);
                    RequestHandler.getInstance(context).getQueue().add(json.createRequest(url.createUrl(dest, tp)));
                    RequestHandler.getInstance(context).getQueue().addRequestFinishedListener(singleList);
                }
            }
        }).execute();
    }
    @Override
    public RecyclerView.Adapter getAdapter() {
        return mAdapter;
    }
    @Override
    public void getStartData(Context context) {
        final Context cntxt = context;
        new dbAsyncGet(new IPostAsyncListener<Destination[]>() {
            @Override
            public void onPostTask(Destination[] result) {
                if (result.length != 0 || result.length < 0) {
                    int[] ids = new int[result.length];
                    for (int i = 0; i < result.length; i++) {
                        ids[i] = result[i].id;
                    }
                    RequestHandler.getInstance(cntxt).getQueue().removeRequestFinishedListener(singleList);
                    RequestHandler.getInstance(cntxt).getQueue().removeRequestFinishedListener(locaListe);
                    RequestHandler.getInstance(cntxt).getQueue().add(json.createBulkRequest(url.createBulkUrl(url.intArrtoString(ids))));
                    RequestHandler.getInstance(cntxt).getQueue().addRequestFinishedListener(bulkList);
                }
            }
        }).execute();
    }
    @Override
    public void getCurrentWeather(double lat, double lon, TextView loc, TextView Info) {
        createLocListe(loc, Info);
        RequestHandler.getInstance(loc.getContext()).getQueue().removeRequestFinishedListener(singleList);
        RequestHandler.getInstance(loc.getContext()).getQueue().removeRequestFinishedListener(bulkList);
        RequestHandler.getInstance(loc.getContext()).getQueue().add(json.createRequest(url.createLocUrl(lat, lon)));
        RequestHandler.getInstance(loc.getContext()).getQueue().addRequestFinishedListener(locaListe);
    }
    @Override
    public void setDataContext(Context context) {
        data.setContext(context);
    }


    private RequestQueue.RequestFinishedListener bulkList;
    private void creatBulk() {
        bulkList = new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                Weather[] weathers = json.getWeaters();
                data.updateList(weathers);
                mAdapter.notifyDataSetChanged();

            }
        };
    }
    private RequestQueue.RequestFinishedListener singleList;
    private void createSingleList() {
        singleList = new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                if (json.getWeather().getId() != 0) {
                    Destination d = new Destination();
                    d.country = json.getWeather().getCountry();
                    d.id = json.getWeather().getId();
                    d.name = json.getWeather().getName();
                    new dbAsyncInsert(new IPostAsyncListener<Boolean>() {
                        @Override
                        public void onPostTask(Boolean result) {
                            data.addElement(json.getWeather());
                            mAdapter.notifyItemInserted(data.getSize());
                        }
                    }).execute(d);
                }
            }
        };
    }
    private RequestQueue.RequestFinishedListener locaListe;
    private void createLocListe(final TextView loca, final TextView info) {
        locaListe = new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {

                Handler handler =new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        txt.prepWeather(json.getWeather());
                        info.setText(txt.getInfo());
                        loca.setText(txt.getNameCountry());
                    }
                });
            }
        };

    }
}
