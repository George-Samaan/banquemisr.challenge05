package com.example.banquemisrchallenge05.ui.features.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.banquemisrchallenge05.R
import com.example.banquemisrchallenge05.ui.sharedComponents.ReusableLottie
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ReusableLottie(R.raw.splash, 400.dp, 1f)

        LaunchedEffect(Unit) {
            delay(3000)
            onTimeout()
        }
    }
}