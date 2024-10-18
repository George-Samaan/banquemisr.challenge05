package com.example.banquemisrchallenge05.ui.features.moviesHomeScreen.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banquemisrchallenge05.data.model.MovieResponse
import com.example.banquemisrchallenge05.data.network.ApiState
import com.example.banquemisrchallenge05.ui.features.moviesHomeScreen.viewModel.MoviesHomeViewModel
import com.example.banquemisrchallenge05.ui.sharedComponents.AnimationIndicator
import com.example.banquemisrchallenge05.ui.sharedComponents.MoviesList

@Composable
fun MoviesHomeScreen(viewModel: MoviesHomeViewModel) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val nowPlayingMoviesState = viewModel.nowPlayingMovies.collectAsState().value
    val popularMoviesState = viewModel.popularMovies.collectAsState().value
    val upcomingMoviesState = viewModel.upcomingMovies.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.getAllData()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF))
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
                upcomingMoviesState = upcomingMoviesState
            )
            AnimationIndicator(
                nowPlayingMoviesState = nowPlayingMoviesState,
                popularMoviesState = popularMoviesState,
                upcomingMoviesState = upcomingMoviesState
            )
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
    upcomingMoviesState: ApiState
) {
    when (selectedTab) {
        0 -> MoviesSections(apiState = nowPlayingMoviesState)
        1 -> MoviesSections(apiState = popularMoviesState)
        2 -> MoviesSections(apiState = upcomingMoviesState)
    }
}

@Composable
fun MoviesSections(apiState: ApiState) {
    when (apiState) {
        is ApiState.Loading -> {
        }

        is ApiState.Success -> {
            if (apiState.data is MovieResponse) {
                val movies = apiState.data.results
                MoviesList(movies = movies)
            }
        }

        is ApiState.Failure -> {
            Log.e("MoviesHomeScreen", "Error fetching : ${apiState.message.message}")
        }
    }
}