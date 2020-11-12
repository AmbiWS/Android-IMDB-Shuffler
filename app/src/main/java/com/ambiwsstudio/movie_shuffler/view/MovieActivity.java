package com.ambiwsstudio.movie_shuffler.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import com.ambiwsstudio.movie_shuffler.R;
import com.ambiwsstudio.movie_shuffler.test.AgeActivity;

public class MovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_movie);

        Intent intent = new Intent(this, AgeActivity.class);
        startActivity(intent);

    }

}
