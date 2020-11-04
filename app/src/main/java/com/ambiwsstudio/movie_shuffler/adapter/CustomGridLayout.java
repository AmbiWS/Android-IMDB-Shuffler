package com.ambiwsstudio.movie_shuffler.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;

public class CustomGridLayout extends GridLayout {

    private String link;

    public String getLink() {

        return this.link;

    }

    public void setLink(String link) {

        this.link = link;

    }

    public CustomGridLayout(Context context) {

        super(context);

    }

    public CustomGridLayout(Context context, AttributeSet attrs) {

        super(context, attrs);

    }

    public CustomGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);

    }

}
