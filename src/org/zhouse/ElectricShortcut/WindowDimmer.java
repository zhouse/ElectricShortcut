package org.zhouse.ElectricShortcut;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class WindowDimmer extends Activity {
    /** Called when the activity is first created. */

	static final String TAG="ZhouseWindowDimmer";
	SeekBar dimlvl;
	TextView dimlvltxt;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
    	{
        super.onCreate(savedInstanceState);
		WindowManager.LayoutParams lp = getWindow().getAttributes();  
		lp.dimAmount =0.5f;
		getWindow().setAttributes(lp);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND,WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        setContentView(R.layout.window_dimmer);

		//Finding Views
        dimlvl = (SeekBar)findViewById(R.id.dimm_lvl);        
        dimlvltxt = (TextView)findViewById(R.id.dim_lvl_txt);
        TextView item_name = (TextView)findViewById(R.id.textView1);
        Button maxButton = (Button)findViewById(R.id.dimmer_max);
        Button offButton = (Button)findViewById(R.id.dimmer_off);
        
        //Setting fonts and listeners
        maxButton.setTypeface(MainActivity.custom_font);
        maxButton.setOnClickListener(onBtnClickNum);
        offButton.setTypeface(MainActivity.custom_font);
        offButton.setOnClickListener(onBtnClickNum);
        dimlvl.setOnSeekBarChangeListener(onDimChange);
        item_name.setTypeface(MainActivity.custom_font);
        dimlvltxt.setTypeface(MainActivity.custom_font);
        
        //Setting values
        Intent myIntent = getIntent();        
        item_name.setText(myIntent.getStringExtra("ITEM_NAME"));
        String value=myIntent.getStringExtra("ITEM_VALUE");

        int dimValue;
        if (MainActivity.checkIfNumber(value)) dimValue=Integer.parseInt(value); else dimValue=0;
        dimlvl.setProgress(dimValue);
        dimlvltxt.setText(Integer.toString(dimlvl.getProgress())+"%");      
        
    	}

    private OnClickListener onBtnClickNum = new OnClickListener(){
      	 
        public void onClick(View v){
        	int value=0;
            if (v.getId() == R.id.dimmer_max) value=100;
			else if (v.getId() == R.id.dimmer_off) value=0;

            Intent intent = getIntent();
            int DeviceId= intent.getIntExtra("ITEM_MIOSDEVICEID", 0);
            	
        	for(int i=0;i<MainActivity.deviceList.size();i++){
        		if(MainActivity.deviceList.get(i).GetID()==DeviceId) MainActivity.deviceList.get(i).ChangeVariable("level", Integer.toString(value));
        	}
            finish();
        }
    };    	
		
	
    private OnSeekBarChangeListener onDimChange = new OnSeekBarChangeListener() {
		
		public void onStopTrackingTouch(SeekBar seekBar) {
	        SeekBar dimlvl = (SeekBar)findViewById(R.id.dimm_lvl);
                        
            Intent intent = getIntent();
            int DeviceId= intent.getIntExtra("ITEM_MIOSDEVICEID", 0);
        	for(int i=0;i<MainActivity.deviceList.size();i++){
        		if(MainActivity.deviceList.get(i).GetID()==DeviceId) MainActivity.deviceList.get(i).ChangeVariable("level", Integer.toString(dimlvl.getProgress()));
        	}
            finish();
		}
		
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
		
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			// TODO Auto-generated method stub
	        dimlvltxt.setText(Integer.toString(dimlvl.getProgress())+"%" );
		}
	};        
    
}