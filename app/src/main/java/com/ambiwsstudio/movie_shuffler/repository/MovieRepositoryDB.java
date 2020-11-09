package com.ambiwsstudio.movie_shuffler.repository;

import android.app.Application;

import com.ambiwsstudio.movie_shuffler.interfaces.MovieDao;
import com.ambiwsstudio.movie_shuffler.model.Movie;
import com.ambiwsstudio.movie_shuffler.storage.AppDatabase;

import java.util.List;
import androidx.lifecycle.LiveData;

public class MovieRepositoryDB {

    private final MovieDao dao;
    private final LiveData<List<Movie>> observableMoviesDB;

    public MovieRepositoryDB(Application application) {

        AppDatabase database = AppDatabase.getInstance(application);
        dao = database.movieDao();
        observableMoviesDB = dao.getAll();

    }

    public LiveData<List<Movie>> getObservableMoviesDB() {

        return observableMoviesDB;

    }

    public void insertMovie(final Movie movie) {

        AppDatabase.databaseWriteExecutor.execute(() -> dao.insertMovie(movie));

    }

    public void deleteMovieById(String id) {

        AppDatabase.databaseWriteExecutor.execute(() -> dao.deleteMovieById(id));

    }

}
