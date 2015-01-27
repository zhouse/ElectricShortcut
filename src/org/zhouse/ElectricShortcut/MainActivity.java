package org.zhouse.ElectricShortcut;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
 
public class MainActivity extends Activity {
	ArrayList<ObjectShop> listaSklepow;
    private ListView list, listView1 ;  
    private ArrayAdapter<String> adapter2 ;  
    RowAdapter adapter;
    public static Typeface custom_font;
   
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        custom_font= Typeface.createFromAsset(getAssets(), "GOTHICBI.TTF");
   
        
        listaSklepow= new ArrayList<ObjectShop>();
 
        list = (ListView) findViewById(R.id.listView1);
  
        adapter = new RowAdapter(this, R.layout.textview, listaSklepow);
    
        listView1 = (ListView)findViewById(R.id.listView1);
        listView1.setAdapter(adapter);
        
        
        new JSONAsyncTask().execute("");
        
    }
    
  //**********
  //ASYNC TASK
  //**********	
  	
    
    //******************
  	class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {


  		@Override
  		protected void onPreExecute() {
  		    super.onPreExecute();

  		}

  		@Override
  		protected Boolean doInBackground(String... urls) {
  		    try {

  		        //------------------>>
  		        HttpGet httppost = new HttpGet("http://android4delivery.com/restaurants/ola.php?type=get_shops");
  		        HttpClient httpclient = new DefaultHttpClient();
  		        HttpResponse response = httpclient.execute(httppost);

  		        // StatusLine stat = response.getStatusLine();
  		        int status = response.getStatusLine().getStatusCode();

  		        Log.d("ActivityMain", "status "+status);
  		        
  		        if (status == 200) {
  		            HttpEntity entity = response.getEntity();
  		            String data = EntityUtils.toString(entity);
  		            
  	  		        Log.d("ActivityMain", "data "+data);
  		            

  		            JSONArray jsono = new JSONArray(data);

  		            for(int i=0; i<jsono.length(); i++){
  		            	ObjectShop sklep;
  		            	sklep=new ObjectShop(jsono.getJSONObject(i));
  		            	listaSklepow.add(sklep);
  		            	Log.d("","sklep "+listaSklepow.get(listaSklepow.size()-1).getName());
  		            }
  		            
  		           
  		            return true;
  		        }


  		    } catch (IOException e) {
  		        e.printStackTrace();
  		    } catch (JSONException e) {

  		        e.printStackTrace();
  		    }
  		    return false;
  		}

  		protected void onPostExecute(Boolean result) {
	            adapter.data=listaSklepow;
	            adapter.notifyDataSetChanged();
  		}
  	
  	}
    
    
 
    
    
    
    
    
    
    
    
    
    
    
    
    
}