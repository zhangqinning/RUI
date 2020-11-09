package com.music.lib_network.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.music.lib_architecture.utils.Utils

fun isConnected(): Boolean {

    val networkTypes = listOf(NetworkCapabilities.TRANSPORT_WIFI, NetworkCapabilities.TRANSPORT_CELLULAR)

    val connectivity = Utils.getApp().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    connectivity.activeNetwork?.apply {
        connectivity.getNetworkCapabilities(this)?.apply {
            for (netWorkType in networkTypes) {
                if (this.hasTransport(netWorkType)) {
                    return true
                }
            }
        }
    }
    return false
}