package com.example.banquemisrchallenge05.ui.features.moviesHome.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.banquemisrchallenge05.R
import com.example.banquemisrchallenge05.data.model.Movie
import com.example.banquemisrchallenge05.ui.sharedComponents.ReusableLottie

@Composable
fun AnimationIndicator(
    nowPlayingMoviesState: LazyPagingItems<Movie>,
    popularMoviesState: LazyPagingItems<Movie>,
    upcomingMoviesState: LazyPagingItems<Movie>
) {
    if (nowPlayingMoviesState.loadState.refresh is LoadState.Loading ||
        popularMoviesState.loadState.refresh is LoadState.Loading ||
        upcomingMoviesState.loadState.refresh is LoadState.Loading
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ReusableLottie(
                R.raw.loading,
                size = 300.dp,
                1f
            )
        }
    }
}