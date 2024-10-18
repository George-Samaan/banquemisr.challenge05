package com.example.banquemisrchallenge05

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.banquemisrchallenge05.data.network.RetrofitInstance
import com.example.banquemisrchallenge05.data.remote.RemoteDataSourceImpl
import com.example.banquemisrchallenge05.data.repository.RepositoryImpl
import com.example.banquemisrchallenge05.ui.features.moviesHomeScreen.view.MoviesHomeScreen
import com.example.banquemisrchallenge05.ui.features.moviesHomeScreen.viewModel.MoviesHomeViewModel
import com.example.banquemisrchallenge05.ui.features.moviesHomeScreen.viewModel.MoviesHomeViewModelFactory
import com.example.banquemisrchallenge05.ui.theme.Banquemisrchallenge05Theme

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MoviesHomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModelFactory = MoviesHomeViewModelFactory(
            RepositoryImpl(
                RemoteDataSourceImpl(RetrofitInstance.apiServices)
            )
        )
        viewModel = ViewModelProvider(this, viewModelFactory)[MoviesHomeViewModel::class.java]
        setContent {
            Banquemisrchallenge05Theme {
                MoviesHomeScreen(viewModel)
            }
        }
    }
}