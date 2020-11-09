package com.ambiwsstudio.movie_shuffler.application;

import android.app.Application;
import com.ambiwsstudio.movie_shuffler.BuildConfig;
import com.ambiwsstudio.movie_shuffler.component.DaggerMovieComponent;
import com.ambiwsstudio.movie_shuffler.component.MovieComponent;
import timber.log.Timber;

public class MovieShufflerApplication extends Application {

    private static MovieComponent component;

    @Override
    public void onCreate() {

        super.onCreate();
        component = DaggerMovieComponent.create();

        if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());

    }

    public static MovieComponent getComponent() {

        return component;

    }
}
