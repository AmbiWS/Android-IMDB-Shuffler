package com.ambiwsstudio.movie_shuffler.adapter;

import android.view.ViewGroup;

import com.ambiwsstudio.movie_shuffler.model.Movie;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class MovieListAdapter extends ListAdapter<Movie, MovieViewHolder> {

    public MovieListAdapter(@NonNull DiffUtil.ItemCallback<Movie> diffCallback) {

        super(diffCallback);

    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return MovieViewHolder.create(parent);

    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Movie current = getItem(position);
        holder.bind(current.getTitle() + "\n" + current.getYear(), "tt" + current.getImdbIdClear());

    }

    public static class MovieDiff extends DiffUtil.ItemCallback<Movie> {

        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {

            return oldItem == newItem;

        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {

            return oldItem.getTitle().equals(newItem.getTitle())
                    || oldItem.getImdbID().equals(newItem.getImdbID());

        }

    }
}
