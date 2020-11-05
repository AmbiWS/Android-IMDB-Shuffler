package com.ambiwsstudio.movie_shuffler.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MovieSharedViewModel extends ViewModel {

    private final MutableLiveData<Boolean> isPageLoaded = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isPageScrolled = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsPageLoaded() {

        return isPageLoaded;

    }

    public MutableLiveData<Boolean> getIsPageScrolled() {

        return isPageScrolled;

    }

    public void setIsPageLoaded(Boolean bool) {

        System.out.println("SET PAGE LOADED FROM VIEW");
        isPageLoaded.setValue(bool);

    }

    public void setIsPageScrolled(Boolean bool) {

        System.out.println("SET PAGE SCROLLED FROM VIEW");
        isPageScrolled.setValue(bool);

    }

}
