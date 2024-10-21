package com.example.banquemisrchallenge05.ui.features.moviesHome.view

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MovieImage(
    painter: Painter,
    contentDescription: String,
    scaleRange: Pair<Float, Float> = 1f to 1.25f,
    translateRangeX: Float = 20f,
    translateRangeY: Float = 20f,
    animationDurationMillis: Int = 6000,
    shadowElevation: Dp = 8.dp
) {
    val infiniteTransition = rememberInfiniteTransition(label = "image_animation")

    // Animating scale for the zoom in/out effect
    val scale by infiniteTransition.animateFloat(
        initialValue = scaleRange.first,
        targetValue = scaleRange.second,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDurationMillis, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "scale_animation"
    )

    // Animating translation for the slight movement (left-right / up-down)
    val translateX by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = translateRangeX,
        animationSpec = infiniteRepeatable(
            animation = tween(
                animationDurationMillis,
                easing = LinearOutSlowInEasing,
                delayMillis = 200
            ),
            repeatMode = RepeatMode.Reverse
        ), label = "translateX_animation"
    )

    val translateY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = translateRangeY,
        animationSpec = infiniteRepeatable(
            animation = tween(
                animationDurationMillis,
                easing = LinearOutSlowInEasing,
                delayMillis = 200
            ),
            repeatMode = RepeatMode.Reverse
        ), label = "translateY_animation"
    )

    // Image with animation, shadow, and rounded corners
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(12.dp))
            .shadow(
                elevation = shadowElevation,
                shape = RoundedCornerShape(12.dp)
            ) // Adding shadow for depth
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                translationX = translateX,
                translationY = translateY
            ),
        contentScale = ContentScale.Crop
    )
}

