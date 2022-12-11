package com.saad.homeflix.data.network.remote

import com.saad.homeflix.data.models.ResponseMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {

    @GET("movie/popular?")
    suspend fun getMovies(
        @Query("api_key") api_key: String?
    ): Response<ResponseMovies>

    @GET("search/movie?")
    suspend fun searchMovie(
        @Query("api_key") api_key: String?,
        @Query("query") query: String,
        @Query("page") page: Int
    ) : Response<ResponseMovies>

}