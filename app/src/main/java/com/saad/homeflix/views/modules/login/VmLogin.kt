package com.saad.homeflix.views.modules.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saad.homeflix.data.models.user.UserRequest
import com.saad.homeflix.data.models.user.UserResponse
import com.saad.homeflix.data.repository.RepositoryAuth
import com.saad.homeflix.utils.LOGIN_URL
import com.saad.homeflix.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VmLogin @Inject constructor(private val repository: RepositoryAuth) : ViewModel() {

    //livedata
    val loginLiveData: LiveData<NetworkResult<UserResponse>>
        get() = repository.authResponseLiveData

    fun login(userRequest: UserRequest) {
        viewModelScope.launch {
            repository.login(LOGIN_URL, userRequest)
        }
    }

}