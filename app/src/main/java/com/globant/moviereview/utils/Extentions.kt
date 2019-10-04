package com.globant.moviereview.utils

import android.content.Context
import android.net.ConnectivityManager

// TODO make a javadoc
fun Context.hasConnection(): Boolean {
    return (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo?.isConnected
        ?: false
}