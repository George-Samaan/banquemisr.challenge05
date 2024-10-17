package com.example.banquemisrchallenge05.data.remote

import com.example.banquemisrchallenge05.data.model.MovieResponse
import com.example.banquemisrchallenge05.data.network.ResponseResult
import com.example.banquemisrchallenge05.data.network.TMDBApiServices
import com.example.banquemisrchallenge05.utils.ApiHelper.handleApiCall
import kotlinx.coroutines.flow.Flow


class RemoteDataSourceImpl(private val apiService: TMDBApiServices) : RemoteDataSource {
    override suspend fun getNowPlayingMovies(): Flow<ResponseResult<MovieResponse>> {
        return handleApiCall { apiService.getNowPlayingMovies() }
    }

    override suspend fun getPopularMovies(): Flow<ResponseResult<MovieResponse>> {
        return handleApiCall { apiService.getPopularMovies() }
    }

    override suspend fun getUpcomingMovies(): Flow<ResponseResult<MovieResponse>> {
        return handleApiCall { apiService.getUpcomingMovies() }
    }
}