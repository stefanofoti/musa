package it.musa.client.vista;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import it.musa.client.R;

public class VistaClosestArtwork extends Fragment {

    // Fields in the view
    private TextView nameArtwork;
    private ImageView imageArtwork;
    private TextView authorArtwork;
    private TextView yearArtwork;
    private TextView descriptionArtwork;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.vista_closest_artwork, container, false);

        this.nameArtwork = (TextView) vista.findViewById(R.id.nameArtwork);
        this.imageArtwork = (ImageView) vista.findViewById(R.id.imageArtwork);
        this.authorArtwork = (TextView) vista.findViewById(R.id.authorArtwork);
        this.yearArtwork = (TextView) vista.findViewById(R.id.yearArtwork);
        this.descriptionArtwork = (TextView) vista.findViewById(R.id.descriptionArtwork);

        return vista;
    }

    public TextView getNameArtwork() {
        return nameArtwork;
    }

    public ImageView getImageArtwork() {
        return imageArtwork;
    }

    public TextView getAuthorArtwork() {
        return authorArtwork;
    }

    public TextView getYearArtwork() {
        return yearArtwork;
    }

    public TextView getDescriptionArtwork() {
        return descriptionArtwork;
    }
}
