package com.ambiwsstudio.movie_shuffler.repository;

import com.ambiwsstudio.movie_shuffler.model.Movie;

import java.util.List;

import androidx.room.Insert;
import androidx.room.Query;

public interface MovieDao {

    @Query("SELECT * FROM movie")
    List<Movie> getAll();

    @Insert
    void insertMovie(Movie movie);

    @Query("DELETE FROM movie WHERE id = :movId")
    void deleteMovieById(int movId);

}
