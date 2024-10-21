package com.example.banquemisrchallenge05.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.banquemisrchallenge05.data.model.Movie
import com.example.banquemisrchallenge05.data.model.MovieDetailsResponse
import com.example.banquemisrchallenge05.data.network.ResponseResult
import com.example.banquemisrchallenge05.data.paging.NowPlayingMoviesPagingSource
import com.example.banquemisrchallenge05.data.paging.PopularMoviesPagingSource
import com.example.banquemisrchallenge05.data.paging.UpcomingMoviesPagingSource
import com.example.banquemisrchallenge05.data.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : Repository {
    override fun getNowPlayingMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { NowPlayingMoviesPagingSource(remoteDataSource) }).flow
    }

    override fun getPopularMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { PopularMoviesPagingSource(remoteDataSource) }).flow
    }

    override fun getUpcomingMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { UpcomingMoviesPagingSource(remoteDataSource) }).flow
    }

    override suspend fun getMovieDetails(movieId: Int): Flow<ResponseResult<MovieDetailsResponse>> {
        return remoteDataSource.getMovieDetails(movieId)
    }
}