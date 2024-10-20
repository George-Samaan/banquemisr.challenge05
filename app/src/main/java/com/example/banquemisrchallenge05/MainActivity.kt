package com.example.banquemisrchallenge05

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.banquemisrchallenge05.data.network.RetrofitInstance
import com.example.banquemisrchallenge05.data.remote.RemoteDataSourceImpl
import com.example.banquemisrchallenge05.data.repository.RepositoryImpl
import com.example.banquemisrchallenge05.ui.features.moviesDetailed.viewModel.MoviesDetailsViewModel
import com.example.banquemisrchallenge05.ui.features.moviesDetailed.viewModel.MoviesDetailsViewModelFactory
import com.example.banquemisrchallenge05.ui.features.moviesHome.viewModel.MoviesHomeViewModel
import com.example.banquemisrchallenge05.ui.features.moviesHome.viewModel.MoviesHomeViewModelFactory
import com.example.banquemisrchallenge05.ui.navigation.NavigationGraph
import com.example.banquemisrchallenge05.ui.theme.Banquemisrchallenge05Theme

class MainActivity : ComponentActivity() {
    private lateinit var homeViewModel: MoviesHomeViewModel
    private lateinit var movieDetailsViewModel: MoviesDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModelFactory = MoviesHomeViewModelFactory(
            RepositoryImpl(
                RemoteDataSourceImpl(RetrofitInstance.apiServices)
            )
        )
        homeViewModel = ViewModelProvider(this, viewModelFactory)[MoviesHomeViewModel::class.java]

        val movieDetailsViewModelFactory = MoviesDetailsViewModelFactory(
            RepositoryImpl(
                RemoteDataSourceImpl(RetrofitInstance.apiServices)
            )
        )
        movieDetailsViewModel = ViewModelProvider(
            this,
            movieDetailsViewModelFactory
        )[MoviesDetailsViewModel::class.java]

        setContent {
            Banquemisrchallenge05Theme {
                val navController = rememberNavController()
                NavigationGraph(navController, homeViewModel, movieDetailsViewModel)
            }
        }
    }
}