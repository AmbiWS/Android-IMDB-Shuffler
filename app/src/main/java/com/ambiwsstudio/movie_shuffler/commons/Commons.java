package com.ambiwsstudio.movie_shuffler.commons;


import java.util.Locale;

public class Commons {

    public static String randomizeMovieId() {

        int moviesCount = 10_000_000;
        int randomId = (int)(Math.random() * moviesCount) + 1;
        return "tt" + String.format(Locale.US, "%07d", randomId);

    }


}
