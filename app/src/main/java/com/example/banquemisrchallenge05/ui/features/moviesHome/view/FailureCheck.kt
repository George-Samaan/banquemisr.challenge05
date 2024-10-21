package com.example.banquemisrchallenge05.ui.features.moviesHome.view

import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banquemisrchallenge05.R
import com.example.banquemisrchallenge05.data.network.ApiState
import com.example.banquemisrchallenge05.ui.sharedComponents.ReusableLottie
import com.example.banquemisrchallenge05.utils.isNetworkAvailable


@Composable
fun FailureCheck(apiState: ApiState.Failure, context: Context) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ReusableLottie(R.raw.no_internet, 300.dp, 1f)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = apiState.message,
                color = Color.Red,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    handleTryAgainClick(context)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(
                    text = stringResource(R.string.open_wifi),
                    color = Color.White
                )
            }
        }
    }
}

fun handleTryAgainClick(context: Context) {
    if (!isNetworkAvailable(context)) {
        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
        context.startActivity(intent)
    }
}