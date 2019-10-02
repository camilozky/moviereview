package com.globant.moviereview.utils

import android.net.ConnectivityManager

/**
 * ConnectivityChecker
 *
 * To know the status of internet access with SO
 *
 * @author juan.rendon
 */

class ConnectivityChecker(private val connectivityManager: ConnectivityManager) {

    val isConnected: Boolean
        get() = connectivityManager.activeNetworkInfo?.isConnected ?: false
}
