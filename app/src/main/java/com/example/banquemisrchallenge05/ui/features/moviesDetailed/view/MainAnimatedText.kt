package com.example.banquemisrchallenge05.ui.features.moviesDetailed.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun MainAnimatedText(
    modifier: Modifier? = Modifier,
    text: String,
    style: TextStyle,
    fontWeight: FontWeight? = null,
    fontSize: Int? = null,
    color: Color? = null,
    animationDuration: Int = 1000
) {
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { isVisible = true }

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInHorizontally(animationSpec = tween(durationMillis = animationDuration)) + scaleIn(
            initialScale = 0.5f
        )
    ) {
        Text(
            modifier = modifier ?: Modifier,
            text = text,
            style = style,
            fontWeight = fontWeight,
            fontSize = fontSize?.sp ?: style.fontSize,
            color = color ?: style.color
        )
    }
}

