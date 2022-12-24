package com.saad.homeflix.ui.modules.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.saad.homeflix.data.models.ResultsItem
import com.saad.homeflix.data.repository.RepositoryMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VmMoviesList @Inject constructor(private val moviesRepository: RepositoryMovies) :
    ViewModel() {

    //livedata
     val moviesLiveData = moviesRepository.getMovies().cachedIn(viewModelScope)

}