package com.saad.homeflix.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.saad.homeflix.data.models.ResultsItem
import com.saad.homeflix.data.network.remote.MoviesApiService
import com.saad.homeflix.data.paging.MoviesPagingSrc
import javax.inject.Inject

class RepositoryMovies @Inject constructor(
    private var service: MoviesApiService
) {

    fun getMovies() = Pager(
        pagingSourceFactory = { MoviesPagingSrc(service) },
        config = PagingConfig(pageSize = 20, maxSize = 100)
    ).liveData

}