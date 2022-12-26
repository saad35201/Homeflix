package com.saad.homeflix.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.saad.homeflix.data.base.BaseRepository
import com.saad.homeflix.data.models.ResponseMovies
import com.saad.homeflix.data.network.remote.MoviesApiService
import com.saad.homeflix.utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class RepositoryMovies @Inject constructor(
    private var service: MoviesApiService
) : BaseRepository() {

    //live data for all movies response
    private val _moviesResponseLiveData = MutableLiveData<NetworkResult<ResponseMovies>>()
    val moviesResponseLiveData: LiveData<NetworkResult<ResponseMovies>>
        get() = _moviesResponseLiveData


    suspend fun getMovies(api_key: String, page: Int) {
        _moviesResponseLiveData.postValue(NetworkResult.Loading())
        _moviesResponseLiveData.postValue(safeApiCall { service.getMovies(api_key, page) }!!)
    }

    suspend fun searchMovie(
        api_key: String?,
        query: String,
        page: Int
    ) {
        _moviesResponseLiveData.postValue(NetworkResult.Loading())
        _moviesResponseLiveData.postValue(safeApiCall { service.searchMovie(api_key,query, page) }!!)
    }


}