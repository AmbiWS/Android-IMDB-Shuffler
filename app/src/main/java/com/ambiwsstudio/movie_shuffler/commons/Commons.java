package com.ambiwsstudio.movie_shuffler.commons;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

public class Commons {

    public static String randomizeMovieId() {

        int moviesIdFloor = 400_000;
        int moviesIdCeil = 2_000_000;
        int randomId = (int)(Math.random() * (moviesIdCeil - moviesIdFloor)) + moviesIdFloor;
        return "tt" + String.format(Locale.US, "%07d", randomId);

    }

}
