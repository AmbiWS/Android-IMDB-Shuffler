package com.ambiwsstudio.movie_shuffler.viewmodel;

import android.view.View;

import com.ambiwsstudio.movie_shuffler.model.Movie;
import com.ambiwsstudio.movie_shuffler.model.MovieLoader;
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
    private MutableLiveData<Movie> movieMutableLiveData;

    public MutableLiveData<Movie> getMovie() {

        if (movieMutableLiveData == null)
            movieMutableLiveData = new MutableLiveData<>();

        return movieMutableLiveData;

    }

    public MutableLiveData<String> getImdbProceedAccess() {

        if (imdbProceedAccess == null)
            imdbProceedAccess = new MutableLiveData<>();

        return imdbProceedAccess;

    }

    public void onClick(View view) {

        if (movieMutableLiveData.getValue() != null)
            imdbProceedAccess.setValue(movieMutableLiveData.getValue().getImdbID());
        else imdbProceedAccess.setValue("N/A");

    }

    public void allowSideScrolling() {



    }

    public MovieViewModel() {

        MovieLoader.getInstance().isNeedViewUpdate(true);
        MovieLoader.getInstance().loadMovie(this, 1);

    }

}
