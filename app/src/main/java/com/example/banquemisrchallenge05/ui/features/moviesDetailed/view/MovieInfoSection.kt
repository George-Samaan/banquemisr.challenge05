package com.example.banquemisrchallenge05.ui.features.moviesDetailed.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banquemisrchallenge05.data.model.MovieDetailsResponse
import com.example.banquemisrchallenge05.ui.sharedComponents.Gap
import com.example.banquemisrchallenge05.ui.theme.blue
import com.example.banquemisrchallenge05.utils.FormatDTHelper.formatReleaseDate
import com.example.banquemisrchallenge05.utils.FormatDTHelper.formatRuntimeToHMMSS
import com.smarttoolfactory.ratingbar.RatingBar
import com.smarttoolfactory.ratingbar.model.Shimmer

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MovieInfoSection(movieDetails: MovieDetailsResponse) {
    Column(modifier = Modifier.padding(horizontal = 12.dp)) {
        MainAnimatedText(
            text = movieDetails.title,
            fontWeight = FontWeight.Bold,
            fontSize = 36,
            color = blue,
            animationDuration = 1000,
            style = MaterialTheme.typography.headlineSmall
        )

        if (!movieDetails.tagline.isNullOrEmpty()) {
            SubAnimatedText(
                text = "\"${movieDetails.tagline}\"",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 20,
                animationDuration = 1500,
                color = Color.Gray
            )
        }

        Gap(6.dp)
        RatingRow(movieDetails)
        Gap(12.dp)
        MainAnimatedText(
            text = movieDetails.genres.joinToString(", ") { it.name },
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20,
            color = blue
        )
        MovieStatusAndLanguage(movieDetails)
        Gap(12.dp)
    }
}

@Composable
fun RatingRow(movieDetails: MovieDetailsResponse) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        RatingBar(
            rating = movieDetails.vote_average.toFloat() / 2,
            space = 2.dp,
            imageVectorEmpty = Icons.Default.StarOutline,
            imageVectorFFilled = Icons.Default.StarRate,
            tintEmpty = Color.Gray,
            itemSize = 27.dp,
            gestureEnabled = false,
            animationEnabled = true,
            shimmer = Shimmer(
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 5000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                ),
                drawBorder = false,
            ),
        )
        Text(
            modifier = Modifier.padding(top = 4.dp, start = 3.dp),
            text = "(${movieDetails.vote_count})",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            fontSize = 14.sp
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MovieStatusAndLanguage(movieDetails: MovieDetailsResponse) {
    Row {
        SubAnimatedText(
            text = "${movieDetails.status} On ",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            fontSize = 16,
            animationDuration = 1800
        )
        SubAnimatedText(
            text = formatReleaseDate(movieDetails.release_date),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            fontSize = 16,
            animationDuration = 1800
        )
    }

    Row {
        SubAnimatedText(
            text = "${formatRuntimeToHMMSS(movieDetails.runtime!!)}  ",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            fontSize = 14,
            animationDuration = 1800
        )
        SubAnimatedText(
            text = movieDetails.original_language.uppercase(),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            fontSize = 14,
            animationDuration = 1800
        )
    }
}