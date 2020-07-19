package it.musa.client.controllo;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import it.musa.client.Applicazione;
import it.musa.client.R;
import it.musa.client.activity.ActivitySurvey;
import it.musa.client.vista.VistaSurvey;

public class ControlloSurvey {

    private View.OnClickListener azioneSubmit = new AzioneSubmit();

    public View.OnClickListener getAzioneSubmit() {
        return azioneSubmit;
    }

    //////////////////////////////////////////
    private static class AzioneSubmit implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            // Get the current Activity with the view
            ActivitySurvey activitySurvey = (ActivitySurvey) Applicazione.getInstance().getCurrentActivity();
            VistaSurvey vistaSurvey = activitySurvey.getVistaLogin();

            // Get data from the view
            String ageValue = vistaSurvey.getAgeEditText().getText().toString();

            String genderValue = activitySurvey.getGenderValue();
            String movieValue = activitySurvey.getMovieValue();
            String artStyleValue = activitySurvey.getArtStyleValue();
            String timeValue = activitySurvey.getTimeValue();

            String useMusa = (vistaSurvey.getSwitchTour().isChecked()) ? "Y" : "N";
            String collectData = (vistaSurvey.getSwitchCollecting().isChecked()) ? "Y" : "N";

            // Variable where the ID of the tour corresponding to the user will be stored
            String tourID = "";

            // When the user presses "LET'S GO" the app tries to send the data of the survey to the server
            try {
                if (sendFormData(activitySurvey, ageValue, genderValue, movieValue, artStyleValue, timeValue, useMusa, collectData) == 0) {
                    if (useMusa == "Y") {

                        //Start tour activity
                        activitySurvey.mostraActivityTour();

                    } else {

                        // Start collect data activity
                        activitySurvey.mostraActivityCollecting();
                    }
                }
                // Catch errors
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        // Function to store the data of the form and to send it to the server
        public int sendFormData(ActivitySurvey activitySurvey, String ageValue, String genderValue, String movieValue, String artStyleValue, String timeValue, String useMusa, String collectData) {

            // POST data (first check if all the fields of the survey are filled)
            if (ageValue != null && genderValue != null && movieValue != null && artStyleValue != null && timeValue != null && collectData.equals("Y")) {
                //new PostData().execute(ageValue, genderValue, movieValue, artStyleValue, timeValue, useMusa, collectData);
                return 0;
            } else {
                // Show a message to the user
                activitySurvey.mostraMessaggioErrore("Please fill all the fields in the survey");

               /* Debug
               Log.i("ageValue", ageValue);
               Log.i("genderValue", genderValue);
               Log.i("movieValue", movieValue);
               Log.i("artStyleValue", artStyleValue);
               Log.i("timeValue", timeValue);
               Log.i("collectDataValue", collectData);*/

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
                URL url = new URL("https://10.0.2.2:5000/api/Tour/GetTour");
                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);

                // Prepare JSON data to be sent
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("ageValue", params[0]);
                jsonParam.put("genderValue", params[1]);
                jsonParam.put("movieValue", params[2]);
                jsonParam.put("artStyleValue", params[3]);
                jsonParam.put("timeValue", params[4]);
                jsonParam.put("useMusa", params[5]);
                jsonParam.put("collectData", params[6]);
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

                // TODO

                // Parse POST response to get the tour ID and save it
                InputStream stream = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                // Read the data
                StringBuilder buffer = new StringBuilder();
                String line = "";
                while((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                    // Debug
                    Log.d("Response: ", "> " + buffer.toString());
                }

                String tourID = buffer.toString();

                // Save tourId to later use it
                Applicazione.getInstance().getModello().putBean("tourID", tourID);

                // Close connection
                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}

