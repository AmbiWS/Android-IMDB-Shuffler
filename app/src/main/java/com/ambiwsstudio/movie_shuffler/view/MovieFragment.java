package com.ambiwsstudio.movie_shuffler.view;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ambiwsstudio.movie_shuffler.R;
import com.ambiwsstudio.movie_shuffler.databinding.FragmentMovieBinding;
import com.ambiwsstudio.movie_shuffler.model.Movie;
import com.ambiwsstudio.movie_shuffler.viewmodel.MovieCollectionViewModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    public static final String ARG_POSTER_URL = "poster";
    public static final String ARG_TITLE = "title";
    public static final String ARG_YEAR = "year";
    public static final String ARG_RUNTIME = "runtime";
    public static final String ARG_GENRE = "genre";
    public static final String ARG_DIRECTOR = "director";
    public static final String ARG_ACTORS = "actors";
    public static final String ARG_PLOT = "plot";
    public static final String ARG_SCORE = "score";
    public static final String ARG_TAG = "tag";

    private MovieCollectionViewModel movieCollectionViewModel;
    FragmentMovieBinding binding;
    ScrollView scrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_movie, container, false);

        View view = binding.getRoot();
        binding.setMovieCollectionViewModel(movieCollectionViewModel);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Bundle args = getArguments();
        scrollView = (ScrollView) view.getRootView();

        movieCollectionViewModel = ViewModelProviders.of(this).get(args.getString(ARG_TAG), MovieCollectionViewModel.class);
        movieCollectionViewModel.getMovie().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {

                if (!movie.getPoster().equals("N/A")) {
                    Picasso.with(MovieFragment.this.getContext())
                            .load(movie.getPoster())
                            .into(binding.imageViewPoster, new Callback() {
                                @Override
                                public void onSuccess() {
                                    int diff = scrollView.getHeight() - Resources.getSystem().getDisplayMetrics().heightPixels;

                                    if (diff > 0)
                                        scrollView.smoothScrollTo(0, diff);
                                }

                                @Override
                                public void onError() {



                                }
                            });
                } else {

                    binding.imageViewPoster.setImageResource(R.drawable.postermissing);

                    int diff = scrollView.getHeight() - Resources.getSystem().getDisplayMetrics().heightPixels;
                    System.out.println(binding.textViewTitle.getY());

                    if (diff > 0)
                        scrollView.smoothScrollTo(0, diff);

                }

                if (!movie.getTitle().equals("N/A"))
                    binding.textViewTitle.setText(movie.getTitle());
                else binding.textViewTitle.setVisibility(View.GONE);

                if (!movie.getYear().equals("N/A"))
                    binding.textViewYear.setText(String.format("Year: %s", movie.getYear()));
                else binding.textViewYear.setVisibility(View.GONE);

                if (!movie.getRuntime().equals("N/A"))
                    binding.textViewRuntime.setText(String.format("Runtime: %s", movie.getRuntime()));
                else binding.textViewRuntime.setVisibility(View.GONE);

                if (!movie.getActors().equals("N/A"))
                    binding.textViewActors.setText(String.format("Actors: %s", movie.getActors()));
                else binding.textViewActors.setVisibility(View.GONE);

                if (!movie.getGenre().equals("N/A"))
                    binding.textViewGenre.setText(String.format("Genre: %s", movie.getGenre()));
                else binding.textViewGenre.setVisibility(View.GONE);

                if (!movie.getPlot().equals("N/A"))
                    binding.textViewPlot.setText(String.format("Plot: %s", movie.getPlot()));
                else binding.textViewPlot.setVisibility(View.GONE);

                if (!movie.getDirector().equals("N/A"))
                    binding.textViewDirector.setText(String.format("Director: %s", movie.getDirector()));
                else binding.textViewDirector.setVisibility(View.GONE);

                if (!movie.getMetascore().equals("N/A"))
                    binding.textViewScore.setText(String.format("Metascore: %s", movie.getMetascore()));
                else binding.textViewScore.setVisibility(View.GONE);

            }
        });



    }
}
