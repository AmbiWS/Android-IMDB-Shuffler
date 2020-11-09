package com.ambiwsstudio.movie_shuffler.viewmodel;

import android.app.Application;

import com.ambiwsstudio.movie_shuffler.component.DaggerMovieComponent;
import com.ambiwsstudio.movie_shuffler.model.Movie;
import com.ambiwsstudio.movie_shuffler.modules.StorageModule;
import com.ambiwsstudio.movie_shuffler.repository.MovieRepositoryDB;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MovieListViewModel extends ViewModel {

    MovieRepositoryDB repository;

    private final LiveData<List<Movie>> observableMovies;

    @Inject
    public MovieListViewModel(MovieRepositoryDB repository) {

        this.repository = repository;
        observableMovies = repository.getObservableMoviesDB();

    }

    public LiveData<List<Movie>> getObservableMovies() {

        return observableMovies;

    }

}
