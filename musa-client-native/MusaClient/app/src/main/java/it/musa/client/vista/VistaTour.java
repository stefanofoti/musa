package it.musa.client.vista;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.musa.client.Applicazione;
import it.musa.client.R;

public class VistaTour extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.vista_survey, container, false);

        inizializzaAzioni();
        return vista;
    }

    private void inizializzaAzioni(){
        //this.submitButton.setOnClickListener(Applicazione.getInstance().getControlloLogin().getAzioneSubmit());
    }
}
