package org.zhouse.ElectricShortcut;

import java.util.ArrayList;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class RowAdapter extends ArrayAdapter<ObjectShop>{
 
    Context context;
    int layoutResourceId;   
    public ArrayList<ObjectShop> data = new ArrayList<ObjectShop>();
    protected LocationManager locationManager;
    static final String TAG="RowAdapter";
 
    public RowAdapter(Context context, int layoutResourceId, ArrayList<ObjectShop> data) {
        super(context, layoutResourceId, data); 
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RowHolder holder = null;
 
        if(row == null){
            LayoutInflater inflater = ((MainActivity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
 
            holder = new RowHolder();
            holder.nazwaSklepu = (TextView)row.findViewById(R.id.nazwa);
            holder.odleglosc = (TextView)row.findViewById(R.id.dystans);
            holder.godziny = (TextView)row.findViewById(R.id.godziny);
            holder.nazwaSklepu.setTypeface(MainActivity.custom_font);
            row.setTag(holder);
        } else {
            holder = (RowHolder)row.getTag();
        }
 
           
        ObjectShop object = data.get(position);
        holder.nazwaSklepu.setText(object.getName());
        
        holder.godziny.setText(String.valueOf(object.getOpen())+"-"+String.valueOf(object.getClose()));
     
        return row;
    }
 
    static class RowHolder{
             TextView nazwaSklepu, odleglosc, godziny;
    }
}