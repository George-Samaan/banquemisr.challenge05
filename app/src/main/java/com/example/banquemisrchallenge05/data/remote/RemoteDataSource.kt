package com.example.banquemisrchallenge05.data.remote

import com.example.banquemisrchallenge05.data.model.MovieDetailsResponse
import com.example.banquemisrchallenge05.data.model.MovieResponse
import com.example.banquemisrchallenge05.data.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getNowPlayingMovies(): Flow<ResponseResult<MovieResponse>>
    suspend fun getPopularMovies(): Flow<ResponseResult<MovieResponse>>
    suspend fun getUpcomingMovies(): Flow<ResponseResult<MovieResponse>>
    suspend fun getMovieDetails(movieId: Int): Flow<ResponseResult<MovieDetailsResponse>>
}