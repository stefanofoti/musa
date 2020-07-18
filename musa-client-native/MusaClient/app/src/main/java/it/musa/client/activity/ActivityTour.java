package it.musa.client.activity;

import android.annotation.TargetApi;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseSettings;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.BeaconTransmitter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;

import javax.net.ssl.HttpsURLConnection;

import it.musa.client.Applicazione;
import it.musa.client.R;

public class ActivityTour extends AppCompatActivity {

    public static final String TAG = ActivityCollecting.class.getSimpleName();

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);

        // GET the tour from the server
        String tourId = (String) Applicazione.getInstance().getModello().getBean("tourID");

        // Create an AltBeacon BLE beacon
        Beacon beacon = new Beacon.Builder()
                .setId1("2f234454-cf6d-4a0f-adf2-f4911ba9ffa6")
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

    private class getTour extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            try {

                // Set up connection
                URL url = new URL("azureGETTourURL");
                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                conn.connect();

                InputStream inputStream = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            } catch (Exception e) {
                e.printStackTrace();
            }

            // Shut up compiler
            return null;
        }
    }
}