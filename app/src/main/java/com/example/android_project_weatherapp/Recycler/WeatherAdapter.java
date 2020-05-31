package com.example.android_project_weatherapp.Recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android_project_weatherapp.Interfaces.IDataSuplier;
import com.example.android_project_weatherapp.R;
import com.google.android.material.snackbar.Snackbar;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyViewHolder> {
    private IDataSuplier dataSuplier;
    public WeatherAdapter(IDataSuplier dataSuplier) {
        this.dataSuplier = dataSuplier;
    }


    @NonNull
    @Override
    public WeatherAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.my_view, parent, false);
        TextView name = constraintLayout.findViewById(R.id.tvName);
        TextView country = constraintLayout.findViewById(R.id.tvCountry);
        TextView humid = constraintLayout.findViewById(R.id.tvHumid);
        TextView weather = constraintLayout.findViewById(R.id.tvWeather);
        TextView windspeed = constraintLayout.findViewById(R.id.tvWindspeed);
        TextView temp = constraintLayout.findViewById(R.id.tvTemp);
        MyViewHolder vh = new MyViewHolder(constraintLayout, name, country, humid, weather, windspeed, temp);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        holder.weather.setText(dataSuplier.getWeather(position));
        holder.windspeed.setText(dataSuplier.getWindspeed(position));
        holder.humid.setText(dataSuplier.getHumid(position));
        holder.temp.setText(dataSuplier.getTemp(position));
        holder.country.setText(dataSuplier.getCountry(position));
        holder.name.setText(dataSuplier.getName(position));

        holder.cl.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                deleteWeather(holder.getAdapterPosition(), view);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSuplier.getSize();

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public ConstraintLayout cl;
        public TextView name;
        public TextView country;
        public TextView temp;
        public TextView humid;
        public TextView windspeed;
        public TextView weather;

        public MyViewHolder(ConstraintLayout CL, TextView name, TextView country, TextView temp, TextView humid, TextView windspeed, TextView weather) {
            super(CL);
            this.cl = CL;
            this.name = name;
            this.country = country;
            this.temp = temp;
            this.humid = humid;
            this.windspeed = windspeed;
            this.weather = weather;
        }
    }
    public void deleteWeather(final int pos, View view) {

        String msg = view.getContext().getResources().getString(R.string.snackdelete) + " " + dataSuplier.getName(pos);
        Snackbar snackbar = Snackbar.make(view, msg, 3500);
        snackbar.setAction("Confirm", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSuplier.removeWeather(pos);

            }
        });
        snackbar.show();
    }


}
