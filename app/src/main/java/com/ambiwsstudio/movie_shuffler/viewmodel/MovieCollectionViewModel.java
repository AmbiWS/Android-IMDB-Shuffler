package com.ambiwsstudio.movie_shuffler.viewmodel;

import com.ambiwsstudio.movie_shuffler.model.Movie;
import com.ambiwsstudio.movie_shuffler.model.MovieLoader;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MovieCollectionViewModel extends ViewModel {

    public MutableLiveData<String> poster = new MutableLiveData<>();
    public MutableLiveData<String> title = new MutableLiveData<>();
    public MutableLiveData<String> year = new MutableLiveData<>();
    public MutableLiveData<String> runtime = new MutableLiveData<>();
    public MutableLiveData<String> genre = new MutableLiveData<>();
    public MutableLiveData<String> director = new MutableLiveData<>();
    public MutableLiveData<String> actors = new MutableLiveData<>();
    public MutableLiveData<String> plot = new MutableLiveData<>();
    public MutableLiveData<String> imdbRating = new MutableLiveData<>();
    private MutableLiveData<Movie> movieMutableLiveData;

    public MutableLiveData<Movie> getMovie() {

        if (movieMutableLiveData == null)
            movieMutableLiveData = new MutableLiveData<>();

        return movieMutableLiveData;

    }

    public MovieCollectionViewModel() {

        MovieLoader.getInstance().isNeedViewUpdate(true);
        MovieLoader.getInstance().loadMovie(this, 1);

    }

}
