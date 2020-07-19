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

public class VistaCollecting extends Fragment {

    // Submit button field
    private Button endCollecting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista =  inflater.inflate(R.layout.vista_collecting, container, false);

        this.endCollecting = (Button) vista.findViewById(R.id.endCollecting);

        inizializzaAzioni();
        return vista;
    }

    private void inizializzaAzioni(){
        this.endCollecting.setOnClickListener(Applicazione.getInstance().getControlloCollecting().getAzioneSubmit());
    }

}
