package it.musa.client.activity;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseSettings;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.BeaconTransmitter;

import java.util.Arrays;

import it.musa.client.Applicazione;
import it.musa.client.R;
import it.musa.client.vista.VistaCollecting;

public class ActivityCollecting extends AppCompatActivity {

    private ProgressDialog progress;

    public static final String TAG = ActivityCollecting.class.getSimpleName();

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collecting);

        // Get userID
        String userID = (String) Applicazione.getInstance().getModello().getBean("userID");

        // Create an AltBeacon BLE beacon
        Beacon beacon = new Beacon.Builder()
                .setId1(userID)
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

        Applicazione.getInstance().getModello().putBean("beaconTransmitter", beaconTransmitter);
    }

    public void mostraActivityFinalThanks() {
        Intent intent = new Intent(this, ActivityFinalThanks.class);
        startActivity(intent);
    }

    public VistaCollecting getVistaCollecting() {
        return (VistaCollecting) getSupportFragmentManager().findFragmentById(R.id.vistaCollecting);
    }
}

