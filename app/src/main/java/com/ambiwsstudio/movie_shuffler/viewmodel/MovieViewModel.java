package com.ambiwsstudio.movie_shuffler.viewmodel;

import android.util.Log;
import android.view.View;

import com.ambiwsstudio.movie_shuffler.model.Movie;
import com.ambiwsstudio.movie_shuffler.repository.MovieRepositoryAPI;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MovieViewModel extends ViewModel {

    public MutableLiveData<String> poster = new MutableLiveData<>();
    public MutableLiveData<String> title = new MutableLiveData<>();
    public MutableLiveData<String> year = new MutableLiveData<>();
    public MutableLiveData<String> runtime = new MutableLiveData<>();
    public MutableLiveData<String> genre = new MutableLiveData<>();
    public MutableLiveData<String> director = new MutableLiveData<>();
    public MutableLiveData<String> actors = new MutableLiveData<>();
    public MutableLiveData<String> plot = new MutableLiveData<>();
    public MutableLiveData<String> imdbRating = new MutableLiveData<>();
    private MutableLiveData<String> imdbProceedAccess;
    private MutableLiveData<String> isErrorOccurred;
    private final MutableLiveData<Movie> movieMutableLiveData;

    public MutableLiveData<Movie> getMovie() {

        return movieMutableLiveData;

    }

    public MutableLiveData<String> getErrorOccurred() {

        if (isErrorOccurred == null)
            isErrorOccurred = new MutableLiveData<>();

        return isErrorOccurred;

    }

    public MutableLiveData<String> getImdbProceedAccess() {

        if (imdbProceedAccess == null)
            imdbProceedAccess = new MutableLiveData<>();

        return imdbProceedAccess;

    }

    public void onClick(View view) {

        Log.i("MovieViewModel", "Click event caught: " + view.toString());

        if (movieMutableLiveData.getValue() != null)
            imdbProceedAccess.setValue(movieMutableLiveData.getValue().getImdbID());
        else imdbProceedAccess.setValue("N/A");

    }

    public MovieViewModel() {

        movieMutableLiveData = new MutableLiveData<>();

        new Thread() {

            public void run() {

                movieMutableLiveData.postValue(MovieRepositoryAPI.getInstance().getFirstMovieInQueue());

            }

        }.start();

    }

}
