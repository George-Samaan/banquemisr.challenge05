package com.example.banquemisrchallenge05.ui.features.moviesDetailed.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.banquemisrchallenge05.ui.sharedComponents.Gap
import com.example.banquemisrchallenge05.ui.theme.blue

@Composable
fun MovieOverviewSection(overview: String) {
    Column {
        MainAnimatedText(
            text = "Overview",
            modifier = Modifier.padding(start = 12.dp),
            fontSize = 24,
            fontWeight = FontWeight.SemiBold,
            color = blue,
            style = MaterialTheme.typography.bodyLarge,
        )
        Gap(4.dp)
        MainAnimatedText(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEFEFEF))
                .padding(12.dp),
            text = overview,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 18,
            color = Color.Black,
            animationDuration = 1200
        )
    }
    Gap(8.dp)
}