package com.ambiwsstudio.movie_shuffler.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MovieSharedViewModel extends ViewModel {

    private final MutableLiveData<Boolean> isPageLoaded = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isPageScrolled = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isPageReadyForScroll = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isMovieToWatch = new MutableLiveData<>();
    private final MutableLiveData<String> currentFragmentInView = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsMovieToWatch() {

        return isMovieToWatch;

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

    public void setIsMovieToWatch(Boolean bool) {

        isMovieToWatch.setValue(bool);

    }

    public void setCurrentFragmentInView(String string) {

        currentFragmentInView.setValue(string);

    }

    public void setIsPageReadyForScroll(Boolean bool) {

        System.out.println("SET IS PAGE READY FOR SCROLL");
        isPageReadyForScroll.setValue(bool);

    }

    public void setIsPageLoaded(Boolean bool) {

        isPageLoaded.setValue(bool);

    }

    public void setIsPageScrolled(Boolean bool) {

        isPageScrolled.setValue(bool);

    }

}
