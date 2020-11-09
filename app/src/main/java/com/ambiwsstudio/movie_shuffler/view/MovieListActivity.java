package com.ambiwsstudio.movie_shuffler.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import android.os.Bundle;
import com.ambiwsstudio.movie_shuffler.R;
import com.ambiwsstudio.movie_shuffler.adapter.MovieListAdapter;
import com.ambiwsstudio.movie_shuffler.application.MovieShufflerApplication;
import com.ambiwsstudio.movie_shuffler.databinding.ActivityMovieListBinding;
import com.ambiwsstudio.movie_shuffler.viewmodel.MovieListViewModel;

import javax.inject.Inject;

public class MovieListActivity extends AppCompatActivity {

    @Inject
    MovieListViewModel movieListViewModel;

    ActivityMovieListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ((MovieShufflerApplication) getApplication()).getComponent().injectMovieListActivity(this);

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
