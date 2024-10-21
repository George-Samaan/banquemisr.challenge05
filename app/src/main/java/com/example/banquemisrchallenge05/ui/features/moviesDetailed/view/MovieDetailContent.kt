package com.example.banquemisrchallenge05.ui.features.moviesDetailed.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.banquemisrchallenge05.data.model.MovieDetailsResponse

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MovieDetailContent(
    movieDetails: MovieDetailsResponse,
    onBackPressed: () -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { isVisible = true }

    AnimatedVisibility(
        visible = isVisible,
        enter = scaleIn(animationSpec = tween(durationMillis = 900)) + fadeIn()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) {
            item {
                MovieDetailHeader(movieDetails, onBackPressed)
                MovieInfoSection(movieDetails)
                MovieOverviewSection(movieDetails.overview.toString())
            }
        }
    }
}