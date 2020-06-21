package it.musa.client.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import it.musa.client.R;
import it.musa.client.vista.VistaWelcome;

public class ActivityWelcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public VistaWelcome getVistaWelcome(){
        return (VistaWelcome) getSupportFragmentManager().findFragmentById(R.id.vistaWelcome);
    }

    public void mostraActivitySurvey() {
        Intent intent = new Intent(this, ActivitySurvey.class);
        startActivity(intent);
    }

}
