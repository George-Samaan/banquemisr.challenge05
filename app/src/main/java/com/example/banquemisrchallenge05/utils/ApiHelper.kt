package com.example.banquemisrchallenge05.utils

import android.util.Log
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
                // Log successful response details
                Log.d(TAG, "API Call Success: ${response.body()}")
                emit(ResponseResult.Success(response.body()!!))
            } else {
                // Log error details in case of failure
                Log.e(TAG, "API Call Error: ${response.code()} - ${response.message()}")
                emit(ResponseResult.Error(Exception("Failed to fetch data")))
            }
        } catch (e: UnknownHostException) {
            Log.e(TAG, "No internet connection: ${e.message}")
            emit(ResponseResult.Error(Exception("Unable to connect to the internet. Please check your Wi-Fi or mobile data.")))
        } catch (e: Exception) {
            // Log exceptions during the API call
            Log.e(TAG, "API Call Exception: ${e.message}")
            emit(ResponseResult.Error(e))
        }
    }
}