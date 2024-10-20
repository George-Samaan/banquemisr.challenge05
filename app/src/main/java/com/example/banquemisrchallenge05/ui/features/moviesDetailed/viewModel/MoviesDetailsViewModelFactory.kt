package com.example.banquemisrchallenge05.ui.features.moviesDetailed.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.banquemisrchallenge05.data.repository.Repository

class MoviesDetailsViewModelFactory(private val repository: Repository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MoviesDetailsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}