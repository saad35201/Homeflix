package com.saad.homeflix.ui.modules.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.saad.homeflix.data.repository.RepositoryMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VmMoviesList @Inject constructor(moviesRepository: RepositoryMovies) :
    ViewModel() {

    //livedata
    val moviesLiveData = moviesRepository.getMovies().cachedIn(viewModelScope)

}