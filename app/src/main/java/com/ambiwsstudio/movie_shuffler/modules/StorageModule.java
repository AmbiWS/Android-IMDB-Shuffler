package com.ambiwsstudio.movie_shuffler.modules;

import android.app.Application;
import android.content.Context;
import com.ambiwsstudio.movie_shuffler.interfaces.MovieDao;
import com.ambiwsstudio.movie_shuffler.storage.AppDatabase;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {

    AppDatabase appDatabase;

    StorageModule (Application application) {

        appDatabase = Room.databaseBuilder(application,
                AppDatabase.class, "aws-itvs-db")
                .fallbackToDestructiveMigration()
                .build();

    }

    @Provides
    @Singleton
    AppDatabase providesAppDatabase() {

        return appDatabase;

    }

    @Provides
    @Singleton
    MovieDao providesMovieDao(AppDatabase appDatabase) {

        return appDatabase.movieDao();

    }



}
