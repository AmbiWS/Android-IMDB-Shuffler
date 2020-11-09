package com.ambiwsstudio.movie_shuffler.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import com.ambiwsstudio.movie_shuffler.R;
import com.ambiwsstudio.movie_shuffler.databinding.FragmentMovieBinding;
import com.ambiwsstudio.movie_shuffler.model.Movie;
import com.ambiwsstudio.movie_shuffler.viewmodel.MovieSharedViewModel;
import com.ambiwsstudio.movie_shuffler.viewmodel.MovieViewModel;

public class MovieFragment extends Fragment {

    public static String ARG_TAG = "tag";

    private FragmentMovieBinding binding;
    private ScrollView scrollView;
    private boolean isScrolled = false;
    private MovieSharedViewModel sharedViewModel;
    private Movie currentMovie;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_movie, container, false);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Bundle args = getArguments();
        if (args != null)
            ARG_TAG = args.getString(ARG_TAG);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(MovieSharedViewModel.class);
        MovieViewModel movieViewModel = new ViewModelProvider(this).get(ARG_TAG, MovieViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setMovieViewModel(movieViewModel);
        binding.linearLayout.setVisibility(View.GONE);

        scrollView = (ScrollView) view.getRootView();
        scrollView.setSmoothScrollingEnabled(true);

        movieViewModel.getMovie().observe(getViewLifecycleOwner(), movie -> {

            movie.setImdbIdClear(movie.getImdbID().substring(2));
            currentMovie = movie;
            binding.linearLayout.setVisibility(View.VISIBLE);

            if (movie.getImage() != null)
                binding.imageViewPoster.setImageBitmap(movie.getImage());
            else binding.imageViewPoster.setImageResource(R.drawable.postermissing);

            textViewSetup(movie.getTitle(), binding.textViewTitle, "%s");
            textViewSetup(movie.getYear(), binding.textViewYear, "Year: %s");
            textViewSetup(movie.getRuntime(), binding.textViewRuntime, "Runtime: %s");
            textViewSetup(movie.getActors(), binding.textViewActors, "Actors: %s");
            textViewSetup(movie.getGenre(), binding.textViewGenre, "Genre: %s");
            textViewSetup(movie.getPlot(), binding.textViewPlot, "Plot: %s");
            textViewSetup(movie.getDirector(), binding.textViewDirector, "Director: %s");
            textViewSetup(movie.getImdbRating(), binding.textViewScore, "IMDB Rating: %s");

            if (!movie.getImdbID().equals("N/A"))
                binding.imdbLink.setText(R.string.imdbText);
            else binding.imdbLink.setVisibility(View.GONE);

            sharedViewModel.setIsFragmentLoaded(MovieFragment.this.getTag());

            if ((this.getTag() != null && this.getTag().equals("f0")) && sharedViewModel.getIsPageReadyForScroll().getValue() == null) {

                sharedViewModel.setCurrentMovie(currentMovie);
                sharedViewModel.setIsPageLoaded(true);
                smoothScrollDown();

            }

        });

        movieViewModel.getImdbProceedAccess().observe(getViewLifecycleOwner(), new Observer<String>() {

            final String IMDB_BASE_URL = "https://www.imdb.com";

            @Override
            public void onChanged(String s) {

                if (!s.equals("N/A")) {

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(IMDB_BASE_URL + "/title/" + s));
                    startActivity(browserIntent);

                } else errorHandle();

            }
        });

        movieViewModel.getErrorOccurred().observe(getViewLifecycleOwner(), s -> {

            if (s.equals("Error"))
                errorHandle();

        });

        sharedViewModel.getCurrentFragmentInView().observe(getViewLifecycleOwner(), s -> {

            if (s.equals(this.getTag())) {

                sharedViewModel.setCurrentMovie(currentMovie);
                sharedViewModel.getIsPageReadyForScroll().observe(getViewLifecycleOwner(), aBoolean -> smoothScrollDown());

            } else {

                sharedViewModel.getIsPageReadyForScroll().removeObservers(getViewLifecycleOwner());

            }

        });

    }

    private void textViewSetup(String data, TextView textView, String format) {

        if (!data.equals("N/A"))
            textView.setText(String.format(format, data));
        else textView.setVisibility(View.GONE);

    }

    private void errorHandle() {

        binding.imageViewPoster.setImageResource(R.drawable.error);
        binding.linearLayout.setVisibility(View.GONE);
        sharedViewModel.setIsPageLoaded(false);

    }

    void smoothScrollDown() {

        if (isScrolled) {

            sharedViewModel.setIsPageScrolled(true);
            return;

        }

        scrollView.post(() -> new CountDownTimer(1500, 20) {

            public void onTick(long millisUntilFinished) {

                scrollView.scrollTo(0, (int) (binding.textViewTitle.getRootView().getBottom() - millisUntilFinished));

            }

            public void onFinish() {

                isScrolled = true;
                sharedViewModel.setIsPageScrolled(true);

            }
        }.start());

    }
}
