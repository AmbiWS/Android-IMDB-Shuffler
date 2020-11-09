package com.ambiwsstudio.movie_shuffler.modules;

import android.app.Application;
import android.content.Context;

import com.ambiwsstudio.movie_shuffler.interfaces.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    Application application;

    public ApplicationModule(Application application) {

        this.application = application;

    }

    @Provides
    @Singleton
    @ApplicationContext
    Context provideContext() {

        return application;

    }

    @Provides
    @Singleton
    Application provideApplication() {

        return application;

    }

}
