package com.example.banquemisrchallenge05.ui.features.moviesHome.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banquemisrchallenge05.data.network.ApiState
import com.example.banquemisrchallenge05.data.network.ResponseResult
import com.example.banquemisrchallenge05.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MoviesHomeViewModel(private val repository: Repository) : ViewModel() {
    private val _nowPlayingMovies = MutableStateFlow<ApiState>(ApiState.Loading)
    val nowPlayingMovies: StateFlow<ApiState> get() = _nowPlayingMovies

    private val _popularMovies = MutableStateFlow<ApiState>(ApiState.Loading)
    val popularMovies: StateFlow<ApiState> get() = _popularMovies

    private val _upcomingMovies = MutableStateFlow<ApiState>(ApiState.Loading)
    val upcomingMovies: StateFlow<ApiState> get() = _upcomingMovies

    private fun fetchNowPlayingMovies() {
        viewModelScope.launch {
            repository.getNowPlayingMovies()
                .catch { error ->
                    _nowPlayingMovies.value = ApiState.Failure(getErrorMessage(error))
                }
                .collect { result ->
                    when (result) {
                        is ResponseResult.Success -> {
                            _nowPlayingMovies.value = ApiState.Success(result.data)
                        }

                        is ResponseResult.Error -> {
                            _nowPlayingMovies.value = ApiState.Failure(
                                result.exception.message ?: "Unknown error occurred"
                            )
                        }
                    }
                }
        }
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch {
            repository.getPopularMovies().catch { error ->
                _popularMovies.value = ApiState.Failure(getErrorMessage(error))

            }.collect { result ->
                when (result) {
                    is ResponseResult.Success -> {
                        _popularMovies.value = ApiState.Success(result.data)
                    }

                    is ResponseResult.Error -> {
                        _popularMovies.value =
                            ApiState.Failure(result.exception.message ?: "Unknown error occurred")
                    }
                }

            }
        }
    }

    private fun fetchUpcomingMovies() {
        viewModelScope.launch {
            repository.getUpcomingMovies().catch { error ->
                _upcomingMovies.value = ApiState.Failure(getErrorMessage(error))
            }.collect { result ->
                when (result) {
                    is ResponseResult.Success -> {
                        _upcomingMovies.value = ApiState.Success(result.data)
                    }

                    is ResponseResult.Error -> {
                        _upcomingMovies.value =
                            ApiState.Failure(result.exception.message ?: "Unknown error occurred")
                    }
                }
            }
        }
    }

    fun getAllData() {
        _nowPlayingMovies.value = ApiState.Loading
        _popularMovies.value = ApiState.Loading
        _upcomingMovies.value = ApiState.Loading
        fetchNowPlayingMovies()
        fetchPopularMovies()
        fetchUpcomingMovies()
    }
}

fun getErrorMessage(exception: Throwable): String {
    return when (exception) {
        is IOException -> "Network error. Please check your connection."
        is HttpException -> "Server error. Please try again later."
        else -> "An unexpected error occurred. Please try again."
    }
}