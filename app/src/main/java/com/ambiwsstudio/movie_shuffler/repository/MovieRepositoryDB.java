package com.ambiwsstudio.movie_shuffler.repository;

import com.ambiwsstudio.movie_shuffler.interfaces.MovieDao;
import com.ambiwsstudio.movie_shuffler.model.Movie;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;

public class MovieRepositoryDB {

    private final MovieDao dao;
    private final LiveData<List<Movie>> observableMoviesDB;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    @Inject
    public MovieRepositoryDB(MovieDao dao) {

        this.dao = dao;
        observableMoviesDB = dao.getAll();

    }

    public LiveData<List<Movie>> getObservableMoviesDB() {

        return observableMoviesDB;

    }

    public void insertMovie(final Movie movie) {

        databaseWriteExecutor.execute(() -> dao.insertMovie(movie));

    }

    public void deleteMovieById(String id) {

        databaseWriteExecutor.execute(() -> dao.deleteMovieById(id));

    }

}
