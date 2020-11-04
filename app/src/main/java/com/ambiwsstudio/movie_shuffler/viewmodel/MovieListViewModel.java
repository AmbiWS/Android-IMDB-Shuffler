package com.ambiwsstudio.movie_shuffler.viewmodel;

import android.app.Application;

import com.ambiwsstudio.movie_shuffler.model.Movie;
import com.ambiwsstudio.movie_shuffler.repository.MovieRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MovieListViewModel extends AndroidViewModel {

    private MovieRepository repository;
    private final LiveData<List<Movie>> observableMovies;

    public MovieListViewModel(@NonNull Application application) {

        super(application);
        repository = new MovieRepository(application);
        observableMovies = repository.getObservableMovies();

    }

    public LiveData<List<Movie>> getObservableMovies() {

        return observableMovies;

    }

    public void insertMovie(Movie movie) {

        repository.insertMovie(movie);

    }

}
