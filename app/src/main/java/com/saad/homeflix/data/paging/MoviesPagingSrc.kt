package com.saad.homeflix.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.saad.homeflix.data.models.ResultsItem
import com.saad.homeflix.data.network.remote.MoviesApiService
import com.saad.homeflix.utils.API_KEY

class MoviesPagingSrc(private val service: MoviesApiService) : PagingSource<Int, ResultsItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultsItem> {
        return try {
            val currentPage = params.key ?: 1
            val response = service.getMovies(API_KEY)
            val responseData = mutableListOf<ResultsItem>()
            val data = response.body()?.results ?: emptyList()
            responseData.addAll(data)


            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (currentPage == response.body()!!.totalPages) null else currentPage + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ResultsItem>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}