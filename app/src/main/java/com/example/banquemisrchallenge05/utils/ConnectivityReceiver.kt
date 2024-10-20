package com.example.banquemisrchallenge05.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo


class ConnectivityReceiver(
    private val onNetworkAvailable: () -> Unit,
    private val onNetworkLost: () -> Unit
) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val networkInfo =
            intent?.getParcelableExtra<NetworkInfo>(ConnectivityManager.EXTRA_NETWORK_INFO)
        if (networkInfo?.isConnected == true && networkInfo.type == ConnectivityManager.TYPE_WIFI) {
            // Wi-Fi is connected, call your fetch data method
            onNetworkAvailable()
        } else {
            onNetworkLost()

        }
    }
}