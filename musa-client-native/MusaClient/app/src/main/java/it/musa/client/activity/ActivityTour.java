package it.musa.client.activity;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseSettings;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.BeaconTransmitter;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import javax.net.ssl.HttpsURLConnection;

import it.musa.client.Applicazione;
import it.musa.client.R;
import it.musa.client.vista.VistaSurvey;
import it.musa.client.vista.VistaTour;

public class ActivityTour extends AppCompatActivity {

    // Variables needed to get the tour (JSON) and display it
    private TextView txtJson;
    private ProgressDialog pd;

    public static final String TAG = ActivityCollecting.class.getSimpleName();

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);

        // Initialize variable
        VistaTour vistaTour = (VistaTour) getSupportFragmentManager().findFragmentById(R.id.vistaTour);
        txtJson = vistaTour.getTxtJSON();

        // GET the tour from the server
        new getTour().execute();

        // Create an AltBeacon BLE beacon
        Beacon beacon = new Beacon.Builder()
                .setId1("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb")
                .setId2("1")
                .setId3("2")
                .setManufacturer(0x0118)    // Radius network
                .setTxPower(-59)
                .setDataFields(Arrays.asList(new Long[] {0l}))
                .build();

        BeaconParser beaconParser = new BeaconParser()
                .setBeaconLayout("m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25");

        // Transmit beacons
        BeaconTransmitter beaconTransmitter = new BeaconTransmitter(getApplicationContext(), beaconParser);
        beaconTransmitter.startAdvertising(beacon, new AdvertiseCallback() {
            @Override
            public void onStartSuccess(AdvertiseSettings settingsInEffect) {
                Log.i(TAG, "Advertisement start succeeded.");
            }

            @Override
            public void onStartFailure(int errorCode) {
                Log.e(TAG, "Advertisement start failed with code: "+ errorCode);
            }
        });

    }

    private class getTour extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(Applicazione.getInstance().getCurrentActivity());
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {

            // Set up needed variables
            BufferedReader reader = null;
            HttpURLConnection conn = null;

            /*try {

                //Retrieve tourID   azureGETTourURL/tourID
                String tourID = (String) Applicazione.getInstance().getModello().getBean("tourID");

                // Set up connection
                URL url = new URL("http://ip.jsontest.com/");
                conn = (HttpURLConnection) url.openConnection();
                conn.connect();

                InputStream stream = conn.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                // Read the data
                StringBuilder buffer = new StringBuilder();
                String line = "";
                while((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                    // Debug
                    Log.d("Response: ", "> " + buffer.toString());
                }

                return buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }*/
            return "{\"ID\":\"IDa\",\"Name\":\"The Welcome Tour\",\"TourArtworks\":\"Discobolus$Venus de Milo$Laocoon Group$Artemision Bronze\"}";
            //return result
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pd.isShowing()){
                pd.dismiss();
            }

            try {

                // Retrieve JSONObject from GET, extract artoworks and display them (result)
                JSONObject jsonObject = new JSONObject("{\"ID\":\"IDa\",\"Name\":\"The Welcome Tour\",\"TourArtworks\":\"Discobolus$Venus de Milo$Laocoon Group$Artemision Bronze\"}");
                String artworks = jsonObject.getString("TourArtworks");
                String[] artworksArray = artworks.split("\\$");
                for (int i = 0; i < artworksArray.length; i++) {
                    txtJson.append("\n"+artworksArray[i] + "\n\n");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // TODO: this needs to be done well -> parse JSON and for each artwork show basic info
        }
    }
}