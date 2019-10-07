package com.globant.moviereview.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Context.hasConnection
 *
 * The functionality that verifies if there is internet access in the Context was extended
 *
 * @author david.mazo
 */

fun Context.hasConnection(): Boolean {
    return (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo?.isConnected
            ?: false
}
