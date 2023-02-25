package com.saad.homeflix.views.modules.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saad.homeflix.data.models.ResponseMovies
import com.saad.homeflix.data.repository.RepositoryMovies
import com.saad.homeflix.utils.API_KEY
import com.saad.homeflix.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VmMoviesList @Inject constructor(private val moviesRepository: RepositoryMovies) :
    ViewModel() {

    //live data all movies
    private val mMoviesLiveData : MutableLiveData<NetworkResult<ResponseMovies>> by lazy { MutableLiveData() }

    val moviesLiveData: LiveData<NetworkResult<ResponseMovies>> get() = mMoviesLiveData


    fun getMovies() {
        viewModelScope.launch {
            mMoviesLiveData.postValue(NetworkResult.Loading())
            mMoviesLiveData.postValue(moviesRepository.getMovies(API_KEY,1))
        }
    }

//    fun searchMovie(query: String){
//        viewModelScope.launch {
//            moviesRepository.searchMovie(API_KEY,query,page)
//        }
//    }

}