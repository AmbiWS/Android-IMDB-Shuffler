package com.ambiwsstudio.movie_shuffler.adapter;

import android.os.Bundle;

import com.ambiwsstudio.movie_shuffler.view.MovieFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MovieCollectionPagerAdapter extends FragmentStateAdapter {

    private static int pagesCount = 5;

    public MovieCollectionPagerAdapter(FragmentActivity fa) {
        super(fa);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public boolean containsItem(long itemId) {
        return super.containsItem(itemId);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        pagesCount++;
        Fragment fragment = new MovieFragment();
        Bundle args = new Bundle();
        args.putString(MovieFragment.ARG_TAG, String.valueOf(position + 1));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return pagesCount;
    }

}
