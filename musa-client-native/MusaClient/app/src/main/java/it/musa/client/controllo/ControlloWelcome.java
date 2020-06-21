package it.musa.client.controllo;

import android.view.View;

import it.musa.client.Applicazione;
import it.musa.client.activity.ActivityWelcome;
import it.musa.client.vista.VistaWelcome;

public class ControlloWelcome {
    private View.OnClickListener azioneWelcome = new ControlloWelcome.AzioneWelcome();

    public View.OnClickListener getAzioneWelcome() {
        return azioneWelcome;
    }

    //////////////////////////////////////////
    private class AzioneWelcome implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            ActivityWelcome activityWelcome = (ActivityWelcome) Applicazione.getInstance().getCurrentActivity();
            VistaWelcome vistaWelcome = activityWelcome.getVistaWelcome();
            activityWelcome.mostraActivitySurvey();
        }
    }
}
