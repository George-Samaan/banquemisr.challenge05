package com.example.banquemisrchallenge05.data.repository

import com.example.banquemisrchallenge05.data.model.MovieResponse
import com.example.banquemisrchallenge05.data.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getNowPlayingMovies(): Flow<ResponseResult<MovieResponse>>
    suspend fun getPopularMovies(): Flow<ResponseResult<MovieResponse>>
    suspend fun getUpcomingMovies(): Flow<ResponseResult<MovieResponse>>
}