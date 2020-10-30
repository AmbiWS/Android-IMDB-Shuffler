package com.ambiwsstudio.movie_shuffler.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ambiwsstudio.movie_shuffler.R;
import com.ambiwsstudio.movie_shuffler.adapter.MovieCollectionPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieCollectionFragment extends Fragment {

    MovieCollectionPagerAdapter adapter;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_collection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        adapter = new MovieCollectionPagerAdapter(getChildFragmentManager());
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(adapter);
    }
}
