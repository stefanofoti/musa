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

public class VistaSurvey extends Fragment {

    private EditText campoUsername;
    private EditText campoPassword;
    private Button bottoneSubmit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.vista_survey, container, false);
        this.bottoneSubmit = (Button) vista.findViewById(R.id.bottoneSubmitSurvey);
        inizializzaAzioni();
        return vista;
    }

    private void inizializzaAzioni(){
        //this.bottoneSubmit.setOnClickListener(Applicazione.getInstance().getControlloSubmit().getAzioneXXX());
    }

    public String getUsername() {
        return this.campoUsername.getText().toString();
    }

    public String getPassword() {
        return this.campoPassword.getText().toString();
    }

    public void mostraErroreUsername(String messaggio){
        this.campoUsername.setError(messaggio);
    }

    public void mostraErrorePassword(String messaggio){
        this.campoPassword.setError(messaggio);
    }

}
