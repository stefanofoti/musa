package it.musa.client.controllo;

import android.util.Log;
import android.view.View;

import org.altbeacon.beacon.BeaconTransmitter;

import it.musa.client.Applicazione;
import it.musa.client.activity.ActivityCollecting;
import it.musa.client.activity.ActivitySurvey;
import it.musa.client.vista.VistaCollecting;
import it.musa.client.vista.VistaSurvey;

public class ControlloCollecting {

    private View.OnClickListener azioneSubmit = new AzioneSubmit();

    public View.OnClickListener getAzioneSubmit() {
        return azioneSubmit;
    }

    //////////////////////////////////////////
    private static class AzioneSubmit implements View.OnClickListener {

        private static final String TAG = ActivityCollecting.class.getSimpleName();

        @Override
        public void onClick(View v) {

            // Get the current Activity
            ActivityCollecting activityCollecting = (ActivityCollecting) Applicazione.getInstance().getCurrentActivity();

            // Start the FinalThanks activity
            activityCollecting.mostraActivityFinalThanks();

            // Stop sending Beacons
            BeaconTransmitter beaconTransmitter = (BeaconTransmitter) Applicazione.getInstance().getModello().getBean("beaconTransmitter");
            beaconTransmitter.stopAdvertising();
            Log.i(TAG, "Advertisement stop succeeded.");
        }
    }
}
