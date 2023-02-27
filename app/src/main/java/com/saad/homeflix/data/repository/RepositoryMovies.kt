package com.saad.homeflix.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.saad.homeflix.data.network.remote.MoviesApiService
import com.saad.homeflix.data.paging.MoviesPagingSrc
import javax.inject.Inject

class RepositoryMovies @Inject constructor(
    private var service: MoviesApiService
) {

    //paging data
    private val pageSize = 20
    private val prefetchDistance = 3 * pageSize
    private val initialLoadSize = 2 * pageSize
    private val maxSize = pageSize + (2 * prefetchDistance)

    fun getMovies() = Pager(
        pagingSourceFactory = { MoviesPagingSrc(service) },
        config = PagingConfig(
            pageSize = pageSize,
            maxSize = maxSize,
            prefetchDistance = prefetchDistance,
            initialLoadSize = initialLoadSize,
            enablePlaceholders = false
        )
    ).liveData

}