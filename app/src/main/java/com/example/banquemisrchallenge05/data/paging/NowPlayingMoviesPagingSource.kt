package com.example.banquemisrchallenge05.data.paging//package com.example.banquemisrchallenge05.ui.features.moviesHome.view

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.banquemisrchallenge05.data.model.Movie
import com.example.banquemisrchallenge05.data.network.ResponseResult
import com.example.banquemisrchallenge05.data.remote.RemoteDataSource

class NowPlayingMoviesPagingSource(
    private val apiService: RemoteDataSource
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPage = params.key ?: 1
            var movies: List<Movie> = emptyList()

            apiService.getNowPlayingMovies(page = currentPage).collect { responseResult ->
                when (responseResult) {
                    is ResponseResult.Success -> {
                        movies = responseResult.data.results
                    }

                    is ResponseResult.Error -> {
                        throw responseResult.exception
                    }
                }
            }

            LoadResult.Page(
                data = movies,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (movies.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}