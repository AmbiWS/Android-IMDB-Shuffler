package com.ambiwsstudio.aliexpress_shuffler.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Toast;

import com.ambiwsstudio.aliexpress_shuffler.R;
import com.ambiwsstudio.aliexpress_shuffler.databinding.ActivityMainBinding;
import com.ambiwsstudio.aliexpress_shuffler.model.User;
import com.ambiwsstudio.aliexpress_shuffler.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        activityMainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        activityMainBinding.setLifecycleOwner(this);
        activityMainBinding.setUserViewModel(userViewModel);

        userViewModel.getStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                if (!aBoolean)
                    Toast.makeText(getApplicationContext(), "Invalid Format!", Toast.LENGTH_SHORT).show();
                else Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();

            }
        });

        userViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {

                if (!user.isAdult()) {

                    activityMainBinding.textView2.setText("Come back later.");

                } else {

                    activityMainBinding.textView2.setText(String.valueOf(user.getAge()));

                }

            }
        });

    }
}
