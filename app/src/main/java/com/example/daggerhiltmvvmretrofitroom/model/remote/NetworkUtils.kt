package com.example.daggerhiltmvvmretrofitroom.model.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

class NetworkUtils @Inject constructor(@ApplicationContext val context:Context, val logHttp: HttpLoggingInterceptor) {
    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}