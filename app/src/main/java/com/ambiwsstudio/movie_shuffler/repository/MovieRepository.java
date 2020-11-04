package com.ambiwsstudio.movie_shuffler.repository;

import android.app.Application;

import com.ambiwsstudio.movie_shuffler.model.Movie;

import java.util.List;

import androidx.lifecycle.LiveData;

public class MovieRepository {

    private MovieDao dao;
    private LiveData<List<Movie>> observableMovies;

    public MovieRepository(Application application) {

        AppDatabase database = AppDatabase.getInstance(application);
        dao = database.movieDao();
        observableMovies = dao.getAll();

    }

    public LiveData<List<Movie>> getObservableMovies() {

        return observableMovies;

    }

    public void insertMovie(final Movie movie) {

        AppDatabase.databaseWriteExecutor.execute(() -> {
            dao.insertMovie(movie);
        });

    }

    public void deleteMovieById(int id) {

        AppDatabase.databaseWriteExecutor.execute(() -> {
            dao.deleteMovieById(id);
        });

    }

}
