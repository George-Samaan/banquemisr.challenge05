package com.example.banquemisrchallenge05.data.repository

import com.example.banquemisrchallenge05.data.model.MovieResponse
import com.example.banquemisrchallenge05.data.network.ResponseResult
import com.example.banquemisrchallenge05.data.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : Repository {
    override suspend fun getNowPlayingMovies(): Flow<ResponseResult<MovieResponse>> {
        return remoteDataSource.getNowPlayingMovies()
    }

    override suspend fun getPopularMovies(): Flow<ResponseResult<MovieResponse>> {
        return remoteDataSource.getPopularMovies()
    }

    override suspend fun getUpcomingMovies(): Flow<ResponseResult<MovieResponse>> {
        return remoteDataSource.getUpcomingMovies()
    }
}