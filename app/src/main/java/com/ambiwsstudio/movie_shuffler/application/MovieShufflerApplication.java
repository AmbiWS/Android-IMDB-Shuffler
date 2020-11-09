package com.ambiwsstudio.movie_shuffler.application;

import android.app.Application;

import com.ambiwsstudio.movie_shuffler.component.DaggerMovieComponent;
import com.ambiwsstudio.movie_shuffler.component.MovieComponent;

public class MovieShufflerApplication extends Application {

    private static MovieComponent component;

    @Override
    public void onCreate() {

        super.onCreate();
        component = DaggerMovieComponent.create();

    }

    public static MovieComponent getComponent() {

        return component;

    }
}
