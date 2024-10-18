package com.example.banquemisrchallenge05.data.network

sealed class ApiState {
    class Success(val data: Any) : ApiState()
    class Failure(val message: Throwable) : ApiState()
    data object Loading : ApiState()
}