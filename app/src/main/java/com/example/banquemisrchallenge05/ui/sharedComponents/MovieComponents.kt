package com.example.banquemisrchallenge05.ui.sharedComponents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.banquemisrchallenge05.data.model.Movie
import com.example.banquemisrchallenge05.utils.Constants

@Composable
fun MoviesList(movies: List<Movie>) {
    val rows = movies.chunked(6)
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(rows) { rowMovies ->
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                items(rowMovies) { movie ->
                    AnimatedCard(
                        movie = movie,
                    )
                }
            }
        }
    }
}

@Composable
fun AnimatedCard(movie: Movie) {
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        isVisible = true
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = scaleIn(animationSpec = tween(durationMillis = 700)),
        exit = scaleOut(animationSpec = tween(durationMillis = 700))
    ) {
        MovieCard(movie = movie)
    }
}


@Composable
fun MovieCard(movie: Movie) {
    Card(
        modifier = Modifier
            .width(175.dp)
            .height(230.dp)
            .padding(horizontal = 10.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(18.dp)),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box {
            Image(
                painter = rememberAsyncImagePainter("${Constants.IMAGE_BASE_URL}${movie.poster_path}"),
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)),
                            startY = 0f,
                            endY = 50f
                        ),
                        shape = RoundedCornerShape(18.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .padding(9.dp)
                ) {
                    Text(
                        text = movie.title,
                        fontSize = 17.sp,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        ),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Text(
                        fontSize = 15.sp,
                        text = movie.release_date,
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.LightGray),
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }
        }
    }
}