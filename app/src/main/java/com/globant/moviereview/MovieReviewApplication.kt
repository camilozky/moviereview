package com.globant.moviereview

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.globant.moviereview.utils.ConnectivityChecker

class MovieReviewApplication : Application() {

    lateinit var connectivityChecker: ConnectivityChecker

    override fun onCreate() {
        super.onCreate()

        connectivityChecker = ConnectivityChecker(getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
    }
}
