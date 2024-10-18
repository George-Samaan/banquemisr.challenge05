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

class MoviesHomeViewModel(private val repository: Repository) : ViewModel() {
    private val _nowPlayingMovies = MutableStateFlow<ApiState>(ApiState.Loading)
    val nowPlayingMovies: StateFlow<ApiState> get() = _nowPlayingMovies

    private val _popularMovies = MutableStateFlow<ApiState>(ApiState.Loading)
    val popularMovies: StateFlow<ApiState> get() = _popularMovies

    private val _upcomingMovies = MutableStateFlow<ApiState>(ApiState.Loading)
    val upcomingMovies: StateFlow<ApiState> get() = _upcomingMovies

    private fun fetchNowPlayingMovies() {
        _nowPlayingMovies.value = ApiState.Loading
        viewModelScope.launch {
            repository.getNowPlayingMovies()
                .catch { error ->
                    _nowPlayingMovies.value = ApiState.Failure(error)
                }
                .collect { result ->
                    when (result) {
                        is ResponseResult.Success -> {
                            _nowPlayingMovies.value = ApiState.Success(result.data)
                        }

                        is ResponseResult.Error -> {
                            _nowPlayingMovies.value = ApiState.Failure(result.exception)
                        }
                    }
                }
        }
    }

    private fun fetchPopularMovies() {
        _popularMovies.value = ApiState.Loading
        viewModelScope.launch {
            repository.getPopularMovies().catch { error ->
                _popularMovies.value = ApiState.Failure(error)

            }.collect { result ->
                when (result) {
                    is ResponseResult.Success -> {
                        _popularMovies.value = ApiState.Success(result.data)
                    }

                    is ResponseResult.Error -> {
                        _popularMovies.value = ApiState.Failure(result.exception)
                    }
                }

            }
        }
    }

    private fun fetchUpcomingMovies() {
        _upcomingMovies.value = ApiState.Loading
        viewModelScope.launch {
            repository.getUpcomingMovies().catch { error ->
                _upcomingMovies.value = ApiState.Failure(error)
            }.collect { result ->
                when (result) {
                    is ResponseResult.Success -> {
                        _upcomingMovies.value = ApiState.Success(result.data)
                    }

                    is ResponseResult.Error -> {
                        _upcomingMovies.value = ApiState.Failure(result.exception)
                    }
                }
            }
        }
    }

    fun getAllData() {
        fetchNowPlayingMovies()
        fetchPopularMovies()
        fetchUpcomingMovies()
    }
}