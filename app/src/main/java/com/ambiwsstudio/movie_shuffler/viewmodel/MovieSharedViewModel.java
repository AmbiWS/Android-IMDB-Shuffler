package com.ambiwsstudio.movie_shuffler.viewmodel;

import com.ambiwsstudio.movie_shuffler.model.Movie;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MovieSharedViewModel extends ViewModel {

    private final MutableLiveData<Boolean> isPageLoaded = new MutableLiveData<>();
    private final MutableLiveData<String> isFragmentLoaded = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isPageScrolled = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isPageReadyForScroll = new MutableLiveData<>();
    private final MutableLiveData<String> currentFragmentInView = new MutableLiveData<>();
    private final MutableLiveData<Movie> currentMovie = new MutableLiveData<>();

    public MutableLiveData<Movie> getCurrentMovie() {

        return currentMovie;

    }

    public MutableLiveData<String> getIsFragmentLoaded() {

        return isFragmentLoaded;

    }

    public MutableLiveData<String> getCurrentFragmentInView() {

        return currentFragmentInView;

    }

    public MutableLiveData<Boolean> getIsPageReadyForScroll() {

        return isPageReadyForScroll;

    }

    public MutableLiveData<Boolean> getIsPageLoaded() {

        return isPageLoaded;

    }

    public MutableLiveData<Boolean> getIsPageScrolled() {

        return isPageScrolled;

    }

    public void setCurrentMovie(Movie movie) {

        currentMovie.setValue(movie);

    }

    public void setIsFragmentLoaded(String string) {

        isFragmentLoaded.setValue(string);

    }

    public void setCurrentFragmentInView(String string) {

        currentFragmentInView.setValue(string);

    }

    public void setIsPageReadyForScroll(Boolean bool) {

        isPageReadyForScroll.setValue(bool);

    }

    public void setIsPageLoaded(Boolean bool) {

        isPageLoaded.setValue(bool);

    }

    public void setIsPageScrolled(Boolean bool) {

        isPageScrolled.setValue(bool);

    }

}
