package com.ambiwsstudio.movie_shuffler.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import android.os.CountDownTimer;
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

    public static  String ARG_TAG = "tag";

    private MovieCollectionViewModel movieCollectionViewModel;
    FragmentMovieBinding binding;
    ScrollView scrollView;
    private boolean isScrolled = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_movie, container, false);

        View view = binding.getRoot();
        binding.setMovieCollectionViewModel(movieCollectionViewModel);

        return view;
    }

    /*

        TODO: OneSideScrolling
        TODO: autoscroll down
        TODO: Future fragments preloading
        TODO: Fragment redesign (Bg behind text, More data, Link to IMDB, etc...
        TODO: FIX CLEAR FIRST PAGE

     */

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Bundle args = getArguments();

        if (args != null)
            ARG_TAG = args.getString(ARG_TAG);

        scrollView = (ScrollView) view.getRootView();
        scrollView.setSmoothScrollingEnabled(true);

        movieCollectionViewModel = ViewModelProviders.of(this).get(args.getString(ARG_TAG), MovieCollectionViewModel.class);
        movieCollectionViewModel.getMovie().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {

                if (movie.getImage() != null) {

                    binding.imageViewPoster.setImageBitmap(movie.getImage());

                } else {

                    binding.imageViewPoster.setImageResource(R.drawable.postermissing);

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

                if (!movie.getImdbRating().equals("N/A"))
                    binding.textViewScore.setText(String.format("IMDB Rating: %s", movie.getImdbRating()));
                else binding.textViewScore.setVisibility(View.GONE);

                if (!movie.getImdbID().equals("N/A"))
                    binding.imdbLink.setText(R.string.imdbText);
                else binding.imdbLink.setVisibility(View.GONE);

                System.out.println(movie.getImdbID());

                if (ARG_TAG.equals("0")) {

                    smoothScrollDown();

                }

            }
        });

    }

    void smoothScrollDown() {

        if (isScrolled)
            return;

        scrollView.post(new Runnable() {
            @Override
            public void run() {

                new CountDownTimer(1500, 20) {

                    public void onTick(long millisUntilFinished) {

                        scrollView.scrollTo(0, (int)(binding.textViewTitle.getRootView().getBottom() - millisUntilFinished));

                    }

                    public void onFinish() {

                        isScrolled = true;

                    }
                }.start();

            }
        });

    }
}
