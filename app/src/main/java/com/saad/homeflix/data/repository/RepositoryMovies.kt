package com.saad.homeflix.data.repository

import androidx.lifecycle.LiveData
import com.saad.homeflix.data.base.BaseRepository
import com.saad.homeflix.data.models.ResponseMovies
import com.saad.homeflix.data.models.ResultsItem
import com.saad.homeflix.data.network.local.MovieDao
import com.saad.homeflix.data.network.remote.MoviesApiService
import com.saad.homeflix.utils.NetworkResult
import javax.inject.Inject

class RepositoryMovies @Inject constructor(
    private var service: MoviesApiService,
    private var moviesDao: MovieDao
) : BaseRepository() {


    //for remote calls

    suspend fun getMovies(api_key: String, page: Int): NetworkResult<ResponseMovies> {
        return safeApiCall { service.getMovies(api_key, page) }
    }

    suspend fun searchMovie(
        api_key: String?,
        query: String,
        page: Int
    ): NetworkResult<ResponseMovies> {
        return safeApiCall { service.searchMovie(api_key, query, page) }
    }

    //for local calls

     suspend fun getAllLikedMovies(): List<ResultsItem> {
         return moviesDao.getAllLikedMovies()
     }

    suspend fun likeMovie(movieId: Int){
        return moviesDao.likeMovie(movieId)
    }

    suspend fun disLikeMovie(moviesId: Int){
        return moviesDao.dislikeMovie(moviesId)
    }


}