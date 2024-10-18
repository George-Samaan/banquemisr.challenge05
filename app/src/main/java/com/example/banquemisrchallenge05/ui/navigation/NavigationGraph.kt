package com.example.banquemisrchallenge05.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.banquemisrchallenge05.ui.features.moviesHome.view.MoviesHomeScreen
import com.example.banquemisrchallenge05.ui.features.moviesHome.viewModel.MoviesHomeViewModel
import com.example.banquemisrchallenge05.ui.features.splash.SplashScreen

@Composable
fun NavigationGraph(navController: NavHostController, viewModel: MoviesHomeViewModel) {
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
            MoviesHomeScreen(viewModel = viewModel)
        }
    }
}