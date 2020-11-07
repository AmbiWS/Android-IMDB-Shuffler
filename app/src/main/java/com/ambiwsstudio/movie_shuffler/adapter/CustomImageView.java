package com.ambiwsstudio.movie_shuffler.adapter;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomImageView extends androidx.appcompat.widget.AppCompatImageView {

    private String link;

    public String getLink() {

        return this.link;

    }

    public void setLink(String link) {

        this.link = link;

    }

    public CustomImageView(@NonNull Context context) {

        super(context);

    }

    public CustomImageView(@NonNull Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);

    }

    public CustomImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);

    }

}
