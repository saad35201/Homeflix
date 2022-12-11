package com.saad.homeflix.data.network.remote

import com.saad.homeflix.data.models.user.UserRequest
import com.saad.homeflix.data.models.user.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface AuthApiService {

    @POST("/users/signin")
    suspend fun login(
        @Url auth_url: String,
        @Body userRequest: UserRequest
    ): Response<UserResponse>

    @POST("/users/signup")
    suspend fun register(
        @Url auth_url: String,
        @Body userRequest: UserRequest
    ): Response<UserResponse>

}