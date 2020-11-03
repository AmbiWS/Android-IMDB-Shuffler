package com.ambiwsstudio.movie_shuffler.commons;

import java.util.Locale;

public class Commons {

    public static String randomizeMovieId() {

        int moviesIdFloor = 400_000;
        int moviesIdCeil = 2_000_000;
        int randomId = (int)(Math.random() * (moviesIdCeil - moviesIdFloor)) + moviesIdFloor;
        return "tt" + String.format(Locale.US, "%07d", randomId);

    }

}
