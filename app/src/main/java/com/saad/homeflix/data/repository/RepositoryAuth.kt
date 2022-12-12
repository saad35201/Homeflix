package com.saad.homeflix.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.saad.homeflix.data.models.user.UserRequest
import com.saad.homeflix.data.models.user.UserResponse
import com.saad.homeflix.data.network.remote.AuthApiService
import com.saad.homeflix.utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class RepositoryAuth @Inject constructor(
    private val service: AuthApiService
) {

    //login live data
    private val _authResponseLiveData = MutableLiveData<NetworkResult<UserResponse>>()
    val authResponseLiveData: LiveData<NetworkResult<UserResponse>>
        get() = _authResponseLiveData

    suspend fun login(auth_url: String, userRequest: UserRequest) {
        _authResponseLiveData.postValue(NetworkResult.Loading())
        val response = service.login(auth_url, userRequest)
        handleResponse(response)
    }

    suspend fun register(auth_url: String, userRequest: UserRequest) {
        _authResponseLiveData.postValue(NetworkResult.Loading())
        val response = service.register(auth_url, userRequest)
        handleResponse(response)
    }

    private fun handleResponse(response: Response<UserResponse>) {
        if (response.isSuccessful && response.body() != null) {
            _authResponseLiveData.postValue(NetworkResult.Success(response.body()))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _authResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("status_message")))
        } else {
            _authResponseLiveData.postValue(NetworkResult.Error("Some thing went Wrong"))
        }
    }


}