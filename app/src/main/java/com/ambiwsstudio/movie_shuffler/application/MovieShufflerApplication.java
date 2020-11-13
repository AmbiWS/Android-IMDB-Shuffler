package com.ambiwsstudio.movie_shuffler.application;

import android.app.Application;
import com.ambiwsstudio.movie_shuffler.component.DaggerMovieComponent;
import com.ambiwsstudio.movie_shuffler.component.MovieComponent;
import com.ambiwsstudio.movie_shuffler.modules.ApplicationModule;
import com.ambiwsstudio.movie_shuffler.modules.StorageModule;

public class MovieShufflerApplication extends Application {

    protected MovieComponent component;

    @Override
    public void onCreate() {

        super.onCreate();

        component = DaggerMovieComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .storageModule(new StorageModule(this))
                .build();

        component.injectMovieShufflerApplication(this);

        /*if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());

        Timber.d("LeakCanary config: %s", LeakCanary.getConfig());*/

    }

    public MovieComponent getComponent() {

        return component;

    }
}
