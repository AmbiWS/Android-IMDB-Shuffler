package com.ambiwsstudio.movie_shuffler.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ambiwsstudio.movie_shuffler.R;
import androidx.recyclerview.widget.RecyclerView;
import dagger.Module;
import dagger.Provides;

@Module
public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView movieItemView;
    private final CustomGridLayout gridLayout;

    private MovieViewHolder(View itemView) {

        super(itemView);
        movieItemView = itemView.findViewById(R.id.textView);
        gridLayout = (CustomGridLayout)itemView;
        gridLayout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(gridLayout.getLink()));
        v.getContext().startActivity(browserIntent);

    }

    public void bind(String text, String link) {

        final String IMDB_BASE_URL = "https://www.imdb.com";
        gridLayout.setLink(IMDB_BASE_URL + "/title/" + link);
        movieItemView.setText(text);

    }

    @Provides
    static MovieViewHolder create(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);

        return new MovieViewHolder(view);

    }

}
