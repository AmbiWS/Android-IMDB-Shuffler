package com.ambiwsstudio.movie_shuffler.repository;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.ambiwsstudio.movie_shuffler.commons.Commons;
import com.ambiwsstudio.movie_shuffler.model.Movie;
import com.ambiwsstudio.movie_shuffler.service.MovieAPI;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import java.util.ArrayDeque;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MovieRepositoryAPI {

    public MovieAPI api;
    private final ArrayDeque<Movie> moviesBuffer;
    private int requestOverallCounter = 0;
    private int requestCurrentCounter = 0;
    private ServiceStatus status;
    private Target target;

    @Inject
    public MovieRepositoryAPI(MovieAPI api) {

        this.api = api;
        moviesBuffer = new ArrayDeque<>();
        status = ServiceStatus.RUNNING;

        new Thread() {

            public void run() {

                fillMoviesBuffer();

            }

        }.start();

    }

    private void pushError() {

        status = ServiceStatus.ERROR;
        moviesBuffer.clear();
        moviesBuffer.addFirst(new Movie());

    }

    private void loadMovie() {

        if (requestCurrentCounter >= 10) {

            pushError();

        }

        if (status == ServiceStatus.RUNNING) {

            requestOverallCounter++;
            requestCurrentCounter++;
            Timber.d("MovieRepositoryAPI ->" + "Retrofit request sending. A:" + requestOverallCounter + "/ C:" + requestCurrentCounter);

            api.getMovie(Commons.randomizeMovieId())
                    .enqueue(new Callback<Movie>() {
                        @Override
                        public void onResponse(Call<Movie> call, Response<Movie> response) {

                            if (response.body().getResponse().equals("False")
                                    || response.body().getTitle().contains("Episode")
                                    || response.body().getGenre().equals("N/A")
                                    || response.body().getYear().equals("N/A")
                                    || response.body().getActors().equals("N/A")) {

                                fillMoviesBuffer();

                            } else {

                                requestCurrentCounter = 0;
                                final Movie movie = response.body();

                                if (!movie.getPoster().equals("N/A")) {

                                    target = new Target() {
                                        @Override
                                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                                            setupMovie(movie, bitmap);
                                            fillMoviesBuffer();

                                        }

                                        @Override
                                        public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                                            pushError();

                                        }

                                        @Override
                                        public void onPrepareLoad(Drawable placeHolderDrawable) {
                                        }

                                    };

                                    Picasso.get().load(movie.getPoster()).into(target);

                                } else {

                                    setupMovie(movie, null);
                                    fillMoviesBuffer();

                                }

                            }

                        }

                        @Override
                        public void onFailure(Call<Movie> call, Throwable t) {

                            pushError();

                        }
                    });

        }

    }

    private void setupMovie(Movie movie, Bitmap image) {

        movie.setImage(image);
        moviesBuffer.addLast(movie);

        synchronized (this) {

            notify();

        }

    }

    public Movie getFirstMovieInQueue() {

        if (moviesBuffer.size() == 0) {

            try {

                synchronized (this) {

                    wait();

                }

            } catch (InterruptedException e) {

                pushError();
                e.printStackTrace();

            }

        }

        return refillMovieAsync();

    }

    private Movie refillMovieAsync() {

        Movie movie = moviesBuffer.getFirst();
        moviesBuffer.removeFirst();

        new Thread() {

            public void run() {

                synchronized (this) {

                    fillMoviesBuffer();

                }

            }

        }.start();

        return movie;

    }

    private void fillMoviesBuffer() {

        if (moviesBuffer.size() < 3) {

            loadMovie();

        }

    }

    private enum ServiceStatus {

        RUNNING, ERROR

    }

}
