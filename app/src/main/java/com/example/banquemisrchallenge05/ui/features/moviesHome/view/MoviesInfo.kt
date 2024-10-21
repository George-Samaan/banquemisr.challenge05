package com.example.banquemisrchallenge05.ui.features.moviesHome.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banquemisrchallenge05.data.model.Movie

@Composable
fun MovieInfo(movie: Movie, modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
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
            modifier = Modifier.padding(9.dp)
        ) {
            Text(
                text = movie.title,
                fontSize = 20.sp,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                fontSize = 17.sp,
                text = movie.release_date,
                style = MaterialTheme.typography.bodySmall.copy(color = Color.LightGray),
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}