package com.ambiwsstudio.movie_shuffler.repository;

import android.content.Context;

import com.ambiwsstudio.movie_shuffler.model.Movie;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Movie.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

    private static volatile AppDatabase instance;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppDatabase getInstance(final Context context) {

        if (instance == null) {

            synchronized (AppDatabase.class) {

                if (instance == null) {

                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "aws-itvs-db")
                            .fallbackToDestructiveMigration()
                            .build();

                }

            }

        }

        return instance;

    }

}
