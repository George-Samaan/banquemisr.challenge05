package com.example.banquemisrchallenge05.data.remote

import com.example.banquemisrchallenge05.data.model.MovieDetailsResponse
import com.example.banquemisrchallenge05.data.model.MovieResponse
import com.example.banquemisrchallenge05.data.network.ResponseResult
import com.example.banquemisrchallenge05.data.network.TMDBApiServices
import com.example.banquemisrchallenge05.utils.ApiHelper.handleApiCall
import kotlinx.coroutines.flow.Flow


class RemoteDataSourceImpl(private val apiService: TMDBApiServices) : RemoteDataSource {
    override suspend fun getNowPlayingMovies(page: Int): Flow<ResponseResult<MovieResponse>> {
        return handleApiCall { apiService.getNowPlayingMovies(page = page) }
    }

    override suspend fun getPopularMovies(page: Int): Flow<ResponseResult<MovieResponse>> {
        return handleApiCall { apiService.getPopularMovies(page = page) }
    }

    override suspend fun getUpcomingMovies(page: Int): Flow<ResponseResult<MovieResponse>> {
        return handleApiCall { apiService.getUpcomingMovies(page = page) }
    }

    override suspend fun getMovieDetails(movieId: Int): Flow<ResponseResult<MovieDetailsResponse>> {
        return handleApiCall { apiService.getMovieDetails(movieId) }
    }
}