package com.ambiwsstudio.movie_shuffler.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;
import com.ambiwsstudio.movie_shuffler.R;
import com.ambiwsstudio.movie_shuffler.model.Movie;
import com.ambiwsstudio.movie_shuffler.repository.MovieRepositoryDB;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import timber.log.Timber;

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

        Timber.d("MovieCollectionVM -> " + "ListClick Caught: " + view.toString());
        isAccessedToList.setValue(true);

    }

    public void saveMovie(Boolean bool, Movie movie) {

        if (bool) {

            repositoryDB.insertMovie(movie);

        } else {

            repositoryDB.deleteMovieById(movie.getImdbIdClear());

        }

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void onSaveClick(View view) {

        Timber.d("MovieCollectionVM ->" + "SaveClick Caught: " + view.toString());
        ImageView imageView = (ImageView) view;

        isMovieToWatch.setValue(((ColorDrawable) imageView.getBackground()).getColor()
                == ((ColorDrawable) getApplication().getResources().getDrawable(R.color.colorLightTrans)).getColor());

    }

}
