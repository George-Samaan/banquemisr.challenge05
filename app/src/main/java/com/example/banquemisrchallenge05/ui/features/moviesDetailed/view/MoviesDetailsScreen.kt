package com.example.banquemisrchallenge05.ui.features.moviesDetailed.view

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.banquemisrchallenge05.data.model.MovieDetailsResponse
import com.example.banquemisrchallenge05.data.network.ApiState
import com.example.banquemisrchallenge05.ui.features.moviesDetailed.viewModel.MoviesDetailsViewModel
import com.example.banquemisrchallenge05.ui.features.moviesHome.view.FailureCheck
import com.example.banquemisrchallenge05.utils.ConnectivityReceiver

@RequiresApi(Build.VERSION_CODES.O)
@Suppress("DEPRECATION")
@Composable
fun MovieDetailsScreen(
    movieId: Int,
    viewModel: MoviesDetailsViewModel,
    onBackPressed: () -> Unit
) {
    val movieDetailsState = viewModel.movieDetails.collectAsState().value
    val context = LocalContext.current
    var isConnected by remember { mutableStateOf(true) }

    val connectivityReceiver = remember {
        ConnectivityReceiver(
            onNetworkAvailable = {
                isConnected = true
                viewModel.getMovieDetails(movieId)
            },
            onNetworkLost = {
                isConnected = false
            }
        )
    }

    DisposableEffect(Unit) {
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(connectivityReceiver, filter)
        onDispose {
            context.unregisterReceiver(connectivityReceiver)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (!isConnected) {
            FailureCheck(apiState = ApiState.Failure("No internet connection"), context = context)
        } else {
            when (movieDetailsState) {
                is ApiState.Loading -> {
                    LoadingState()
                }

                is ApiState.Success -> {
                    val movieDetails = movieDetailsState.data as? MovieDetailsResponse
                    if (movieDetails != null) {
                        MovieDetailContent(
                            movieDetails = movieDetails,
                            onBackPressed = onBackPressed
                        )
                    }
                }

                is ApiState.Failure -> FailureCheck(
                    apiState = movieDetailsState,
                    context = context
                )
            }
        }
    }
}
