package com.ambiwsstudio.movie_shuffler.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;
import com.ambiwsstudio.movie_shuffler.R;
import com.ambiwsstudio.movie_shuffler.model.Movie;
import com.ambiwsstudio.movie_shuffler.repository.MovieRepositoryDB;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import timber.log.Timber;

public class MovieCollectionViewModel extends ViewModel {

    MovieRepositoryDB repository;
    Application application;

    private MutableLiveData<Boolean> isMovieToWatch;
    private MutableLiveData<Boolean> isAccessedToList;

    @Inject
    public MovieCollectionViewModel(MovieRepositoryDB repository, Application application) {

        this.application = application;
        this.repository = repository;

    }

    public MutableLiveData<Boolean> getIsMovieToWatch() {

        if (isMovieToWatch == null)
            isMovieToWatch = new MutableLiveData<>();

        return isMovieToWatch;

    }

    public MutableLiveData<Boolean> getIsAccessedToList() {

        if (isAccessedToList == null)
            isAccessedToList = new MutableLiveData<>();

        return isAccessedToList;

    }

    public void onListClick(View view) {

        Timber.d("MovieCollectionVM -> " + "ListClick Caught: " + view.toString());
        isAccessedToList.setValue(true);

    }

    public void saveMovie(Boolean bool, Movie movie) {

        if (bool) {

            repository.insertMovie(movie);

        } else {

            repository.deleteMovieById(movie.getImdbIdClear());

        }

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void onSaveClick(View view) {

        Timber.d("MovieCollectionVM ->" + "SaveClick Caught: " + view.toString());
        ImageView imageView = (ImageView) view;

        isMovieToWatch.setValue(((ColorDrawable) imageView.getBackground()).getColor()
                == ((ColorDrawable) application.getResources().getDrawable(R.color.colorLightTrans)).getColor());

    }

}
