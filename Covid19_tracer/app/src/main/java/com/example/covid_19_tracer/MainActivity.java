package com.example.covid_19_tracer;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;

    // URL to get contacts JSON
    private static String url = "https://api.covid19india.org/state_district_wise.json";

    ArrayList<HashMap<String, String>> stateList = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.list);
        new GetStates().execute();
    }

    private class GetStates extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Loading ...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);


                    JSONObject state = jsonObj.getJSONObject("Rajasthan");
                    JSONObject district = state.getJSONObject("districtData");
                    // looping through All Contacts

                    Iterator<?> dis = district.keys();
                    while(dis.hasNext() ) {
                        String key = (String)dis.next();
                        if ( district.get(key) instanceof JSONObject ) {
                            JSONObject c = new JSONObject(district.get(key).toString());

                            String dist  = key;
                            String active = c.getString("active");
                            String confirmed = c.getString("confirmed");
                            String deceased = c.getString("deceased");
                            String recovered = c.getString("recovered");


                            HashMap<String, String> data = new HashMap<>();

                            data.put("district", dist);
                            data.put("active", active);
                            data.put("confirmed", confirmed);
                            data.put("deceased", deceased);
                            data.put("recovered", recovered);

                            stateList.add(data);
                        }
                    }

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, stateList,
                    R.layout.list_item, new String[]{"district", "active", "confirmed",
                    "deceased", "recovered"}, new int[]{R.id.district, R.id.name,
                    R.id.email, R.id.mobile, R.id.recovered});

            lv.setAdapter(adapter);
        }

    }
}