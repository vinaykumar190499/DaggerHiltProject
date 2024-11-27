package com.example.daggerhiltmvvmretrofitroom.model.remote

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class NetworkInterceptor @Inject constructor(
    @ApplicationContext private val context: Context,
    private val networkUtils: NetworkUtils
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkUtils.isInternetAvailable(context)) {
            throw NoInternetException("No internet connection")
        }
        return chain.proceed(chain.request())
    }
}
class NoInternetException(message: String) : IOException(message)