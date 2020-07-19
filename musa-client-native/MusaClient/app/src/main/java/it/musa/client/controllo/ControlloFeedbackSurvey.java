package it.musa.client.controllo;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import it.musa.client.Applicazione;
import it.musa.client.activity.ActivityFeedbackSurvey;
import it.musa.client.activity.ActivitySurvey;
import it.musa.client.vista.VistaFeedbackSurvey;
import it.musa.client.vista.VistaSurvey;

public class ControlloFeedbackSurvey {

    private View.OnClickListener azioneSubmit = new AzioneSubmit();

    public View.OnClickListener getAzioneSubmit() {
        return azioneSubmit;
    }

    //////////////////////////////////////////
    private static class AzioneSubmit implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            // Get the current Activity with the view
            ActivityFeedbackSurvey activityFeedbackSurvey = (ActivityFeedbackSurvey) Applicazione.getInstance().getCurrentActivity();
            VistaFeedbackSurvey vistaFeedbackSurvey = activityFeedbackSurvey.getVistaFeedbackSurvey();

            // Get data from the view
            String funValue = activityFeedbackSurvey.getFunValue();
            String experienceValue = activityFeedbackSurvey.getExperienceValue();
            String learningValue = activityFeedbackSurvey.getLearningValue();
            String museumValue = activityFeedbackSurvey.getMuseumValue();
            String friendValue = activityFeedbackSurvey.getFriendValue();

            // When the user presses "SUBMIT" the app tries to send the data of the survey to the server
            try {
                if (sendFormData(activityFeedbackSurvey, funValue, experienceValue, learningValue, museumValue, friendValue) == 0) {
                    activityFeedbackSurvey.mostraActivityFinalThanks();
                }
                // Catch errors
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        // Function to store the data of the form and to send it to the server
        public int sendFormData(ActivityFeedbackSurvey activityFeedbackSurvey, String funValue, String experienceValue, String learningValue, String museumValue, String friendValue) {

            // POST data (first check if all the fields of the survey are filled)
            if (funValue != null && experienceValue != null && learningValue != null && museumValue != null && friendValue != null) {
                //new PostData().execute(funValue, experienceValue, learningValue, museumValue, friendValue);
                return 0;
            } else {
                // Show a message to the user
                activityFeedbackSurvey.mostraMessaggioErrore("Please fill all the fields in the survey");

                return 1;

            }
        }
    }

    // Class that sends the POST request to the backend on Azure
    private static class PostData extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            try {

                // Set up the connection
                URL url = new URL("http://10.0.2.2:5002/api/Tour/GetTour");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);

                // Prepare JSON data to be sent
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("funValue", params[0]);
                jsonParam.put("experienceValue", params[1]);
                jsonParam.put("learningValue", params[2]);
                jsonParam.put("museumValue", params[3]);
                jsonParam.put("friendValue", params[4]);
                jsonParam.put("userID", "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");

                // Log the JSON object to see if everything is fine
                Log.i("JSON", jsonParam.toString());

                // Send the data
                DataOutputStream outputStream = new DataOutputStream(conn.getOutputStream());
                outputStream.writeBytes(jsonParam.toString());
                outputStream.flush();
                outputStream.close();

                // Other logs to see if everything went fine
                Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                Log.i("MESSAGE", conn.getResponseMessage());

                // Close connection
                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
