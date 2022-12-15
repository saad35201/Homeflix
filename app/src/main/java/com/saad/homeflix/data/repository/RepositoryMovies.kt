package com.saad.homeflix.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.saad.homeflix.data.models.ResponseMovies
import com.saad.homeflix.data.network.remote.MoviesApiService
import com.saad.homeflix.utils.NetworkResult
import com.saad.homeflix.utils.TAG
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class RepositoryMovies @Inject constructor(
    private var service: MoviesApiService
) {

    //live data for all movies response
    private val _moviesResponseLiveData = MutableLiveData<NetworkResult<ResponseMovies>>()
    val moviesResponseLiveData: LiveData<NetworkResult<ResponseMovies>>
    get() = _moviesResponseLiveData

    //live data for search movies response
    private val _moviesSearchResponseLiveData = MutableLiveData<NetworkResult<ResponseMovies>>()
    val moviesSearchResponseLiveData: LiveData<NetworkResult<ResponseMovies>>
        get() = _moviesSearchResponseLiveData

    suspend fun getMovies(api_key: String?) {
        _moviesResponseLiveData.postValue(NetworkResult.Loading())
        val response = service.getMovies(api_key)
        handleResponse(response)
    }

    suspend fun searchMovie(
        api_key: String?,
        query: String,
        page: Int
    ) {
        _moviesSearchResponseLiveData.postValue(NetworkResult.Loading())
        val response = service.searchMovie(api_key, query, page)
        handleResponse(response)
    }

    private fun handleResponse(response: Response<ResponseMovies>) {
        if (response.isSuccessful && response.body() != null) {
            _moviesResponseLiveData.postValue(NetworkResult.Success(response.body()))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _moviesResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("status_message")))
        } else {
            _moviesResponseLiveData.postValue(NetworkResult.Error("Some thing went Wrong"))
        }
    }


}