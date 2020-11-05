package com.ambiwsstudio.movie_shuffler.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
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

public class MovieCollectionFragment extends Fragment {

    private static MovieCollectionFragment instance;
    private MovieCollectionPagerAdapter adapter;
    FragmentMovieCollectionBinding binding;
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

    @SuppressLint("StaticFieldLeak")
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

                // TODO FIX 'WATCH LATER' DISAPPEARS ON FRAGMENT COMEBACK
                // TODO USER INPUT ENABLE AFTER SCROLL VIA SHARED VM
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

        movieCollectionViewModel.getIsAccessedToList().observe(this, aBoolean -> {

            Intent intent = new Intent(getContext(), MovieListActivity.class);
            startActivity(intent);

        });

        movieCollectionViewModel.getIsMovieToWatch().observe(this, aBoolean -> {

            if (aBoolean) {

                binding.checkImageView.setBackgroundResource(R.color.colorGreenTrans);

            } else {

                binding.checkImageView.setBackgroundResource(R.color.colorLightTrans);

            }

        });

    }
}
