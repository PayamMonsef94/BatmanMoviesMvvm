package com.example.batmanmoviesmvvm.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.batmanmoviesmvvm.data.model.Detail
import com.example.batmanmoviesmvvm.data.model.Movie
import io.reactivex.Single

@Dao
interface AppDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies(): Single<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovies(movies: List<Movie>)

    @Query("SELECT * FROM details where imdbID = :movieId")
    fun getDetail(movieId: String): Single<Detail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetail(detail: Detail)

}
