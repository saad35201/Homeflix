package com.saad.homeflix.data.base

import com.saad.homeflix.utils.NetworkResult
import retrofit2.Response

open class BaseRepository {

//    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Response<T> {
//
//        return when (val result: NetworkResult<T> = safeApiResult(call)) {
//            is NetworkResult.Success ->
//                Response(result.data, null)
//            is NetworkResult.Error -> {
//                Response(null, result.errorResponse)
//            }
//        }
//    }
//
//    private suspend fun <T : Any> safeApiResult(call: suspend () -> Response<T>): NetworkResult<T> {
//        try {
//            val response = call.invoke()
//            if (response.isSuccessful) {
//                return NetworkResult.Success(response.body()!!)
//            }
//            return NetworkResult.Error(parseError(response))
//        } catch (ex: Exception) {
//            ex.printStackTrace()
//            return NetworkResult.Error(parseError(ex))
//        }
//    }

}