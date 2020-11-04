package com.ambiwsstudio.movie_shuffler.repository;

import com.ambiwsstudio.movie_shuffler.model.Movie;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Movie.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

}
