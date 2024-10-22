package com.example.banquemisrchallenge05.ui.repository

import com.example.banquemisrchallenge05.data.model.MovieDetailsResponse
import com.example.banquemisrchallenge05.data.model.MovieResponse
import com.example.banquemisrchallenge05.data.network.ResponseResult
import com.example.banquemisrchallenge05.data.remote.RemoteDataSource
import com.example.banquemisrchallenge05.ui.features.moviesDetailed.viewModel.MockData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRemoteDataSource : RemoteDataSource {
    override suspend fun getNowPlayingMovies(page: Int): Flow<ResponseResult<MovieResponse>> {
        return flow {
            emit(
                ResponseResult.Success(
                    MovieResponse(
                        MockData.mockMovieResponse.results,
                        MockData.mockMovieResponse.totalResults,
                        MockData.mockMovieResponse.totalPages
                    )
                )
            )
        }
    }

    override suspend fun getPopularMovies(page: Int): Flow<ResponseResult<MovieResponse>> {
        return flow {
            emit(
                ResponseResult.Success(
                    MovieResponse(
                        MockData.mockMovieResponse.results,
                        MockData.mockMovieResponse.totalResults,
                        MockData.mockMovieResponse.totalPages
                    )
                )
            )
        }
    }

    override suspend fun getUpcomingMovies(page: Int): Flow<ResponseResult<MovieResponse>> {
        return flow {
            emit(
                ResponseResult.Success(
                    MovieResponse(
                        MockData.mockMovieResponse.results,
                        MockData.mockMovieResponse.totalResults,
                        MockData.mockMovieResponse.totalPages
                    )
                )
            )
        }
    }

    override suspend fun getMovieDetails(movieId: Int): Flow<ResponseResult<MovieDetailsResponse>> {
        TODO("Not yet implemented")
    }
}