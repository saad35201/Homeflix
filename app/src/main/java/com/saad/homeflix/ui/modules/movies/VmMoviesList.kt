package com.saad.homeflix.ui.modules.movies

import androidx.lifecycle.LiveData
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

    //test
    var page=1

    //live data all movies
    val moviesResponseLiveData: LiveData<NetworkResult<ResponseMovies>>
        get() = moviesRepository.moviesResponseLiveData


    fun getMovies() {
        viewModelScope.launch {
            moviesRepository.getMovies(API_KEY,page)
        }
    }

    fun searchMovie(query: String){
        viewModelScope.launch {
            moviesRepository.searchMovie(API_KEY,query,page)
        }
    }

}