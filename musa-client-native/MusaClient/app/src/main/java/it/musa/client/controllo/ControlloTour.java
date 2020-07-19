package it.musa.client.controllo;

import android.util.Log;
import android.view.View;

import org.altbeacon.beacon.BeaconTransmitter;

import it.musa.client.Applicazione;
import it.musa.client.activity.ActivityCollecting;
import it.musa.client.activity.ActivitySurvey;
import it.musa.client.activity.ActivityTour;
import it.musa.client.vista.VistaSurvey;

public class ControlloTour {

    private View.OnClickListener azioneEndTour = new ControlloTour.AzioneEndTour();

    public View.OnClickListener getAzioneEndTourTour() {
        return azioneEndTour;
    }

    public View.OnClickListener azioneClosestArtwork = new ControlloTour.AzioneClosestArtwork();

    public View.OnClickListener getAzioneClosestArtwork() {
        return azioneClosestArtwork;
    }

    //////////////////////////////////////////
    // Listener for EndTourButton
    private static class AzioneEndTour implements View.OnClickListener {

        public static final String TAG = ActivityTour.class.getSimpleName();

        @Override
        public void onClick(View v) {

            // Get the current Activity with the view
            ActivityTour activityTour = (ActivityTour) Applicazione.getInstance().getCurrentActivity();
            //VistaSurvey vistaSurvey = activityTour.getVistaLogin();

            // Stop sending Beacons
            BeaconTransmitter beaconTransmitter = activityTour.getBeaconTransmitter();
            beaconTransmitter.stopAdvertising();
            Log.i(TAG, "Advertisement stop succeeded.");

            activityTour.mostraActivityFeedbackSurvey();

        }
    }

    //TODO
    private static class AzioneClosestArtwork implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }
}


