package it.musa.client.activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import it.musa.client.Applicazione;
import it.musa.client.R;
import it.musa.client.vista.VistaClosestArtwork;

public class ActivityClosestArtwork extends AppCompatActivity {

    private ProgressDialog pd;

    // Fields in the view to fill
    private TextView nameArtwork;
    private ImageView imageArtwork;
    private TextView authorArtwork;
    private TextView yearArtwork;
    private TextView descriptionArtwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closest_artwork);

        // Initialize variables
        VistaClosestArtwork vistaClosestArtwork = (VistaClosestArtwork) getSupportFragmentManager().findFragmentById(R.id.vistaClosestArtwork);

        this.nameArtwork = vistaClosestArtwork.getNameArtwork();
        this.imageArtwork = vistaClosestArtwork.getImageArtwork();
        this.authorArtwork = vistaClosestArtwork.getAuthorArtwork();
        this.yearArtwork = vistaClosestArtwork.getYearArtwork();
        this.descriptionArtwork = vistaClosestArtwork.getDescriptionArtwork();

        // GET artwork data from the server and display data
        new getData().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        // GET image from the server and display it
        //new getImage().execute();

    }

    private class getData extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(Applicazione.getInstance().getCurrentActivity());
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {

            // Set up needed variables
            BufferedReader reader = null;
            HttpURLConnection conn = null;
            String json = null;

            try {

                // Retrieve userID
                String userID = (String) Applicazione.getInstance().getModello().getBean("userID");

                // Set up connection    local url: "http://10.0.2.2:5002/api/Musa/"+userID+"/details"
                URL url = new URL("http://musa-be.azurewebsites.net/api/Musa/aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa/details");
                conn = (HttpURLConnection) url.openConnection();
                conn.connect();

                InputStream stream = conn.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                // Read the data
                StringBuilder buffer = new StringBuilder();
                String line = "";
                while((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                    // Debug
                    Log.d("Response: ", "> " + buffer.toString());
                }

                json = buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            //return "{\"ID\":\"IDa\",\"Name\":\"The Welcome Tour\",\"TourArtworks\":\"Discobolus$Venus de Milo$Laocoon Group$Artemision Bronze\"}";
            return json;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pd.isShowing()){
                pd.dismiss();
            }

            try {

                // Retrieve JSONObject from GET, extract information and display it
                JSONObject jsonObject = new JSONObject(result);

                String artworkName = jsonObject.getString("Name");
                nameArtwork.append(artworkName + "\n\n");

                // Memorize image link GET the image after this
                String artworkImageLink = jsonObject.getString("Image");
                Applicazione.getInstance().getModello().putBean("artworkImageLink", artworkImageLink);

                String artworkAuthor = jsonObject.getString("Author");
                authorArtwork.append(artworkAuthor+"\n");

                String artworkYear = jsonObject.getString("Year");
                yearArtwork.append(artworkYear+"\n");

                String artworkDescription = jsonObject.getString("Description");
                descriptionArtwork.append(artworkDescription);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /*private class getImage extends AsyncTask<String, Void, Bitmap> {

        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(Applicazione.getInstance().getCurrentActivity());
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected Bitmap doInBackground(String... params) {

            // Set up needed variables
            BufferedReader reader = null;
            HttpURLConnection conn = null;
            //String json = null;
            Bitmap image = null;

            try {

                //Retrieve image link
                String artworkImageLink = (String) Applicazione.getInstance().getModello().getBean("artworkImageLink");

                // Set up connection
                URL url = new URL("http://musa-be.azurewebsites.net/images"+ artworkImageLink);
                conn = (HttpURLConnection) url.openConnection();
                conn.connect();

                InputStream stream = conn.getInputStream();

                image = BitmapFactory.decodeStream(stream);

                return image;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return image;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            if (pd.isShowing()) {
                pd.dismiss();
            }

            try {

                // Set image on view
                imageArtwork.setImageBitmap(result);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/
}