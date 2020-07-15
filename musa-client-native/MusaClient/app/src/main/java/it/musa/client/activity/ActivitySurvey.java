package it.musa.client.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import it.musa.client.Applicazione;
import it.musa.client.R;
import it.musa.client.vista.VistaSurvey;

public class ActivitySurvey extends AppCompatActivity {

    private ProgressDialog progress;

    // Fields for RadioGroup values
    private String genderValue;
    private String movieValue;
    private String artStyleValue;
    private String timeValue;

    public static final String TAG = ActivitySurvey.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
    }

    public VistaSurvey getVistaLogin(){
        return (VistaSurvey) getSupportFragmentManager().findFragmentById(R.id.vistaSurvey);
    }

    // Get methods to return the values of the RadioGroup fields
    public String getGenderValue() {
        return genderValue;
    }

    public String getMovieValue() {
        return movieValue;
    }

    public String getArtStyleValue() {
        return artStyleValue;
    }

    public String getTimeValue() {
        return timeValue;
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

    // Add onClick event listeners to determine which is the value for each RadioGroup
    public void onRadioButtonGenderClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.femaleButton:
                if (checked)
                    this.genderValue = "F";
                    break;
            case R.id.maleButton:
                if (checked)
                    this.genderValue = "M";
                    break;
        }
    }

    public void onRadioButtonMovieClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.marieAntoinetteButton:
                if (checked)
                    this.movieValue = "MA";
                break;
            case R.id.enemyAtTheGatesButton:
                if (checked)
                    this.movieValue = "EG";
                break;
            case R.id.captainAmericaButton:
                if (checked)
                    this.movieValue = "CA";
                break;
            case R.id.spiritedAwayButton:
                if (checked)
                    this.movieValue = "SA";
                break;
        }
    }

    public void onRadioButtonArtStyleClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.normalArtButton:
                if (checked)
                    this.artStyleValue = "N";
                break;
            case R.id.cubaLisaArtStyle:
                if (checked)
                    this.artStyleValue = "C";
                break;
            case R.id.legoLisaArtStyle:
                if (checked)
                    this.artStyleValue = "L";
                break;
        }
    }

    public void onRadioButtonTimeClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.halfHourButton:
                if (checked)
                    this.timeValue = "hH";
                break;
            case R.id.hourButton:
                if (checked)
                    this.timeValue = "HH";
                break;
            case R.id.moreHoursButton:
                if (checked)
                    this.timeValue = "MH";
                break;
        }
    }



}
