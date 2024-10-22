package com.example.banquemisrchallenge05.ui.features.moviesHome.view

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.banquemisrchallenge05.data.model.Movie
import com.example.banquemisrchallenge05.ui.features.moviesHome.viewModel.MoviesHomeViewModel
import com.example.banquemisrchallenge05.ui.sharedComponents.Gap
import com.example.banquemisrchallenge05.utils.ConnectivityReceiver

@Suppress("DEPRECATION")
@Composable
fun MoviesHomeScreen(viewModel: MoviesHomeViewModel, navController: NavController) {
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }
    val currentPageIndex = rememberSaveable { mutableListOf(0, 0, 0) }

    val popularItems = viewModel.popular.collectAsLazyPagingItems()
    val nowPlayingItems = viewModel.nowPlaying.collectAsLazyPagingItems()
    val upcomingItems = viewModel.upcoming.collectAsLazyPagingItems()

    val context = LocalContext.current
    val isConnected = remember { mutableStateOf(true) }

    val connectivityReceiver = remember {
        ConnectivityReceiver(
            onNetworkAvailable = {
                isConnected.value = true
                refreshMovieData(nowPlayingItems, popularItems, upcomingItems)
            },
            onNetworkLost = {
                isConnected.value = false
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

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFFFFF))
    ) {
        Column {
            Gap(50.dp)
            TabsSection(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
            MoviesContent(
                selectedTab = selectedTab,
                nowPlayingMoviesState = nowPlayingItems,
                popularMoviesState = popularItems,
                upcomingMoviesState = upcomingItems,
                onMovieClick = { movieId ->
                    navController.navigate("movieDetails/$movieId")
                },
                currentPageIndex = currentPageIndex
            )
            AnimationIndicator(
                nowPlayingMoviesState = nowPlayingItems,
                popularMoviesState = popularItems,
                upcomingMoviesState = upcomingItems
            )
        }
    }
}


private fun refreshMovieData(
    nowPlayingItems: LazyPagingItems<Movie>,
    popularItems: LazyPagingItems<Movie>,
    upcomingItems: LazyPagingItems<Movie>
) {
    nowPlayingItems.refresh()
    popularItems.refresh()
    upcomingItems.refresh()
}