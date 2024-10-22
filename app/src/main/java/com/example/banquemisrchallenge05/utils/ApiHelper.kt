package com.example.banquemisrchallenge05.utils

import com.example.banquemisrchallenge05.data.network.ResponseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.net.UnknownHostException

object ApiHelper {
    private const val TAG = "ApiHelper"
    suspend fun <T> handleApiCall(call: suspend () -> Response<T>): Flow<ResponseResult<T>> = flow {
        try {
            val response = call()
            if (response.isSuccessful && response.body() != null) {
                emit(ResponseResult.Success(response.body()!!))
            } else {
                emit(ResponseResult.Error(Exception("Failed to fetch data")))
            }
        } catch (e: UnknownHostException) {
            emit(ResponseResult.Error(Exception("Unable to connect to the internet. Please check your Wi-Fi or mobile data.")))
        } catch (e: Exception) {
            emit(ResponseResult.Error(e))
        }
    }
}