package it.musa.client.controllo;

import android.view.View;

import it.musa.client.Applicazione;
import it.musa.client.activity.ActivitySurvey;
import it.musa.client.vista.VistaSurvey;

public class ControlloSurvey {

    private View.OnClickListener azioneSubmit = new AzioneSubmit();

    public View.OnClickListener getAzioneSubmit() {
        return azioneSubmit;
    }

    //////////////////////////////////////////
    private class AzioneSubmit implements View.OnClickListener {

        @Override
        public void onClick(View v) {


        }

    }
}
