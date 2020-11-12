package com.ambiwsstudio.movie_shuffler.modules;

import com.ambiwsstudio.movie_shuffler.service.MovieAPI;
import com.ambiwsstudio.movie_shuffler.repository.MovieRepositoryAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ServiceModule {

    private static final String BASE_URL = "https://movie-database-imdb-alternative.p.rapidapi.com";

    @Provides
    @Singleton
    Retrofit provideRetrofit() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    @Provides
    @Singleton
    MovieAPI provideMovieAPI(Retrofit retrofit) {

        return retrofit.create(MovieAPI.class);

    }

    @Provides
    @Singleton
    MovieRepositoryAPI provideMovieRepositoryAPI(MovieAPI api) {

        return new MovieRepositoryAPI(api);

    }

}
