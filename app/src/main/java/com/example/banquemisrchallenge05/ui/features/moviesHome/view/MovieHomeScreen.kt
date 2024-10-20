package com.example.banquemisrchallenge05.ui.features.moviesHome.view

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.banquemisrchallenge05.R
import com.example.banquemisrchallenge05.data.model.MovieResponse
import com.example.banquemisrchallenge05.data.network.ApiState
import com.example.banquemisrchallenge05.ui.features.moviesHome.viewModel.MoviesHomeViewModel
import com.example.banquemisrchallenge05.ui.sharedComponents.AnimationIndicator
import com.example.banquemisrchallenge05.ui.sharedComponents.MoviesList
import com.example.banquemisrchallenge05.ui.sharedComponents.ReusableLottie
import com.example.banquemisrchallenge05.utils.ConnectivityReceiver
import com.example.banquemisrchallenge05.utils.isNetworkAvailable
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Suppress("DEPRECATION")
@Composable
fun MoviesHomeScreen(viewModel: MoviesHomeViewModel, navController: NavController) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val nowPlayingMoviesState = viewModel.nowPlayingMovies.collectAsState().value
    val popularMoviesState = viewModel.popularMovies.collectAsState().value
    val upcomingMoviesState = viewModel.upcomingMovies.collectAsState().value
    val isRefreshing = remember { mutableStateOf(false) }
    val context = LocalContext.current
    var isConnected by remember { mutableStateOf(true) }

    val connectivityReceiver = remember {
        ConnectivityReceiver(
            onNetworkAvailable = {
                isConnected = true
                viewModel.getAllData()
            },
            onNetworkLost = {
                isConnected = false
            }
        )
    }

    DisposableEffect(Unit) {
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(connectivityReceiver, filter)
        onDispose {
            context.unregisterReceiver(connectivityReceiver)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getAllData()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF))
    ) {
        if (!isConnected) {
            FailureCheck(
                apiState = ApiState.Failure("No internet connection"),
                context = context
            )
        } else {
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing.value),
                onRefresh = {
                    isRefreshing.value = true
                    viewModel.getAllData()
                    isRefreshing.value = false
                }
            ) {
                Column(
                    modifier = Modifier.padding(top = 50.dp)
                ) {
                    TabsSection(
                        selectedTab = selectedTab,
                        onTabSelected = { selectedTab = it }
                    )
                    MoviesContent(
                        selectedTab = selectedTab,
                        nowPlayingMoviesState = nowPlayingMoviesState,
                        popularMoviesState = popularMoviesState,
                        upcomingMoviesState = upcomingMoviesState,
                        onMovieClick = { movieId ->
                            navController.navigate("movieDetails/$movieId")
                        }
                    )
                    AnimationIndicator(
                        nowPlayingMoviesState = nowPlayingMoviesState,
                        popularMoviesState = popularMoviesState,
                        upcomingMoviesState = upcomingMoviesState
                    )
                }
            }
        }
    }
}

@Composable
fun TabsSection(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    val tabTitles = listOf("Now Playing", "Popular", "Upcoming")

    Box(
        modifier = Modifier
            .padding(horizontal = 6.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFDDDDDD))
    ) {
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = Color(0xFFDDDDDD),
            contentColor = Color.Black,
            modifier = Modifier
                .height(70.dp)
                .padding(horizontal = 8.dp),
            indicator = { tabPositions ->
                Box(
                    Modifier
                        .tabIndicatorOffset(tabPositions[selectedTab])
                        .height(4.dp)
                        .fillMaxWidth()
                        .background(Color.Black, RoundedCornerShape(8.dp))
                )
            }
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    text = {
                        TabTitle(title, selectedTab == index)
                    },
                    modifier = Modifier
                        .padding(vertical = 12.dp),
                    selected = selectedTab == index,
                    onClick = { onTabSelected(index) },
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
        }
    }
}

@Composable
fun TabTitle(title: String, isSelected: Boolean) {
    Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
        color = if (isSelected) Color.Black else Color.Gray
    )
}

@Composable
fun MoviesContent(
    selectedTab: Int,
    nowPlayingMoviesState: ApiState,
    popularMoviesState: ApiState,
    upcomingMoviesState: ApiState,
    onMovieClick: (Int) -> Unit
) {
    when (selectedTab) {
        0 -> MoviesSections(
            apiState = nowPlayingMoviesState,
            onMovieClick = onMovieClick
        )

        1 -> MoviesSections(
            apiState = popularMoviesState,
            onMovieClick = onMovieClick
        )

        2 -> MoviesSections(
            apiState = upcomingMoviesState,
            onMovieClick = onMovieClick
        )
    }
}

@Composable
fun MoviesSections(
    apiState: ApiState,
    onMovieClick: (Int) -> Unit
) {
    val context = LocalContext.current

    when (apiState) {
        is ApiState.Loading -> {}
        is ApiState.Success -> {
            if (apiState.data is MovieResponse) {
                val movies = apiState.data.results
                MoviesList(movies = movies, onMovieClick = onMovieClick)
            }
        }

        is ApiState.Failure -> {
            FailureCheck(apiState, context)
        }
    }
}

@Composable
fun FailureCheck(apiState: ApiState.Failure, context: Context) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ReusableLottie(R.raw.no_internet, 300.dp, 1f)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = apiState.message,
                color = Color.Red,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    handleTryAgainClick(context)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(
                    text = "Open Wifi ",
                    color = Color.White
                )
            }
        }
    }
}

fun handleTryAgainClick(context: Context) {
    if (!isNetworkAvailable(context)) {
        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
        context.startActivity(intent)
    }
}