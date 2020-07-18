package it.musa.client.vista;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.musa.client.R;

public class VistaTour extends Fragment {

    private TextView txtJSON;

    // TODO: add button to get info on nearest artowork and button to end tour

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.vista_tour, container, false);

        this.txtJSON = (TextView) vista.findViewById(R.id.textViewTour);

        inizializzaAzioni();
        return vista;
    }

    private void inizializzaAzioni(){
        //this.submitButton.setOnClickListener(Applicazione.getInstance().getControlloLogin().getAzioneSubmit());
    }

    public TextView getTxtJSON() {
        return txtJSON;
    }
}
