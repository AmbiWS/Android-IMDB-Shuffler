package com.ambiwsstudio.movie_shuffler.adapter;

import android.os.Bundle;
import android.view.View;

import com.ambiwsstudio.movie_shuffler.view.MovieFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.adapter.FragmentViewHolder;
import androidx.viewpager2.widget.ViewPager2;

import static androidx.core.util.Preconditions.checkArgument;

public class MovieCollectionPagerAdapter extends FragmentStateAdapter {

    private static int pagesCount = 5;
    private FragmentActivity fa;

    public MovieCollectionPagerAdapter(FragmentActivity fa) {
        super(fa);
        this.fa = fa;
    }

    public FragmentActivity getFragmentActivity() {
        return fa;
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
        args.putString(MovieFragment.ARG_TAG, String.valueOf(position));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return pagesCount;
    }

}
