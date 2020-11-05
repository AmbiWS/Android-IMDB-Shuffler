package com.ambiwsstudio.movie_shuffler.viewmodel;

import android.app.Application;

import com.ambiwsstudio.movie_shuffler.model.Movie;
import com.ambiwsstudio.movie_shuffler.repository.MovieRepositoryDB;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MovieListViewModel extends AndroidViewModel {

    private final LiveData<List<Movie>> observableMovies;

    public MovieListViewModel(@NonNull Application application) {

        super(application);
        MovieRepositoryDB repository = new MovieRepositoryDB(application);
        observableMovies = repository.getObservableMoviesDB();

    }

    public LiveData<List<Movie>> getObservableMovies() {

        return observableMovies;

    }

}
