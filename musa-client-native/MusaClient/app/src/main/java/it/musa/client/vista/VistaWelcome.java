package it.musa.client.vista;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import it.musa.client.Applicazione;
import it.musa.client.R;

public class VistaWelcome extends Fragment {


    private Button bottoneMeetMusa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.vista_welcome, container, false);
        this.bottoneMeetMusa = (Button) vista.findViewById(R.id.bottoneMeetMusa);
        inizializzaAzioni();
        return vista;
    }

    private void inizializzaAzioni(){
        this.bottoneMeetMusa.setOnClickListener(Applicazione.getInstance().getControlloWelcome().getAzioneWelcome());
    }

}
