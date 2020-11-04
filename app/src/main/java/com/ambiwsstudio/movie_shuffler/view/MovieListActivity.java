package com.ambiwsstudio.movie_shuffler.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MotionEvent;

import com.ambiwsstudio.movie_shuffler.R;
import com.ambiwsstudio.movie_shuffler.adapter.MovieListAdapter;
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

        final MovieListAdapter adapter = new MovieListAdapter(new MovieListAdapter.MovieDiff());
        binding.moviesToWatchRecyclerView.setAdapter(adapter);
        binding.moviesToWatchRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        movieListViewModel.getObservableMovies().observe(this, (movies -> {

            adapter.submitList(movies);
            adapter.notifyDataSetChanged();

        }));

    }
}
