package com.ambiwsstudio.movie_shuffler.modules;

import com.ambiwsstudio.movie_shuffler.interfaces.MovieAPI;
import com.ambiwsstudio.movie_shuffler.repository.MovieRepositoryAPI;
import com.ambiwsstudio.movie_shuffler.service.MovieService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {

    @Provides
    @Singleton
    MovieAPI provideMovieAPI() {

        return MovieService.getInstance().getMovieAPI();

    }

    @Provides
    @Singleton
    MovieRepositoryAPI provideMovieRepositoryAPI(MovieAPI api) {

        return new MovieRepositoryAPI(api);

    }

}
