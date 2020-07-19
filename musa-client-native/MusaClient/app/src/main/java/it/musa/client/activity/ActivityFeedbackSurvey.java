package it.musa.client.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import it.musa.client.Applicazione;
import it.musa.client.R;
import it.musa.client.vista.VistaFeedbackSurvey;
import it.musa.client.vista.VistaSurvey;

public class ActivityFeedbackSurvey extends AppCompatActivity {

    // Fields for RadioGroup buttons
    private String funValue;
    private String experienceValue;
    private String learningValue;
    private String museumValue;
    private String friendValue;

    public static final String TAG = ActivityFeedbackSurvey.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_survey);
    }

    public VistaFeedbackSurvey getVistaFeedbackSurvey(){
        return (VistaFeedbackSurvey) getSupportFragmentManager().findFragmentById(R.id.vistaFeedbackSurvey);
    }

    public void mostraActivityFinalThanks() {
        Intent intent = new Intent(this, ActivityFinalThanks.class);
        startActivity(intent);
    }

    // Getter methods

    public String getFunValue() {
        return funValue;
    }

    public String getExperienceValue() {
        return experienceValue;
    }

    public String getLearningValue() {
        return learningValue;
    }

    public String getMuseumValue() {
        return museumValue;
    }

    public String getFriendValue() {
        return friendValue;
    }

    public void mostraMessaggioErrore(String messaggio){
        Toast.makeText(Applicazione.getInstance(), messaggio, Toast.LENGTH_LONG).show();
    }

    // Add onClick event listeners to determine which is the value for each RadioGroup
    public void onRadioButtonFeedbackSurveyFunValueClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.feedbackSurveyFunValue1:
                if (checked)
                    this.funValue = "1";
                break;
            case R.id.feedbackSurveyFunValue2:
                if (checked)
                    this.funValue = "2";
                break;
            case R.id.feedbackSurveyFunValue3:
                if (checked)
                    this.funValue = "3";
                break;
            case R.id.feedbackSurveyFunValue4:
                if (checked)
                    this.funValue = "4";
                break;
            case R.id.feedbackSurveyFunValue5:
                if (checked)
                    this.funValue = "5";
                break;
        }
    }

    public void onRadioButtonFeedbackSurveyExperienceValueClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.feedbackSurveyExperienceHighValue:
                if (checked)
                    this.experienceValue = "H";
                break;
            case R.id.feedbackSurveyExperienceMiddleValue:
                if (checked)
                    this.experienceValue = "M";
                break;
            case R.id.feedbackSurveyExperienceLowValue:
                if (checked)
                    this.experienceValue = "L";
                break;
        }
    }

    public void onRadioButtonFeedbackSurveyLearningValueClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.feedbackSurveyLearningHighValue:
                if (checked)
                    this.learningValue = "H";
                break;
            case R.id.feedbackSurveyLearningMiddleValue:
                if (checked)
                    this.learningValue = "M";
                break;
            case R.id.feedbackSurveyLearningLowValue:
                if (checked)
                    this.learningValue = "L";
                break;
        }
    }

    public void onRadioButtonFeedbackSurveyMuseumValueClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.feedbackSurveyMuseumHighValue:
                if (checked)
                    this.museumValue = "H";
                break;
            case R.id.feedbackSurveyMuseumMiddleValue:
                if (checked)
                    this.museumValue = "M";
                break;
            case R.id.feedbackSurveyMuseumLowValue:
                if (checked)
                    this.museumValue = "L";
                break;
        }
    }

    public void onRadioButtonFeedbackSurveyFriendValueClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.feedbackSurveyFriendHighValue:
                if (checked)
                    this.friendValue = "Y";
                break;
            case R.id.feedbackSurveyFriendMiddleValue:
                if (checked)
                    this.friendValue = "M";
                break;
            case R.id.feedbackSurveyFriendLowValue:
                if (checked)
                    this.friendValue = "N";
                break;
        }
    }

}
