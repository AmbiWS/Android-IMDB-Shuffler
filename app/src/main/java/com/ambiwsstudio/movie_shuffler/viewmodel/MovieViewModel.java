package com.ambiwsstudio.movie_shuffler.viewmodel;

import android.view.View;

import com.ambiwsstudio.movie_shuffler.model.Movie;
import com.ambiwsstudio.movie_shuffler.repository.MovieRepositoryAPI;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import timber.log.Timber;

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
    private final MutableLiveData<Movie> movieMutableLiveData;

    MovieRepositoryAPI movieRepositoryAPI;

    @Inject
    public MovieViewModel(MovieRepositoryAPI movieRepositoryAPI) {

        this.movieRepositoryAPI = movieRepositoryAPI;
        movieMutableLiveData = new MutableLiveData<>();

        new Thread() {

            public void run() {

                Movie movie = movieRepositoryAPI.getFirstMovieInQueue();

                if (movie.getResponse() == null) {

                    movieMutableLiveData.postValue(null);

                } else {

                    movieMutableLiveData.postValue(movie);

                }

            }

        }.start();

    }

    public MutableLiveData<Movie> getMovie() {

        return movieMutableLiveData;

    }

    public MutableLiveData<String> getImdbProceedAccess() {

        if (imdbProceedAccess == null)
            imdbProceedAccess = new MutableLiveData<>();

        return imdbProceedAccess;

    }

    public void onClick(View view) {

        Timber.d("MovieViewModel -> " + "Click event caught: " + view.toString());

        if (movieMutableLiveData.getValue() != null)
            imdbProceedAccess.setValue(movieMutableLiveData.getValue().getImdbID());
        else imdbProceedAccess.setValue("N/A");

    }

}
