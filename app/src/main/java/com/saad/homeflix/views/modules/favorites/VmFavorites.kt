package com.saad.homeflix.views.modules.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saad.homeflix.data.models.ResponseMovies
import com.saad.homeflix.data.models.ResultsItem
import com.saad.homeflix.data.repository.RepositoryMovies
import com.saad.homeflix.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VmFavorites @Inject constructor(
    private val moviesRepository: RepositoryMovies
): ViewModel() {

    //live data favorite movies
    private val mMoviesLiveData : MutableLiveData<List<ResultsItem>> by lazy { MutableLiveData() }
    val moviesLiveData: LiveData<List<ResultsItem>> get() = mMoviesLiveData

    suspend fun getAllFavoriteMovies() {
        mMoviesLiveData.value = moviesRepository.getAllLikedMovies()
    }

    suspend fun likeMovie(movieId: Int){
        moviesRepository.likeMovie(movieId)
    }

    suspend fun disLikeMovie(movieId: Int){
        moviesRepository.disLikeMovie(movieId)
    }

}