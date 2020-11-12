package com.ambiwsstudio.movie_shuffler.test;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import timber.log.Timber;

public class AgeActivityViewModel extends ViewModel {

    public MutableLiveData<String> age = new MutableLiveData<>();
    public MutableLiveData<String> ageCheckerClick = new MutableLiveData<>();

    public void onClick(View view) {

        Timber.d("AgeActivityViewModel click: %s", view.toString());
        ageCheckerClick.setValue(age.getValue());

    }

}
