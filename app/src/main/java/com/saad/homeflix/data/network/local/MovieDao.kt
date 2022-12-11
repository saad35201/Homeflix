package com.saad.homeflix.data.network.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.saad.homeflix.data.models.ResultsItem

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie ORDER BY id DESC")
    fun getAllMovies() : LiveData<List<ResultsItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(list: List<ResultsItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movie : ResultsItem)

    @Delete
    suspend fun deleteMovie(movie: ResultsItem)

}