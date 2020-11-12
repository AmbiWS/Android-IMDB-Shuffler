package com.ambiwsstudio.movie_shuffler.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.ambiwsstudio.movie_shuffler.R;
import com.ambiwsstudio.movie_shuffler.databinding.ActivityAgeBinding;

public class AgeActivity extends AppCompatActivity {

    AgeActivityViewModel viewModel;
    ActivityAgeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(AgeActivityViewModel.class);
        binding = DataBindingUtil.setContentView(AgeActivity.this, R.layout.activity_age);
        binding.setLifecycleOwner(this);
        binding.setAgeActivityViewModel(viewModel);

        viewModel.ageCheckerClick.observe(this, s -> {

            String message;

            if (s != null && !s.equals("") && s.matches("\\d+")) {

                message = s + " y.o.";

            } else {

                message = "Please, enter your age";

            }

            Toast.makeText(AgeActivity.this, message, Toast.LENGTH_SHORT).show();

        });

    }

}