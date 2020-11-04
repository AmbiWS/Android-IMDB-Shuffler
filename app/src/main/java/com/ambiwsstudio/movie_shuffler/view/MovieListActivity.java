package com.ambiwsstudio.movie_shuffler.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import com.ambiwsstudio.movie_shuffler.R;
import com.ambiwsstudio.movie_shuffler.databinding.ActivityMovieListBinding;
import com.ambiwsstudio.movie_shuffler.viewmodel.MovieListViewModel;

public class MovieListActivity extends AppCompatActivity {

    ActivityMovieListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MovieListViewModel movieListViewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        binding = DataBindingUtil.setContentView(MovieListActivity.this, R.layout.activity_movie_list);
        binding.setLifecycleOwner(this);
        binding.setMovieListViewModel(movieListViewModel);

        binding.moviesToWatchRecyclerView.setOnClickListener(v -> {



        });

    }
}
