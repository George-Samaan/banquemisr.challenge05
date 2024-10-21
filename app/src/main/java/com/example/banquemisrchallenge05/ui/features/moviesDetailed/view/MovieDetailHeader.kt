package com.example.banquemisrchallenge05.ui.features.moviesDetailed.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.banquemisrchallenge05.data.model.MovieDetailsResponse
import com.example.banquemisrchallenge05.utils.Constants

@Composable
fun MovieDetailHeader(
    movieDetails: MovieDetailsResponse,
    onBackPressed: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        val posterPainter = rememberAsyncImagePainter(
            model = "${Constants.IMAGE_BASE_URL}${movieDetails.poster_path}"
        )
        Image(
            painter = posterPainter,
            contentDescription = movieDetails.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),
            contentScale = ContentScale.FillBounds
        )

        FloatingActionButton(
            onClick = onBackPressed,
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.TopStart),
            contentColor = Color.Black,
            containerColor = Color.White,
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }
    }
}