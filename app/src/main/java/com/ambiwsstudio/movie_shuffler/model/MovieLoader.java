package com.ambiwsstudio.movie_shuffler.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import com.ambiwsstudio.movie_shuffler.R;
import com.ambiwsstudio.movie_shuffler.commons.Commons;
import com.ambiwsstudio.movie_shuffler.service.MovieService;
import com.ambiwsstudio.movie_shuffler.view.MovieActivity;
import com.ambiwsstudio.movie_shuffler.viewmodel.MovieCollectionViewModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieLoader {

    private static MovieLoader instance;
    private MovieCollectionViewModel movieCollectionViewModel;
    private int loaderCounter = 0;
    private int requestCounter = 0;
    private boolean isNeedViewUpdate = false;
    private Movie currentMovie;
    private ArrayDeque<Movie> moviesList = new ArrayDeque<>();

    public static MovieLoader getInstance() {

        if (instance == null)
            instance = new MovieLoader();

        return instance;

    }

    public boolean isNeedViewUpdate() {

        return isNeedViewUpdate;

    }

    public void isNeedViewUpdate(boolean b) {

        isNeedViewUpdate = b;

    }

    private void sendRequest() {

        if (moviesList.size() < 3 || isNeedViewUpdate) {

            if (loaderCounter > 10) {

                // TODO
                return;

            }

            System.out.println("c:" + loaderCounter + " // a:" + ++requestCounter);

            MovieService.getInstance()
                    .getMovieAPI()
                    .getMovie(Commons.randomizeMovieId())
                    .enqueue(new Callback<Movie>() {
                        @Override
                        public void onResponse(Call<Movie> call, Response<Movie> response) {

                            if (response.body().getResponse().equals("False")
                                    /*|| response.body().getTitle().contains("Episode")
                                    || response.body().getGenre().equals("N/A")
                                    || response.body().getYear().equals("N/A")
                                    || response.body().getActors().equals("N/A")*/) {

                                loaderCounter++;
                                sendRequest();

                            } else {

                                loaderCounter = 1;
                                final Movie movie = response.body();

                                if (!movie.getPoster().equals("N/A")) {

                                    Picasso.get().load(movie.getPoster()).into(new Target() {
                                        @Override
                                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                                            movie.setImage(bitmap);

                                            moviesList.addLast(movie);

                                            if (isNeedViewUpdate) {

                                                movieCollectionViewModel.getMovie().postValue(moviesList.getFirst());
                                                moviesList.removeFirst();
                                                isNeedViewUpdate = false;

                                            }

                                            sendRequest();

                                        }

                                        @Override
                                        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                                            // TODO
                                        }


                                        @Override
                                        public void onPrepareLoad(Drawable placeHolderDrawable) {
                                        }
                                    });

                                } else {

                                    // TODO DUPLICATE
                                    movie.setImage(null);
                                    moviesList.addLast(movie);

                                    if (isNeedViewUpdate) {

                                        movieCollectionViewModel.getMovie().postValue(moviesList.getFirst());
                                        moviesList.removeFirst();
                                        isNeedViewUpdate = false;

                                    }

                                    sendRequest();

                                }

                            }

                        }

                        @Override
                        public void onFailure(Call<Movie> call, Throwable t) {

                            // TODO
                            t.printStackTrace();

                        }
                    });

        }

    }

    synchronized public void loadMovie(MovieCollectionViewModel movieCollectionViewModel, int loaderCounter) {

        this.movieCollectionViewModel = movieCollectionViewModel;
        this.loaderCounter = loaderCounter;

        if (moviesList.size() > 0) {

            movieCollectionViewModel.getMovie().postValue(moviesList.getFirst());
            moviesList.removeFirst();
            isNeedViewUpdate = false;

        }

        sendRequest();

    }

}
