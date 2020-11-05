package com.ambiwsstudio.movie_shuffler.repository;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.ambiwsstudio.movie_shuffler.commons.Commons;
import com.ambiwsstudio.movie_shuffler.model.Movie;
import com.ambiwsstudio.movie_shuffler.service.MovieAPI;
import com.ambiwsstudio.movie_shuffler.service.MovieService;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayDeque;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepositoryAPI {

    private final MovieAPI api;
    private final ArrayDeque<Movie> moviesBuffer;
    private int requestOverallCounter = 0;
    private int requestCurrentCounter = 0;
    private ServiceStatus status;
    private Target target;
    private static MovieRepositoryAPI instance;

    public static MovieRepositoryAPI getInstance() {

        if (instance == null)
            instance = new MovieRepositoryAPI();

        return instance;

    }

    private MovieRepositoryAPI() {

        api = MovieService.getInstance().getMovieAPI();
        moviesBuffer = new ArrayDeque<>();
        status = ServiceStatus.RUNNING;

        new Thread() {

            public void run() {

                fillMoviesBuffer();

            }

        }.start();

    }

    private void pushError() {

        System.out.println("ERROR PUSHED");
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
            System.out.println("a:" + requestOverallCounter + "/ c:" + requestCurrentCounter);

            api.getMovie(Commons.randomizeMovieId())
                    .enqueue(new Callback<Movie>() {
                        @Override
                        public void onResponse(Call<Movie> call, Response<Movie> response) {

                            System.out.println("RETROFIT RESPONSE");
                            if (response.body().getResponse().equals("False")
                                    /*|| response.body().getTitle().contains("Episode")
                                    || response.body().getGenre().equals("N/A")
                                    || response.body().getYear().equals("N/A")
                                    || response.body().getActors().equals("N/A")*/
                                /*|| response.body().getPoster().equals("N/A")*/) {

                                System.out.println("ON RESPONSE FALSE");
                                fillMoviesBuffer();

                            } else {

                                System.out.println("ON RESPONSE SUCCESS");
                                requestCurrentCounter = 0;
                                final Movie movie = response.body();

                                if (!movie.getPoster().equals("N/A")) {

                                    System.out.println("ON POSTER EXISTS");
                                    target = new Target() {
                                        @Override
                                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                                            System.out.println("ON BITMAP SUCCESS");
                                            setupMovie(movie, bitmap);
                                            fillMoviesBuffer();

                                        }

                                        @Override
                                        public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                                            System.out.println("ON BITMAP FAIL SUCCESS");
                                            pushError();

                                        }

                                        @Override
                                        public void onPrepareLoad(Drawable placeHolderDrawable) {
                                        }
                                    };

                                    Picasso.get().load(movie.getPoster()).into(target);

                                } else {

                                    System.out.println("ON POSTER NULL");
                                    setupMovie(movie, null);
                                    fillMoviesBuffer();

                                }

                            }

                        }

                        @Override
                        public void onFailure(Call<Movie> call, Throwable t) {

                            System.out.println("RETROFIT FAIL");
                            pushError();

                        }
                    });

        }

    }

    private void setupMovie(Movie movie, Bitmap image) {

        movie.setImage(image);
        moviesBuffer.addLast(movie);

        synchronized (this) {

            notifyAll();

        }

        System.out.println("ON MOVIE ADDED <<<<<<<<<<");
        System.out.println("MOVIES SIZE: " + moviesBuffer.size());

    }

    public Movie getFirstMovieInQueue() {

        if (moviesBuffer.size() == 0) {

            try {

                System.out.println("AWAITION");

                synchronized (this) {

                    wait();

                }

            } catch (InterruptedException e) {

                pushError();
                e.printStackTrace();

            }

        }

        System.out.println("SIZE" + moviesBuffer.size());
        return refillMovieAsync();

    }

    private Movie refillMovieAsync() {

        Movie movie = moviesBuffer.getFirst();

        new Thread() {

            public void run() {

                moviesBuffer.removeFirst();
                fillMoviesBuffer();

            }

        }.start();

        System.out.println("RETURNING MOVIE TO VM <<<<<<<<<<");
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
