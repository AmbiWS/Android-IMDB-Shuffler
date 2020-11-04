package com.ambiwsstudio.movie_shuffler.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieService {

    private static final String BASE_URL = "https://movie-database-imdb-alternative.p.rapidapi.com";
    private static MovieService instance;
    private final Retrofit retrofit;

    private MovieService() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static MovieService getInstance() {

        if (instance == null)
            instance = new MovieService();

        return instance;

    }

    public MovieAPI getMovieAPI() {

        return retrofit.create(MovieAPI.class);

    }

}
