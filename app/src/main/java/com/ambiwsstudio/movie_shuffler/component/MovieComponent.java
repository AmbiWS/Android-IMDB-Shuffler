package com.ambiwsstudio.movie_shuffler.component;

import android.app.Application;
import android.content.Context;

import com.ambiwsstudio.movie_shuffler.application.MovieShufflerApplication;
import com.ambiwsstudio.movie_shuffler.interfaces.ApplicationContext;
import com.ambiwsstudio.movie_shuffler.modules.ApplicationModule;
import com.ambiwsstudio.movie_shuffler.modules.ServiceModule;
import com.ambiwsstudio.movie_shuffler.modules.StorageModule;
import com.ambiwsstudio.movie_shuffler.view.MovieCollectionFragment;
import com.ambiwsstudio.movie_shuffler.view.MovieFragment;
import com.ambiwsstudio.movie_shuffler.view.MovieListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ServiceModule.class, StorageModule.class})
public interface MovieComponent {

    void injectMovieShufflerApplication(MovieShufflerApplication movieShufflerApplication);
    void injectMovieListActivity(MovieListActivity movieListActivity);
    void injectMovieFragment(MovieFragment movieFragment);
    void injectMovieCollectionFragment(MovieCollectionFragment movieCollectionFragment);

    @ApplicationContext
    Context getContext();
    Application getApplication();

}
