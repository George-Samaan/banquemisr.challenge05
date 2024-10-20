package com.example.banquemisrchallenge05.ui.features.moviesHome.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.banquemisrchallenge05.data.repository.Repository
import retrofit2.HttpException
import java.io.IOException

class MoviesHomeViewModel(private val repository: Repository) : ViewModel() {
    val popular = repository.getNowPlayingMovies().cachedIn(viewModelScope)
    val nowPlaying = repository.getPopularMovies().cachedIn(viewModelScope)
    val upcoming = repository.getUpcomingMovies().cachedIn(viewModelScope)
}

fun getErrorMessage(exception: Throwable): String {
    return when (exception) {
        is IOException -> "Network error. Please check your connection."
        is HttpException -> "Server error. Please try again later."
        else -> "An unexpected error occurred. Please try again."
    }
}