package com.example.banquemisrchallenge05.data.repository

import androidx.paging.PagingData
import com.example.banquemisrchallenge05.data.model.Movie
import com.example.banquemisrchallenge05.data.model.MovieDetailsResponse
import com.example.banquemisrchallenge05.data.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getNowPlayingMovies(): Flow<PagingData<Movie>>
    fun getPopularMovies(): Flow<PagingData<Movie>>
    fun getUpcomingMovies(): Flow<PagingData<Movie>>
    suspend fun getMovieDetails(movieId: Int): Flow<ResponseResult<MovieDetailsResponse>>
}