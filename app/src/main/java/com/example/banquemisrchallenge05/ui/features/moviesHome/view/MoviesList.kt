package com.example.banquemisrchallenge05.ui.features.moviesHome.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.banquemisrchallenge05.R
import com.example.banquemisrchallenge05.data.model.Movie
import com.example.banquemisrchallenge05.utils.Constants
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@Composable
fun MoviesList(
    movies: LazyPagingItems<Movie>,
    onMovieClick: (Int) -> Unit,
    pagerState: PagerState // Keep pagerState parameter
) {
    // Track the current page index
    LaunchedEffect(pagerState.currentPage) {
        // Here you can handle any logic when the current page changes if needed
    }

    HorizontalPager(
        count = movies.itemCount,
        state = pagerState,
        modifier = Modifier.fillMaxWidth(),
        itemSpacing = 18.dp,
        contentPadding = PaddingValues(horizontal = 80.dp, vertical = 50.dp)
    ) { page ->
        movies[page]?.let { movie ->
            MovieCard(
                movie = movie,
                onMovieClick = onMovieClick,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.95f)
                    .graphicsLayer {
                        val pageOffset =
                            (pagerState.currentPage - page) + pagerState.currentPageOffset
                        scaleX = 1 - (0.1f * pageOffset)
                        scaleY = 1 - (0.1f * pageOffset)
                    }
            )
        }
    }
}

@Composable
fun MovieCard(movie: Movie, onMovieClick: (Int) -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .clickable { onMovieClick(movie.id) }
            .clip(RoundedCornerShape(18.dp)),
        elevation = CardDefaults.cardElevation(15.dp)
    ) {
        Box {
            val painter = rememberAsyncImagePainter(
                model = "${Constants.IMAGE_BASE_URL}${movie.poster_path}",
                error = painterResource(R.drawable.ic_launcher_foreground),
            )
            MovieImage(painter = painter, contentDescription = movie.title)
            if (painter.state is AsyncImagePainter.State.Loading) {
                LoadingIndicator()
            }
            MovieInfo(movie = movie, modifier = Modifier.align(Alignment.BottomStart))
        }
    }
}
