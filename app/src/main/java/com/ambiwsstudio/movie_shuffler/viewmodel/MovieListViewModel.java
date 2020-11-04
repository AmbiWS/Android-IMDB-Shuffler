package com.ambiwsstudio.movie_shuffler.viewmodel;

import android.app.Application;

import com.ambiwsstudio.movie_shuffler.model.Movie;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class MovieListViewModel extends AndroidViewModel {

    private MutableLiveData<List<Movie>> moviesList;

    public MovieListViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Movie>> getMoviesList() {

        if (moviesList == null)
            moviesList = new MutableLiveData<>();

        return moviesList;

    }
}
