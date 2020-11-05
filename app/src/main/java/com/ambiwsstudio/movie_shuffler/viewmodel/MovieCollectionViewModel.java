package com.ambiwsstudio.movie_shuffler.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.ambiwsstudio.movie_shuffler.R;
import com.ambiwsstudio.movie_shuffler.model.Movie;
import com.ambiwsstudio.movie_shuffler.repository.MovieRepositoryAPI;
import com.ambiwsstudio.movie_shuffler.repository.MovieRepositoryDB;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class MovieCollectionViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> isMovieToWatch;
    private MutableLiveData<Boolean> isAccessedToList;
    private final MovieRepositoryDB repositoryDB;

    public MovieCollectionViewModel(@NonNull Application application) {

        super(application);
        repositoryDB = new MovieRepositoryDB(application);

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

        Log.i("MovieCollectionVM", "ListClick Caught: " + view.toString());
        isAccessedToList.setValue(true);

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void onSaveClick(View view) {

        Log.i("MovieCollectionVM", "SaveClick Caught: " + view.toString());
        ImageView imageView = (ImageView) view;

        Movie lastMovie = MovieRepositoryAPI.getLastMovie();

        if (((ColorDrawable) imageView.getBackground()).getColor()
                == ((ColorDrawable) getApplication().getResources().getDrawable(R.color.colorLightTrans)).getColor()) {

            lastMovie.setImdbIdClear(lastMovie.getImdbID().substring(2));
            repositoryDB.insertMovie(lastMovie);
            isMovieToWatch.setValue(true);

        } else {

            repositoryDB.deleteMovieById(lastMovie.getImdbID().substring(2));
            isMovieToWatch.setValue(false);

        }

    }

}
