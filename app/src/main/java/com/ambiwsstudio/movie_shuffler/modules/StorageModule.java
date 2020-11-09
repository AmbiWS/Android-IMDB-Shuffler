package com.ambiwsstudio.movie_shuffler.modules;

import android.app.Application;

import com.ambiwsstudio.movie_shuffler.interfaces.ApplicationContext;
import com.ambiwsstudio.movie_shuffler.interfaces.MovieDao;
import com.ambiwsstudio.movie_shuffler.repository.MovieRepositoryDB;
import com.ambiwsstudio.movie_shuffler.storage.AppDatabase;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {

    AppDatabase appDatabase;

    public StorageModule (@ApplicationContext Application application) {

        appDatabase = Room.databaseBuilder(application,
                AppDatabase.class, "aws-itvs-db")
                .fallbackToDestructiveMigration()
                .build();

    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase() {

        return appDatabase;

    }

    @Provides
    @Singleton
    MovieDao provideMovieDao(AppDatabase appDatabase) {

        return appDatabase.movieDao();

    }

    @Provides
    @Singleton
    MovieRepositoryDB provideMovieRepositoryDB(MovieDao dao) {

        return new MovieRepositoryDB(dao);

    }


}
