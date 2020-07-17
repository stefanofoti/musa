package it.musa.client.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import it.musa.client.R;
import it.musa.client.vista.VistaCollecting;

public class ActivityCollecting extends AppCompatActivity {

    private ProgressDialog progress;

    public static final String TAG = ActivityCollecting.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collecting);
    }

    public VistaCollecting getVistaCollecting() {
        return (VistaCollecting) getSupportFragmentManager().findFragmentById(R.id.vistaCollecting);
    }

}
