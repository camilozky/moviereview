package com.globant.moviereview.utils

import android.net.ConnectivityManager

class ConnectivityChecker(private val connectivityManager: ConnectivityManager) {

    val isConnected: Boolean
        get() = connectivityManager.activeNetworkInfo?.isConnected ?: false
}
