package com.ambiwsstudio.movie_shuffler.test;

import android.content.Context;
import android.os.Build;

import com.ambiwsstudio.movie_shuffler.R;

import androidx.annotation.RequiresApi;

public class ClassWithContext {

    Context context;

    public ClassWithContext(Context context) {

        this.context = context;

    }

    public String getData() {

        return context.getString(R.string.app_name);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public String divideIntColor() {

        int color = context.getColor(R.color.colorAccent);
        return String.valueOf(color / 2);

    }
}
