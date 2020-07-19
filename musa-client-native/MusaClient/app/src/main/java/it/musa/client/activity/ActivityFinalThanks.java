package it.musa.client.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import it.musa.client.R;
import it.musa.client.vista.VistaCollecting;
import it.musa.client.vista.VistaFinalThanks;

public class ActivityFinalThanks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_thanks);
    }

    public VistaFinalThanks getVistaFinalThanks() {
        return (VistaFinalThanks) getSupportFragmentManager().findFragmentById(R.id.vistaFinalThanks);
    }
}