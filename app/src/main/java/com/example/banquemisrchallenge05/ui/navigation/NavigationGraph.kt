package com.example.banquemisrchallenge05.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.banquemisrchallenge05.ui.features.moviesDetailed.view.MovieDetailsScreen
import com.example.banquemisrchallenge05.ui.features.moviesDetailed.viewModel.MoviesDetailsViewModel
import com.example.banquemisrchallenge05.ui.features.moviesHome.view.MoviesHomeScreen
import com.example.banquemisrchallenge05.ui.features.moviesHome.viewModel.MoviesHomeViewModel
import com.example.banquemisrchallenge05.ui.features.splash.SplashScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationGraph(
    navController: NavHostController,
    homeViewModel: MoviesHomeViewModel,
    movieDetailsViewModel: MoviesDetailsViewModel
) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen {
                navController.navigate("home") {
                    popUpTo("splash") {
                        inclusive = true
                    }
                }
            }
        }
        composable("home") {
            MoviesHomeScreen(viewModel = homeViewModel, navController = navController)
        }
        composable("movieDetails/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toInt() ?: 0
            MovieDetailsScreen(movieId = movieId, viewModel = movieDetailsViewModel,
                onBackPressed = { navController.popBackStack() })
        }
    }
}