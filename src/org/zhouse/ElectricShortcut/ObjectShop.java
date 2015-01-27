package org.zhouse.ElectricShortcut;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class ObjectShop {
	private String Nazwa;
	private double Lon, Lat;
	private int Open, Close;
	
	public ObjectShop(){
		
	}
//********
//SET
//********
	
	public void SetName(String nazwa){
		this.Nazwa=nazwa;
		
	}
	
	
	public void SetLocalization(double lat, double lon){
		this.Lat=lat;
		this.Lon=lon;
	}
	
	
	public void SetHours(int open, int close){
		this.Open=open;
		this.Close=close;
	}
	
//*******
//GET
//*******
	
	public String getName(){
		return this.Nazwa;
	
	}
	
	public double getLon(){
		return this.Lon;
	}
	
	public double getLat(){
		return this.Lat;
		
	}
	
	public int getOpen(){
		return this.Open;
		
	}
	
	public int getClose(){
		return this.Close;
		
	}
	
	public ObjectShop(JSONObject json){
		try{
			this.Nazwa=json.getString("name");
			this.Open=json.getInt("open-time");
			this.Close=json.getInt("close-time");
			this.Lat=Double.parseDouble(json.getString("lat"));
			this.Lon=Double.parseDouble(json.getString("lon"));
			
			
		} catch(JSONException e){
			
		}
	}
	
//*******
//OTHERS
//*******	

	public double getOdleglosc(double Lat2,double Lon2){
	    double earthRadius = 6371; //kilometers
	    double dLat = Math.toRadians(Lat2-this.Lat);
	    double dLng = Math.toRadians(Lon2-this.Lon);
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(this.Lat)) * Math.cos(Math.toRadians(Lat2)) *
	               Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    float dist = (float) (earthRadius * c);
		return dist;
	}
	
	
}
