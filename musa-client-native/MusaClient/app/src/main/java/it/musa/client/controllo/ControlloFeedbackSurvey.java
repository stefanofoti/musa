package it.musa.client.controllo;

import android.view.View;

import it.musa.client.Applicazione;
import it.musa.client.activity.ActivityFeedbackSurvey;
import it.musa.client.activity.ActivitySurvey;
import it.musa.client.vista.VistaFeedbackSurvey;
import it.musa.client.vista.VistaSurvey;

public class ControlloFeedbackSurvey {

    private View.OnClickListener azioneSubmit = new AzioneSubmit();

    public View.OnClickListener getAzioneSubmit() {
        return azioneSubmit;
    }

    //////////////////////////////////////////
    private static class AzioneSubmit implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            // Get the current Activity with the view
            ActivityFeedbackSurvey activityFeedbackSurvey = (ActivityFeedbackSurvey) Applicazione.getInstance().getCurrentActivity();
            VistaFeedbackSurvey vistaFeedbackSurvey = activityFeedbackSurvey.getVistaFeedbackSurvey();
        }


    }
}
