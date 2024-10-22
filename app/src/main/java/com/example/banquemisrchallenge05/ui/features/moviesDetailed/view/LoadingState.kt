package com.example.banquemisrchallenge05.ui.features.moviesDetailed.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.banquemisrchallenge05.R
import com.example.banquemisrchallenge05.ui.sharedComponents.ReusableLottie

@Composable
fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ReusableLottie(
            R.raw.loading,
            size = 300.dp,
            speed = 1f
        )
    }
}