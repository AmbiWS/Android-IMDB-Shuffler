package com.ambiwsstudio.movie_shuffler.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ambiwsstudio.movie_shuffler.R;

import androidx.recyclerview.widget.RecyclerView;

// TODO CHECK WHERE TO PUT CLASS
public class MovieViewHolder extends RecyclerView.ViewHolder {

    private final TextView movieItemView;

    private MovieViewHolder(View itemView) {

        super(itemView);
        movieItemView = itemView.findViewById(R.id.textView);

    }

    public void bind(String text) {

        movieItemView.setText(text);

    }

    static MovieViewHolder create(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);

        return new MovieViewHolder(view);

    }

}
