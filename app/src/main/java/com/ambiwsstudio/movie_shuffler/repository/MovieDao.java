package com.ambiwsstudio.movie_shuffler.repository;

import com.ambiwsstudio.movie_shuffler.model.Movie;
import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie")
    LiveData<List<Movie>> getAll();

    @Insert
    void insertMovie(Movie movie);

    @Query("DELETE FROM movie WHERE id = :movId")
    void deleteMovieById(String movId);

}
