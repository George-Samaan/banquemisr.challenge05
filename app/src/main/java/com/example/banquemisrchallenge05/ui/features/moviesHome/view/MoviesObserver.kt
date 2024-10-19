package com.example.banquemisrchallenge05.ui.features.moviesHome.view

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.banquemisrchallenge05.ui.features.moviesHome.viewModel.MoviesHomeViewModel
import com.example.banquemisrchallenge05.utils.isNetworkAvailable

class MoviesObserver(
    private val viewModel: MoviesHomeViewModel,
    private val context: Context
) : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        if (isNetworkAvailable(context)) {
            viewModel.getAllData()
        }
    }
}