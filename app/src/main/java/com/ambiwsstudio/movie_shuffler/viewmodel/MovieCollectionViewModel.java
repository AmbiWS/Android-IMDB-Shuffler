package com.ambiwsstudio.movie_shuffler.viewmodel;

import com.ambiwsstudio.movie_shuffler.commons.Commons;
import com.ambiwsstudio.movie_shuffler.model.Movie;
import com.ambiwsstudio.movie_shuffler.service.MovieService;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieCollectionViewModel extends ViewModel {

    public MutableLiveData<String> poster = new MutableLiveData<>();
    public MutableLiveData<String> title = new MutableLiveData<>();
    public MutableLiveData<String> year = new MutableLiveData<>();
    public MutableLiveData<String> runtime = new MutableLiveData<>();
    public MutableLiveData<String> genre = new MutableLiveData<>();
    public MutableLiveData<String> director = new MutableLiveData<>();
    public MutableLiveData<String> actors = new MutableLiveData<>();
    public MutableLiveData<String> plot = new MutableLiveData<>();
    public MutableLiveData<String> metascore = new MutableLiveData<>();
    private MutableLiveData<Movie> movieMutableLiveData;

    public MutableLiveData<Movie> getMovie() {

        if (movieMutableLiveData == null)
            movieMutableLiveData = new MutableLiveData<>();

        return movieMutableLiveData;

    }

    private void loadMovie(final int loadCount) {

        if (loadCount > 10) {

            Movie movie = new Movie();
            movie.setTitle("Sorry, service unavailable right now.");
            movieMutableLiveData.setValue(movie);
            return;

        }

        MovieService.getInstance()
                .getMovieAPI()
                .getMovie(Commons.randomizeMovieId())
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {

                        System.out.println(loadCount);
                        if (response.body().getResponse().equals("False")) {

                            loadMovie(loadCount + 1);

                        } else {

                            Movie movie = response.body();
                            movieMutableLiveData.setValue(movie);

                        }

                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {

                        t.printStackTrace();

                    }
                });

    }

    public MovieCollectionViewModel() {

       loadMovie(1);

    }

}
