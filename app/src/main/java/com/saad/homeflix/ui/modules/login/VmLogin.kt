package com.saad.homeflix.ui.modules.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saad.homeflix.data.models.user.UserRequest
import com.saad.homeflix.data.models.user.UserResponse
import com.saad.homeflix.data.repository.RepositoryAuth
import com.saad.homeflix.utils.API_KEY
import com.saad.homeflix.utils.AUTH_BASE_URL
import com.saad.homeflix.utils.NetworkResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class VmLogin @Inject constructor(private val repository: RepositoryAuth): ViewModel() {

    //livedata
    val loginLiveData: LiveData<NetworkResult<UserResponse>>
        get() = repository.authResponseLiveData

    fun login(userRequest: UserRequest){
        viewModelScope.launch {
            repository.login(AUTH_BASE_URL,userRequest)
        }
    }

}