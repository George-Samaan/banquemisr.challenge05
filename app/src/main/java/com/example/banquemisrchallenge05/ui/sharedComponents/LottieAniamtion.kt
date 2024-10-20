@file:Suppress("DEPRECATION")

package com.example.banquemisrchallenge05.ui.sharedComponents

import androidx.annotation.RawRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.banquemisrchallenge05.R
import com.example.banquemisrchallenge05.data.model.Movie

@Composable
fun ReusableLottie(
    @RawRes lottieRes: Int,
    size: Dp,
    speed: Float = 1f
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieRes))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        speed = speed,
    )

    Box(
        modifier = Modifier
            .size(size)
            .padding(top = 10.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Transparent)
    ) {

        LottieAnimation(
            composition = composition,
            progress = progress,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

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