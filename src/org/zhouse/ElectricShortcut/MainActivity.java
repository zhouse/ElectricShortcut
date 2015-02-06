package org.zhouse.ElectricShortcut;
 
import java.util.ArrayList;

import org.zhouse.libs.ObjectAllDevices;
import org.zhouse.libs.ObjectDevice;
import org.zhouse.libs.ZhouseVeraServer;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
 
public class MainActivity extends Activity {
	final static String TAG = "MainActivity";
	ArrayList<ObjectDevice> deviceList;
    private ListView listView1 ;    
    ZhouseDeviceListAdapter adapter;
    public static Typeface custom_font;
   
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        LinearLayout deviceLayout=(LinearLayout) findViewById(R.id.deviceLayout);
        LayoutParams deviceLayoutParams=deviceLayout.getLayoutParams();
        
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
           
        deviceLayoutParams.height=metrics.heightPixels;
        deviceLayoutParams.width= metrics.widthPixels;
        
        custom_font= Typeface.createFromAsset(getAssets(), "GOTHICBI.TTF");
   
        deviceList= new ArrayList<ObjectDevice>();
  
        adapter = new ZhouseDeviceListAdapter(this, R.layout.textview, deviceList);
    
        listView1 = (ListView)findViewById(R.id.listView1);
        listView1.setAdapter(adapter);
        
        /**
         * Creating ArrayList of devices
         */
        ZhouseVeraServer serwer = null;
        try {
			serwer = new ZhouseVeraServer(getBaseContext());
		} catch (Exception e) {
			Log.d(TAG, "Nie udało się zainicjalizować serwera");
			e.printStackTrace();
		}
       
        ObjectAllDevices allDevices = serwer.GetDevices();
        
        for (int i = 0; i < allDevices.GetDevicesCount(); i++) {	
        	if (allDevices.GetDevice(i).GetCategory() == 2 || allDevices.GetDevice(i).GetCategory() == 3) {	
        		deviceList.add(allDevices.GetDevice(i));
			}
			
		}
        
        
        
    }
    
    
  
    
    
    
    
    
    
    
    
    
    
    
    
    
}