package com.ambiwsstudio.movie_shuffler.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ambiwsstudio.movie_shuffler.R;
import com.ambiwsstudio.movie_shuffler.adapter.MovieCollectionPagerAdapter;
import com.ambiwsstudio.movie_shuffler.databinding.FragmentMovieCollectionBinding;
import com.ambiwsstudio.movie_shuffler.model.Movie;
import com.ambiwsstudio.movie_shuffler.viewmodel.MovieCollectionViewModel;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieCollectionFragment extends Fragment {

    private static MovieCollectionFragment instance;
    private MovieCollectionPagerAdapter adapter;
    private FragmentMovieCollectionBinding binding;
    private static int currentFragmentPos = 0;
    ViewPager2 viewPager2;

    static MovieCollectionFragment getInstance() {

        if (instance == null)
            instance = new MovieCollectionFragment();

        return instance;

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_movie_collection, container, false);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        MovieCollectionViewModel movieCollectionViewModel = ViewModelProviders.of(this).get(MovieCollectionViewModel.class);
        instance = MovieCollectionFragment.this;
        binding.setLifecycleOwner(this);
        binding.setMovieCollectionViewModel(movieCollectionViewModel);

        adapter = new MovieCollectionPagerAdapter(instance.getActivity());
        viewPager2 = view.findViewById(R.id.pager);
        viewPager2.setAdapter(adapter);

        viewPager2.setUserInputEnabled(false);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {

                super.onPageSelected(position);

                currentFragmentPos = position;
                viewPager2.setUserInputEnabled(false);
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {

                        viewPager2.setUserInputEnabled(true);

                    }
                }, 1000);

                MovieFragment fragment = (MovieFragment) adapter.getFragmentActivity()
                        .getSupportFragmentManager()
                        .findFragmentByTag("f" + position);

                if (fragment != null) {

                    if (fragment.isMovieToWatch)
                        binding.checkImageView.setBackgroundResource(R.color.colorGreenTrans);
                    else binding.checkImageView.setBackgroundResource(R.color.colorLightTrans);

                    if (position != 0)
                        fragment.smoothScrollDown();

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        movieCollectionViewModel.getIsAccessedToList().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                System.out.println(aBoolean);

            }
        });

        movieCollectionViewModel.getIsMovieToWatch().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                MovieFragment fragment = (MovieFragment) adapter.getFragmentActivity()
                        .getSupportFragmentManager()
                        .findFragmentByTag("f" + currentFragmentPos);

                if (fragment != null) {

                    if (aBoolean) {

                        fragment.isMovieToWatch = true;

                        Movie movie = fragment.currentMovie;
                        int id = Integer.parseInt(movie.getImdbID().substring(2));
                        movie.setImdbIdClear(id);

                        ((MovieActivity)MovieCollectionFragment.this.getActivity())
                                .getRoomInstance()
                                .movieDao()
                                .insertMovie(movie);

                        binding.checkImageView.setBackgroundResource(R.color.colorGreenTrans);

                    } else {

                        fragment.isMovieToWatch = false;
                        binding.checkImageView.setBackgroundResource(R.color.colorLightTrans);

                    }

                }

            }
        });

    }
}
