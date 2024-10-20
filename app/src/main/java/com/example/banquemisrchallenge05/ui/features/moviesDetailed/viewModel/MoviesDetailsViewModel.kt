package com.example.banquemisrchallenge05.ui.features.moviesDetailed.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banquemisrchallenge05.data.network.ApiState
import com.example.banquemisrchallenge05.data.network.ResponseResult
import com.example.banquemisrchallenge05.data.repository.Repository
import com.example.banquemisrchallenge05.ui.features.moviesHome.viewModel.getErrorMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MoviesDetailsViewModel(private val repository: Repository) : ViewModel() {
    private val _movieDetails = MutableStateFlow<ApiState>(ApiState.Loading)
    val movieDetails: StateFlow<ApiState> get() = _movieDetails

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            repository.getMovieDetails(movieId).catch { error ->
                _movieDetails.value = ApiState.Failure(getErrorMessage(error))
            }.collect { result ->
                when (result) {
                    is ResponseResult.Success -> {
                        Log.d("MovieDetailsViewModel", "Movie details fetched successfully")
                        _movieDetails.value = ApiState.Success(result.data)
                        Log.d("MovieDetailsViewModel", "Current State: $_movieDetails")
                    }

                    is ResponseResult.Error -> {
                        Log.e(
                            "MovieDetailsViewModel",
                            "Error fetching movie details: ${result.exception.message}"
                        )
                        _movieDetails.value =
                            ApiState.Failure(result.exception.message ?: "Unknown error occurred")
                    }
                }
            }
        }
    }
}