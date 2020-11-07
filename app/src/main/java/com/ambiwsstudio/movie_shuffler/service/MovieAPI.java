package com.ambiwsstudio.movie_shuffler.service;

import com.ambiwsstudio.movie_shuffler.model.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface MovieAPI {

    @Headers({
            "x-rapidapi-host: movie-database-imdb-alternative.p.rapidapi.com",
            "x-rapidapi-key: 04c4a86a8cmshe43b6945891ef10p1e6312jsn77f37294123b"
    })
    @GET("/?r=json")
    Call<Movie> getMovie(
            @Query("i") String movieId
    );

}
