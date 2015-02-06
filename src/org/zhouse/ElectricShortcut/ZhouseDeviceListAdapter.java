package org.zhouse.ElectricShortcut;

import java.util.ArrayList;

import org.zhouse.libs.ListViewHelper;
import org.zhouse.libs.ObjectDevice;
import org.zhouse.libs.ObjectVeraScene;
import org.zhouse.libs.ObjectVirtualDevice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ZhouseDeviceListAdapter extends ArrayAdapter<ObjectDevice> {
	static final String TAG="ZhouseDeviceList";
	
	public ArrayList<ListViewHelper> helpersList;
	public ArrayList<ObjectDevice> deviceList;
	

	Context mainContext;
    
    public ZhouseDeviceListAdapter(Context context, int textViewResourceId, ArrayList<ObjectDevice> list) {
    	super(context, textViewResourceId);
    	mainContext=context;
    	
    	/**
    	 * Convert ArrayList<ObjectDevice> to ArrayList<ListViewHelper>
    	 */
    	helpersList = new ArrayList<ListViewHelper>();
    	for (int i = 0; i < list.size(); i++) {
    		helpersList.add(new ListViewHelper(list.get(i).GetID(), list.get(i).GetCategory(), list.get(i).GetRoom(), list.get(i).GetName()));
		}
    	
    }

//*******************************
//
//      GET VIEW FUNCTIONS
//
//*******************************  
    
    public int getViewTypeCount() {
        return 15;
    }
    
    public int getItemViewType(int position) {		
		return helpersList.get(position).GetViewType();
    }
    
	@Override
	public int getCount() {
		return helpersList.size();
	}


    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
		int deviceImage;
		holder=new ViewHolder();
		String[] values;
		String device_value="";
		int category_number=0;
		String device_name="";
		
		if (helpersList.get(position).GetCategory() == 2) {
			deviceImage = R.drawable.dimmer_icon_active;
		}
		else {
			deviceImage = R.drawable.switch_icon_active;
		}
		
		category_number=helpersList.get(position).GetCategory();
		device_name=helpersList.get(position).GetName();
		
    
		switch (getItemViewType(position)){
		case 0:			
			
		    if (convertView == null || convertView.getTag()==null) {
				convertView = View.inflate(mainContext, R.layout.child_row, null);
				holder.devicePositionUnits = (TextView)convertView.findViewById(R.id.textStateUnits);
				holder.devicePosition=(TextView)convertView.findViewById(R.id.textState);
				holder.deviceImagePosition=(ImageView)convertView.findViewById(R.id.imageState);
				holder.devicePosition.setTypeface(MainActivity.custom_font);
				holder.deviceName =(TextView)convertView.findViewById(R.id.grp_child);
				holder.deviceName.setTypeface(MainActivity.custom_font);
				
				convertView.setTag(holder);
		    } else {
		        holder = (ViewHolder) convertView.getTag();
		    }
		    
		    holder.deviceName.setCompoundDrawablesWithIntrinsicBounds(mainContext.getResources().getDrawable(deviceImage), null, null, null);
		    holder.deviceName.setText(device_name);
		    holder.deviceName.setTypeface(MainActivity.custom_font);
	
		    int stateImage=0;
		    String stateString="";
		    String stateStringUnits="";
		    String value=device_value;
			value=value.replaceAll("\\s","");
    
		    switch (category_number){
				case 2:		if (value!="") {stateString=value; stateStringUnits="%";}  break;
				case 3: 	if (value.contains("0")) stateImage=R.drawable.off_icon; else if (value.contains("1")) stateImage=R.drawable.on_icon; break;
		    	}
		    		    
		    holder.devicePositionUnits.setVisibility(View.VISIBLE);
		    holder.devicePosition.setVisibility(View.VISIBLE);
		    holder.deviceImagePosition.setVisibility(View.VISIBLE);

		    if (stateString!="") {
		    	holder.devicePosition.setText(stateString); 
		    	holder.devicePosition.setVisibility(View.VISIBLE);
		    	} else holder.devicePosition.setVisibility(View.GONE);
		    
		    if (stateStringUnits!="") {
		    	holder.devicePositionUnits.setText(stateStringUnits);
		    	holder.devicePositionUnits.setVisibility(View.VISIBLE);
		    	} else holder.devicePositionUnits.setVisibility(View.GONE);

		    if (stateImage!=0) {
		    	holder.deviceImagePosition.setImageDrawable(mainContext.getResources().getDrawable(stateImage)); 
		    	holder.deviceImagePosition.setVisibility(View.VISIBLE);
			} else holder.deviceImagePosition.setVisibility(View.GONE);

		    break;
		
			}
        return convertView;
    }
    
    
    static class ViewHolder{
       	@Override
		public String toString() {
			return "aaa "+deviceName.getText();       		
		}
		ImageView deviceImage;
       	TextView deviceName;
       	TextView devicePositionUnits;
       	TextView devicePosition;
       	TextView weatherDescription;
       	ImageView deviceImagePosition;
       	TextView astronomicSet;
       	TextView astronomicRise;
       	TextView astronomicSunSet;
       	TextView astronomicSunRise;
       	TextView astronomicMoonSet;
       	TextView astronomicMoonRise;    	
       	TextView chronosUp;
       	TextView chronosHash;
       	TextView chronosStar;
    }
        

//*******************************
//
//      ON CLICK ROUTINES
//
//*******************************      
/*
    private OnItemClickListener deviceClick = new OnItemClickListener(){

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			ClickDevicesRoutines clickRoutines=new ClickDevicesRoutines(mainContext);

			Log.d(TAG, "clicked: "+helpersList.get(arg2).GetName()+" cat: "+helpersList.get(arg2).GetCategory());
			
			if (helpersList.get(arg2).GetCategory()==27){
	        	clickRoutines.runDevice(ConnectionService.veraServer.GetVeraScenes().GetScene(helpersList.get(arg2).GetId()));				
			} else if (ConnectionService.veraServer.GetDevices().GetDevice(helpersList.get(arg2).GetId())!=null){
	        	clickRoutines.runDevice(ConnectionService.veraServer.GetDevices().GetDevice(helpersList.get(arg2).GetId()));
			} else if (ConnectionService.veraServer.GetVirtualDevices().GetDevice(helpersList.get(arg2).GetId())!=null){
	        	clickRoutines.runDevice(ConnectionService.veraServer.GetVirtualDevices().GetDevice(helpersList.get(arg2).GetId()));
			}

		}
    };
*/

}
