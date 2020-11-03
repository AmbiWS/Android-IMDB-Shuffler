package com.ambiwsstudio.movie_shuffler.model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.ambiwsstudio.movie_shuffler.commons.Commons;
import com.ambiwsstudio.movie_shuffler.service.MovieService;
import com.ambiwsstudio.movie_shuffler.viewmodel.MovieViewModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayDeque;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieLoader {

    private static MovieLoader instance;
    private MovieViewModel movieViewModel;
    private int loaderCounter = 0;
    private int requestCounter = 0;
    private boolean isNeedViewUpdate = false;
    private Target target;
    private ArrayDeque<Movie> moviesList = new ArrayDeque<>();

    public static MovieLoader getInstance() {

        if (instance == null)
            instance = new MovieLoader();

        return instance;

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
                                    || response.body().getActors().equals("N/A")*/
                                    /*|| response.body().getPoster().equals("N/A")*/) {

                                loaderCounter++;
                                sendRequest();

                            } else {

                                loaderCounter = 1;
                                final Movie movie = response.body();

                                if (!movie.getPoster().equals("N/A")) {

                                    target = new Target() {
                                        @Override
                                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                                            movie.setImage(bitmap);

                                            moviesList.addLast(movie);

                                            if (isNeedViewUpdate) {

                                                movieViewModel.getMovie().postValue(moviesList.getFirst());
                                                moviesList.removeFirst();
                                                isNeedViewUpdate = false;

                                            }

                                            sendRequest();

                                        }

                                        @Override
                                        public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                                        }

                                        @Override
                                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                                        }
                                    };

                                    Picasso.get().load(movie.getPoster()).into(target);

                                } else {

                                    // TODO DUPLICATE
                                    movie.setImage(null);
                                    moviesList.addLast(movie);

                                    if (isNeedViewUpdate) {

                                        movieViewModel.getMovie().postValue(moviesList.getFirst());
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
                            movieViewModel.getErrorOccurred().postValue("Error");
                            t.printStackTrace();

                        }
                    });

        }

    }

    synchronized public void loadMovie(MovieViewModel movieViewModel, int loaderCounter) {

        this.movieViewModel = movieViewModel;
        this.loaderCounter = loaderCounter;

        if (moviesList.size() > 0) {

            movieViewModel.getMovie().postValue(moviesList.getFirst());
            moviesList.removeFirst();
            isNeedViewUpdate = false;

        }

        sendRequest();

    }

}
