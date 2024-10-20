package com.example.banquemisrchallenge05.ui.features.moviesDetailed.view

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.banquemisrchallenge05.data.model.MovieDetailsResponse
import com.example.banquemisrchallenge05.data.network.ApiState
import com.example.banquemisrchallenge05.ui.features.moviesDetailed.viewModel.MoviesDetailsViewModel
import com.example.banquemisrchallenge05.ui.features.moviesHome.view.FailureCheck
import com.example.banquemisrchallenge05.ui.sharedComponents.Gap
import com.example.banquemisrchallenge05.utils.ConnectivityReceiver
import com.example.banquemisrchallenge05.utils.Constants
import com.example.banquemisrchallenge05.utils.FormatDTHelper.formatReleaseDate
import com.example.banquemisrchallenge05.utils.FormatDTHelper.formatRuntimeToHMMSS
import com.smarttoolfactory.ratingbar.RatingBar
import com.smarttoolfactory.ratingbar.model.Shimmer

@Composable
fun MovieDetailsScreen(
    movieId: Int,
    viewModel: MoviesDetailsViewModel,
    onBackPressed: () -> Unit
) {
    val movieDetailsState = viewModel.movieDetails.collectAsState().value
    val context = LocalContext.current
    var isConnected by remember { mutableStateOf(true) }

    // ConnectivityReceiver for monitoring network state
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

    // Register and unregister the receiver
    DisposableEffect(Unit) {
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(connectivityReceiver, filter)
        onDispose {
            context.unregisterReceiver(connectivityReceiver)
        }
    }

    // UI content
    Box(modifier = Modifier.fillMaxSize()) {
        if (!isConnected) {
            FailureCheck(apiState = ApiState.Failure("No internet connection"), context = context)
        } else {
            when (movieDetailsState) {
                is ApiState.Loading -> {
                    CircularProgressIndicator()
                }

                is ApiState.Success -> {
                    val movieDetails = movieDetailsState.data as? MovieDetailsResponse
                    if (movieDetails != null) {
                        MovieDetailContent(
                            movieDetails,
                            onBackPressed
                        )
                    }
                }

                is ApiState.Failure -> {
                    FailureCheck(apiState = movieDetailsState, context = context)
                }
            }
        }
    }
}

@Composable
fun MovieDetailContent(movieDetails: MovieDetailsResponse, onBackPressed: () -> Unit) {
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        isVisible = true
    }
    AnimatedVisibility(
        visible = isVisible,
        enter = scaleIn(animationSpec = tween(durationMillis = 500)) + slideInHorizontally()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                val posterPainter = rememberImagePainter(
                    data = "${Constants.IMAGE_BASE_URL}${movieDetails.poster_path}"
                )
                Image(
                    painter = posterPainter,
                    contentDescription = movieDetails.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp),
                    contentScale = ContentScale.FillBounds
                )

                // Floating Action Button for back navigation
                FloatingActionButton(
                    onClick = onBackPressed,
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.TopStart),
                    contentColor = Color.Black,
                    containerColor = Color.White,
                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
            Gap(8.dp)

            Column(
                modifier = Modifier.padding(horizontal = 12.dp)
            ) {
                AnimatedText(
                    text = movieDetails.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 34,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "\"${movieDetails.tagline}\"",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 18.sp,
                    color = Color.Gray
                )
                Gap(6.dp)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingBar(
                        rating = movieDetails.vote_average.toFloat() / 2,
                        space = 1.dp,
                        imageVectorEmpty = Icons.Default.StarOutline,
                        imageVectorFFilled = Icons.Default.StarRate,
                        tintEmpty = Color.Gray,
                        itemSize = 20.dp,
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
                        fontSize = 10.sp
                    )
                }

                Gap(16.dp)
                Text(
                    text = "${movieDetails.genres.joinToString(", ") { it.name }}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.bodyMedium
                )
                Row {
                    Text(
                        text = "${movieDetails.status} On ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        Text(
                            text = formatReleaseDate(movieDetails.release_date), // Use the formatting function
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                    }
                }
                Row {
                    Text(
                        text = "${formatRuntimeToHMMSS(movieDetails.runtime!!)}  ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                    Text(
                        text = "${movieDetails.original_language.uppercase()}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }

                Gap(16.dp)
                AnimatedText(
                    text = "Overview",
                    fontSize = 20,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.headlineSmall.copy(color = MaterialTheme.colorScheme.primary)
                )
                Gap(4.dp)
            }
            AnimatedText(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFEFEFEF))
                    .padding(12.dp),
                text = movieDetails.overview.toString(),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                animationDuration = 1200
            )
        }
    }
}


@Composable
fun AnimatedText(
    modifier: Modifier? = Modifier,
    text: String,
    style: TextStyle,
    fontWeight: FontWeight? = null,
    fontSize: Int? = null,
    color: Color? = null,
    animationDuration: Int = 1000
) {
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        isVisible = true
    }
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInHorizontally(animationSpec = tween(durationMillis = animationDuration)) + scaleIn(
            initialScale = 0.5f,
        )
    ) {
        Text(
            modifier = modifier ?: Modifier,
            text = text,
            style = style,
            fontWeight = fontWeight,
            fontSize = fontSize?.sp ?: style.fontSize,
            color = color ?: style.color
        )
    }
}
