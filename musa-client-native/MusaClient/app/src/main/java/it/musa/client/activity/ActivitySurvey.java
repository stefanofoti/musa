package it.musa.client.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import it.musa.client.Applicazione;
import it.musa.client.R;
import it.musa.client.vista.VistaSurvey;

public class ActivitySurvey extends AppCompatActivity {

    private ProgressDialog progress;

    public static final String TAG = ActivitySurvey.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
    }

    public VistaSurvey getVistaLogin(){
        return (VistaSurvey) getSupportFragmentManager().findFragmentById(R.id.vistaSurvey);
    }

    public void mostraMessaggioErrore(String messaggio){
        Toast.makeText(Applicazione.getInstance(), messaggio, Toast.LENGTH_LONG).show();
    }

    public void mostraFinestraCaricamento(){
        progress = ProgressDialog.show(this, "MuSa", "Loading...", true);
    }

    public void chiudiFinestraCaricamento(){
        progress.dismiss();
    }

}
