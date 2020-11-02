package com.ambiwsstudio.movie_shuffler.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MovieCollectionViewModel extends ViewModel {

    private MutableLiveData<Boolean> allowToScroll;

    public MutableLiveData<Boolean> getAllowToScroll() {

        if (allowToScroll == null)
            allowToScroll = new MutableLiveData<>();

        return allowToScroll;

    }

    public void allowToScroll() {

        System.out.println("PROC");
        allowToScroll.setValue(true);

    }

}
