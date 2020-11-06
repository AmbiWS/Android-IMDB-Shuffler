package com.ambiwsstudio.movie_shuffler.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
import com.ambiwsstudio.movie_shuffler.viewmodel.MovieSharedViewModel;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

public class MovieCollectionFragment extends Fragment {

    private MovieCollectionPagerAdapter adapter;
    private MovieSharedViewModel sharedViewModel;
    private final HashSet<String> moviesToWatch = new HashSet<>();
    private int currentPosition = -1;
    private Movie currentMovie;
    FragmentMovieCollectionBinding binding;
    ViewPager2 viewPager2;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_movie_collection, container, false);

        return binding.getRoot();

    }

    /*private void updateRange(int i) {

        if (!isRangePreloaded) {

            if (fragmentRange.size() < 5 && !fragmentRange.contains(i)) {

                fragmentRange.addLast(currentPosition);

                if (fragmentRange.size() == 5)
                    isRangePreloaded = true;

            }

        } else {

            int currPosTemp = currentPosition;
            if (fragmentRange.getLast() == currPosTemp - 1) {

                fragmentRange.clear();
                for (int j = 0; j < 5; j++)
                    fragmentRange.addFirst(currPosTemp--);

            } else if (fragmentRange.getFirst() - 1 == currPosTemp) {

                fragmentRange.clear();
                for (int j = 0; j < 5; j++)
                    fragmentRange.addLast(currPosTemp++);

            }

        }

    }*/

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        currentMovie = new Movie();
        sharedViewModel = new ViewModelProvider(requireActivity()).get(MovieSharedViewModel.class);
        MovieCollectionViewModel movieCollectionViewModel = ViewModelProviders.of(this).get(MovieCollectionViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setMovieCollectionViewModel(movieCollectionViewModel);

        adapter = new MovieCollectionPagerAdapter(this.getActivity());
        viewPager2 = view.findViewById(R.id.pager);
        viewPager2.setAdapter(adapter);
        viewPager2.setOffscreenPageLimit(1);

        viewPager2.setUserInputEnabled(false);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {

                super.onPageSelected(position);

                currentPosition = position;
                viewPager2.setUserInputEnabled(false);

                //System.out.println(moviesToWatch);
                //System.out.println(position);
                if (moviesToWatch.contains("f" + currentPosition)) {

                    binding.checkImageView.setBackgroundResource(R.color.colorGreenTrans);

                } else {

                    binding.checkImageView.setBackgroundResource(R.color.colorLightTrans);

                }

                sharedViewModel.setCurrentFragmentInView("f" + position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        movieCollectionViewModel.getIsAccessedToList().observe(getViewLifecycleOwner(), aBoolean -> {

            Intent intent = new Intent(getContext(), MovieListActivity.class);
            startActivity(intent);

        });

        movieCollectionViewModel.getIsMovieToWatch().observe(getViewLifecycleOwner(), aBoolean -> {

            if (aBoolean) {

                binding.checkImageView.setBackgroundResource(R.color.colorGreenTrans);
                moviesToWatch.add("f" + currentPosition);

            } else {

                binding.checkImageView.setBackgroundResource(R.color.colorLightTrans);
                moviesToWatch.remove("f" + currentPosition);

            }

            movieCollectionViewModel.saveMovie(aBoolean, currentMovie);

        });

        sharedViewModel.getIsFragmentLoaded().observe(getViewLifecycleOwner(), moviesToWatch::remove);

        sharedViewModel.getIsPageLoaded().observe(getViewLifecycleOwner(), aBoolean -> {

            sharedViewModel.setIsPageReadyForScroll(true);

            if (aBoolean) {

                binding.listImageView.setVisibility(View.VISIBLE);
                binding.checkImageView.setVisibility(View.VISIBLE);

            } else {

                binding.listImageView.setVisibility(View.GONE);
                binding.checkImageView.setVisibility(View.GONE);
                viewPager2.setUserInputEnabled(false);

            }

        });

        sharedViewModel.getIsPageScrolled().observe(getViewLifecycleOwner(), aBoolean -> viewPager2.setUserInputEnabled(true));

        sharedViewModel.getCurrentMovie().observe(getViewLifecycleOwner(), movie -> {

            currentMovie = movie;

        });

    }
}
