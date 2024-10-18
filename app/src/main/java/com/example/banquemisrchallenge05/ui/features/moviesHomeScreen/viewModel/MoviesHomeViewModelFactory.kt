package com.example.banquemisrchallenge05.ui.features.moviesHomeScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.banquemisrchallenge05.data.repository.Repository

class MoviesHomeViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesHomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MoviesHomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}