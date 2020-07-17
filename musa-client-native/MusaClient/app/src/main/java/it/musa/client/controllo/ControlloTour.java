package it.musa.client.controllo;

import android.view.View;

public class ControlloTour {

    private View.OnClickListener azioneTour = new ControlloTour.AzioneTour();

    public View.OnClickListener getAzioneTour() {
        return azioneTour;
    }

    //////////////////////////////////////////
    private static class AzioneTour implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }
}
