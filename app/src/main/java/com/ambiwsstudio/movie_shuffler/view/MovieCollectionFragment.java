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
import com.ambiwsstudio.movie_shuffler.viewmodel.MovieCollectionViewModel;
import com.ambiwsstudio.movie_shuffler.viewmodel.MovieSharedViewModel;

import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

public class MovieCollectionFragment extends Fragment {

    private MovieCollectionPagerAdapter adapter;
    private MovieSharedViewModel sharedViewModel;
    private final HashSet<String> moviesToWatch = new HashSet<>();
    private int currentPosition = 0;
    FragmentMovieCollectionBinding binding;
    ViewPager2 viewPager2;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_movie_collection, container, false);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        sharedViewModel = new ViewModelProvider(requireActivity()).get(MovieSharedViewModel.class);
        MovieCollectionViewModel movieCollectionViewModel = ViewModelProviders.of(this).get(MovieCollectionViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setMovieCollectionViewModel(movieCollectionViewModel);

        adapter = new MovieCollectionPagerAdapter(this.getActivity());
        viewPager2 = view.findViewById(R.id.pager);
        viewPager2.setAdapter(adapter);
        viewPager2.setOffscreenPageLimit(2);

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
                System.out.println("ON PAGE SELECTED");
                currentPosition = position;
                viewPager2.setUserInputEnabled(false);

                if (moviesToWatch.contains("f" + currentPosition))
                    binding.checkImageView.setBackgroundResource(R.color.colorGreenTrans);

                sharedViewModel.setCurrentFragmentInView("f" + position);

                /*if (position == 1)
                    sharedViewModel.setIsPageReadyForScroll(true);*/


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

            sharedViewModel.setIsMovieToWatch(aBoolean);

        });

        sharedViewModel.getIsPageLoaded().observe(getViewLifecycleOwner(), aBoolean -> {

            System.out.println("0F LOADED PROC!");
            sharedViewModel.setIsPageReadyForScroll(true);

            if (aBoolean) {

                binding.listImageView.setVisibility(View.VISIBLE);
                binding.checkImageView.setVisibility(View.VISIBLE);


                // TODO
                //binding.checkImageView.setBackgroundResource(R.color.colorGreenTrans);

                //binding.checkImageView.setBackgroundResource(R.color.colorLightTrans);


            } else {

                binding.listImageView.setVisibility(View.GONE);
                binding.checkImageView.setVisibility(View.GONE);
                viewPager2.setUserInputEnabled(false);

            }

        });

        sharedViewModel.getIsPageScrolled().observe(getViewLifecycleOwner(), aBoolean -> viewPager2.setUserInputEnabled(true));

    }
}
