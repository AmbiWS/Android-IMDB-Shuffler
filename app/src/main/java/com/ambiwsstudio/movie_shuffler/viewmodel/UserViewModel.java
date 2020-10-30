package com.ambiwsstudio.movie_shuffler.viewmodel;

import android.view.View;

import com.ambiwsstudio.movie_shuffler.model.User;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {

    public MutableLiveData<String> age = new MutableLiveData<>();
    private MutableLiveData<Boolean> status;
    private MutableLiveData<User> userMutableLiveData;

    public MutableLiveData<User> getUser() {

        if (userMutableLiveData == null)
            userMutableLiveData = new MutableLiveData<>();

        return userMutableLiveData;

    }

    public MutableLiveData<Boolean> getStatus() {

        if (status == null)
            status = new MutableLiveData<>();

        return status;

    }

    public void onClick(View view) {

        if (age.getValue() == null || !age.getValue().matches("\\d+")) {

            status.setValue(false);

        } else {

            status.setValue(true);
            User user = new User(Integer.parseInt(age.getValue()));
            userMutableLiveData.setValue(user);

        }

    }

}
