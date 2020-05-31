package com.example.android_project_weatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.android_project_weatherapp.Interfaces.IPresenter;
import com.example.android_project_weatherapp.Objects.Presenter;
import com.example.android_project_weatherapp.RoomDB.AppDatabase;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {
    private AppDatabase appDB;

    private RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager;

    private IPresenter present;


    private FusedLocationProviderClient fusedLocationClient;


    private EditText editText;
    private TextView currentLoc;
    private TextView currentInf;

    private final int REQUEST_LOCATION = 1;
    private volatile boolean hasPermission = false;


    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        //        view elements
        editText = findViewById(R.id.editText);
        currentLoc = findViewById(R.id.currentLocTV);
        currentInf = findViewById(R.id.currentWeatherTV);


        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MainActivity.this, R.string.granted, Toast.LENGTH_SHORT).show();
            hasPermission = true;
        } else {
            currentInf.setText(R.string.locationpermission);
            currentLoc.setText(R.string.locationpermission);
            requestLocationPermission();

        }


        // RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // Adapter


//        db initialized with context
        appDB = AppDatabase.getAppDatabase(this);
//        presenter
        present = new Presenter();


        recyclerView.setAdapter(present.getAdapter());
        present.setDataContext(recyclerView.getContext());


        createSuccessListeLoc();


    }

    @Override
    protected void onResume() {
        super.onResume();
        updateRecycleView();
        updateLoc();
    }

    public void onClickTryAdd(View view) {
        present.insertNameDestination(editText.getText().toString(), 0, editText.getContext());
    }

    private void updateRecycleView() {
        present.getStartData(recyclerView.getContext());
    }

    public void onClickHelp(View view) {
        HelpDialog help = new HelpDialog();
        help.show(getSupportFragmentManager(), "Info");
    }

    private OnSuccessListener<Location> successListener;

    private void createSuccessListeLoc() {
        successListener = new OnSuccessListener<Location>() {

            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    present.getCurrentWeather(location.getLatitude(), location.getLongitude(), currentLoc, currentInf);
                } else {
                    // could Toast
                }
            }
        };
    }

    private void updateLoc() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (!hasPermission) {
                    while (!hasPermission) {
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, R.string.granted, Toast.LENGTH_SHORT).show();
                                    hasPermission = true;
                                }
                            });
                        }
                    }
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                fusedLocationClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);
                fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(MainActivity.this, successListener);

            }

        }).start();


    }


    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                hasPermission = true;

            } else {
                Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
                hasPermission = false;
            }
        }
    }
}
