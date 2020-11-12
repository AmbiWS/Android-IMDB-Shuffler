package com.ambiwsstudio.movie_shuffler.storage;

import com.ambiwsstudio.movie_shuffler.interfaces.MovieDao;
import com.ambiwsstudio.movie_shuffler.model.Movie;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import dagger.Module;

@Module
@Database(entities = {Movie.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

}
