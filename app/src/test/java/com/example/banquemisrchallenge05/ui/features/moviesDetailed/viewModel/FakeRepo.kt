package com.example.banquemisrchallenge05.ui.features.moviesDetailed.viewModel

import androidx.paging.PagingData
import com.example.banquemisrchallenge05.data.model.Movie
import com.example.banquemisrchallenge05.data.model.MovieDetailsResponse
import com.example.banquemisrchallenge05.data.network.ResponseResult
import com.example.banquemisrchallenge05.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepo : Repository {
    override fun getNowPlayingMovies(): Flow<PagingData<Movie>> {
        return flow {
            emit(PagingData.from(MockData.fakeMovies))
        }
    }

    override fun getPopularMovies(): Flow<PagingData<Movie>> {
        return flow {
            emit(PagingData.from(MockData.fakeMovies))
        }
    }

    override fun getUpcomingMovies(): Flow<PagingData<Movie>> {
        return flow {
            emit(PagingData.from(MockData.fakeMovies))
        }
    }

    override suspend fun getMovieDetails(movieId: Int): Flow<ResponseResult<MovieDetailsResponse>> {
        return flow {
            val movieDetails = MockData.mockMovieDetailsResponse.find { it.id == movieId }
            if (movieDetails != null) {
                emit(ResponseResult.Success(movieDetails))
            } else {
                emit(ResponseResult.Error(Exception("Movie with ID $movieId not found.")))
            }
        }
    }
}