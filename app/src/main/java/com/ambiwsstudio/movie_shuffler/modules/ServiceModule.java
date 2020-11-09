package com.ambiwsstudio.movie_shuffler.modules;

import com.ambiwsstudio.movie_shuffler.interfaces.MovieAPI;
import com.ambiwsstudio.movie_shuffler.service.MovieService;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {

    @Provides
    MovieAPI provideMovieAPI() {

        return MovieService.getInstance().getMovieAPI();

    }

}
