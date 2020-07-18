package it.musa.client.vista;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;

import it.musa.client.Applicazione;
import it.musa.client.R;

public class VistaSurvey extends Fragment {

    // Form fields in the view
    private EditText ageEditText;

    private RadioButton genderFemaleButton;
    private RadioButton genderMaleButton;

    private RadioButton movieMarieAntoinetteButton;
    private RadioButton movieEnemyAtTheGatesButton;
    private RadioButton movieCaptainAmericaButton;
    private RadioButton movieSpiritedAwayButton;

    private RadioButton normalLisaArtStyleButton;
    private RadioButton cubaLisaArtStyleButton;
    private RadioButton legoLisaArtStyleButton;

    private RadioButton halfHourTimeButton;
    private RadioButton hourTimeButton;
    private RadioButton moreHoursTimeButton;

    private Switch switchTour;
    private Switch switchCollecting;

    private Button submitButton;



    // Constructor: initialized all the fields for the View and creates it
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.vista_survey, container, false);

        this.ageEditText = (EditText) vista.findViewById(R.id.surveyAgeInput);

        this.genderFemaleButton = (RadioButton) vista.findViewById(R.id.femaleButton);
        this.genderMaleButton = (RadioButton) vista.findViewById(R.id.maleButton);

        this.movieMarieAntoinetteButton = (RadioButton) vista.findViewById(R.id.marieAntoinetteButton);
        this.movieEnemyAtTheGatesButton = (RadioButton) vista.findViewById(R.id.enemyAtTheGatesButton);
        this.movieCaptainAmericaButton = (RadioButton) vista.findViewById(R.id.captainAmericaButton);
        this.movieSpiritedAwayButton = (RadioButton) vista.findViewById(R.id.spiritedAwayButton);

        this.normalLisaArtStyleButton = (RadioButton) vista.findViewById(R.id.normalArtButton);
        this.cubaLisaArtStyleButton = (RadioButton) vista.findViewById(R.id.cubaLisaArtStyle);
        this.legoLisaArtStyleButton = (RadioButton) vista.findViewById(R.id.legoLisaArtStyle);

        this.halfHourTimeButton = (RadioButton) vista.findViewById(R.id.halfHourButton);
        this.hourTimeButton = (RadioButton) vista.findViewById(R.id.hourButton);
        this.moreHoursTimeButton = (RadioButton) vista.findViewById(R.id.moreHoursButton);

        this.switchTour = (Switch) vista.findViewById(R.id.switchTour);
        this.switchCollecting = (Switch) vista.findViewById(R.id.switchCollecting);

        this.submitButton = (Button) vista.findViewById(R.id.submitSurveyButton);

        inizializzaAzioni();
        return vista;
    }

    private void inizializzaAzioni(){
        this.submitButton.setOnClickListener(Applicazione.getInstance().getControlloLogin().getAzioneSubmit());
    }

    // Get methods: they allow external classes to access the fields of this class
    public EditText getAgeEditText() {
        return ageEditText;
    }

    public RadioButton getFemaleButton() {
        return genderFemaleButton;
    }

    public RadioButton getGenderMaleButton() {
        return genderMaleButton;
    }

    public RadioButton getMovieMarieAntoinetteButton() {
        return movieMarieAntoinetteButton;
    }

    public RadioButton getMovieEnemyAtTheGatesButton() {
        return movieEnemyAtTheGatesButton;
    }

    public RadioButton getMovieCaptainAmericaButton() {
        return movieCaptainAmericaButton;
    }

    public RadioButton getMovieSpiritedAwayButton() {
        return movieSpiritedAwayButton;
    }

    public RadioButton getNormalLisaArtStyleButton() {
        return normalLisaArtStyleButton;
    }

    public RadioButton getCubaLisaArtStyleButton() {
        return cubaLisaArtStyleButton;
    }

    public RadioButton getLegoLisaArtStyleButton() {
        return legoLisaArtStyleButton;
    }

    public RadioButton getHalfHourTimeButton() {
        return halfHourTimeButton;
    }

    public RadioButton getHourTimeButton() {
        return hourTimeButton;
    }

    public RadioButton getMoreHoursTimeButton() {
        return moreHoursTimeButton;
    }

    public Switch getSwitchTour() {
        return switchTour;
    }

    public Switch getSwitchCollecting() {
        return switchCollecting;
    }

    public Button getSubmitButton() {
        return submitButton;
    }

    /*public void mostraErroreUsername(String messaggio){
        this.campoUsername.setError(messaggio);
    } Maybe useful in the future */

}
