package it.musa.client.vista;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import it.musa.client.Applicazione;
import it.musa.client.R;

public class VistaFeedbackSurvey extends Fragment {

    //Form fields in the view

    private RadioButton funValue1;
    private RadioButton funValue2;
    private RadioButton funValue3;
    private RadioButton funValue4;
    private RadioButton funValue5;

    private RadioButton experienceValueHigh;
    private RadioButton experienceValueMiddle;
    private RadioButton experienceValueLow;

    private RadioButton learningValueHigh;
    private RadioButton learningValueMiddle;
    private RadioButton learningValueLow;

    private RadioButton museumValueHigh;
    private RadioButton museumValueMiddle;
    private RadioButton museumValueLow;

    private RadioButton friendValueHigh;
    private RadioButton friendValueMiddle;
    private RadioButton friendValueLow;

    private Button submit;

    // Constructor: initializes all the fields for the View and creates it
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.vista_feedback_survey, container, false);

        this.funValue1 = (RadioButton) vista.findViewById(R.id.feedbackSurveyFunValue1);
        this.funValue2 = (RadioButton) vista.findViewById(R.id.feedbackSurveyFunValue2);
        this.funValue3 = (RadioButton) vista.findViewById(R.id.feedbackSurveyFunValue3);
        this.funValue4 = (RadioButton) vista.findViewById(R.id.feedbackSurveyFunValue4);
        this.funValue5 = (RadioButton) vista.findViewById(R.id.feedbackSurveyFunValue5);

        this.experienceValueHigh = (RadioButton) vista.findViewById(R.id.feedbackSurveyExperienceHighValue);
        this.experienceValueMiddle = (RadioButton) vista.findViewById(R.id.feedbackSurveyExperienceMiddleValue);
        this.experienceValueLow = (RadioButton) vista.findViewById(R.id.feedbackSurveyExperienceLowValue);

        this.learningValueHigh = (RadioButton) vista.findViewById(R.id.feedbackSurveyLearningHighValue);
        this.learningValueMiddle = (RadioButton) vista.findViewById(R.id.feedbackSurveyLearningMiddleValue);
        this.learningValueLow = (RadioButton) vista.findViewById(R.id.feedbackSurveyLearningLowValue);

        this.museumValueHigh = (RadioButton) vista.findViewById(R.id.feedbackSurveyMuseumHighValue);
        this.museumValueMiddle = (RadioButton) vista.findViewById(R.id.feedbackSurveyMuseumMiddleValue);
        this.museumValueLow = (RadioButton) vista.findViewById(R.id.feedbackSurveyMuseumLowValue);

        this.friendValueHigh = (RadioButton) vista.findViewById(R.id.feedbackSurveyFriendHighValue);
        this.friendValueMiddle = (RadioButton) vista.findViewById(R.id.feedbackSurveyFriendMiddleValue);
        this.friendValueLow = (RadioButton) vista.findViewById(R.id.feedbackSurveyFriendLowValue);

        this.submit = (Button) vista.findViewById(R.id.submitFeedbackSurveyButton);

        inizializzaAzioni();
        return vista;
    }

    private void inizializzaAzioni(){
        this.submit.setOnClickListener(Applicazione.getInstance().getControlloFeedbackSurvey().getAzioneSubmit());
    }

    // Get methods: they allow external classes to access the fields of this class

    public RadioButton getFunValue1() {
        return funValue1;
    }

    public RadioButton getFunValue2() {
        return funValue2;
    }

    public RadioButton getFunValue3() {
        return funValue3;
    }

    public RadioButton getFunValue4() {
        return funValue4;
    }

    public RadioButton getFunValue5() {
        return funValue5;
    }

    public RadioButton getExperienceValueHigh() {
        return experienceValueHigh;
    }

    public RadioButton getExperienceValueMiddle() {
        return experienceValueMiddle;
    }

    public RadioButton getExperienceValueLow() {
        return experienceValueLow;
    }

    public RadioButton getLearningValueHigh() {
        return learningValueHigh;
    }

    public RadioButton getLearningValueMiddle() {
        return learningValueMiddle;
    }

    public RadioButton getLearningValueLow() {
        return learningValueLow;
    }

    public RadioButton getMuseumValueHigh() {
        return museumValueHigh;
    }

    public RadioButton getMuseumValueMiddle() {
        return museumValueMiddle;
    }

    public RadioButton getMuseumValueLow() {
        return museumValueLow;
    }

    public RadioButton getFriendValueHigh() {
        return friendValueHigh;
    }

    public RadioButton getFriendValueMiddle() {
        return friendValueMiddle;
    }

    public RadioButton getFriendValueLow() {
        return friendValueLow;
    }

    public Button getSubmit() {
        return submit;
    }
}

