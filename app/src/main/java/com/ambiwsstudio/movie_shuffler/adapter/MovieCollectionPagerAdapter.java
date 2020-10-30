package com.ambiwsstudio.movie_shuffler.adapter;

import android.os.Bundle;

import com.ambiwsstudio.movie_shuffler.view.MovieFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class MovieCollectionPagerAdapter extends FragmentStatePagerAdapter {

    public MovieCollectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Fragment fragment = new MovieFragment();
        Bundle args = new Bundle();
        args.putInt(MovieFragment.ARG_OBJECT, position + 1);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public int getCount() {
        return 100;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }
}
