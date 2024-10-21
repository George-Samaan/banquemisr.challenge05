package com.example.banquemisrchallenge05.ui.features.moviesHome.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.banquemisrchallenge05.data.model.Movie
import com.example.banquemisrchallenge05.data.network.ApiState
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

@Composable
fun MoviesContent(
    selectedTab: Int,
    nowPlayingMoviesState: LazyPagingItems<Movie>,
    popularMoviesState: LazyPagingItems<Movie>,
    upcomingMoviesState: LazyPagingItems<Movie>,
    onMovieClick: (Int) -> Unit,
    currentPageIndex: MutableList<Int>
) {
    val pagerState = rememberPagerState(initialPage = currentPageIndex[selectedTab])
    LaunchedEffect(selectedTab) {
        pagerState.scrollToPage(currentPageIndex[selectedTab])
    }

    when (selectedTab) {
        0 -> MoviesSections(
            movies = nowPlayingMoviesState,
            onMovieClick = onMovieClick,
            pagerState = pagerState
        )

        1 -> MoviesSections(
            movies = popularMoviesState,
            onMovieClick = onMovieClick,
            pagerState = pagerState
        )

        2 -> MoviesSections(
            movies = upcomingMoviesState,
            onMovieClick = onMovieClick,
            pagerState = pagerState
        )
    }

    LaunchedEffect(pagerState.currentPage) {
        currentPageIndex[selectedTab] = pagerState.currentPage
    }
}

@Suppress("DEPRECATION")
@Composable
fun MoviesSections(
    movies: LazyPagingItems<Movie>,
    onMovieClick: (Int) -> Unit,
    pagerState: PagerState
) {
    when (movies.loadState.refresh) {
        is LoadState.Loading -> {}

        is LoadState.Error -> {
            val error = movies.loadState.refresh as LoadState.Error
            FailureCheck(
                ApiState.Failure(error.error.message ?: "Unknown Error"),
                LocalContext.current
            )
        }

        else -> {
            MoviesList(movies = movies, onMovieClick = onMovieClick, pagerState = pagerState)
        }
    }
}