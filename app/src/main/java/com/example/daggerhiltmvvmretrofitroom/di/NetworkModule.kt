package com.example.daggerhiltmvvmretrofitroom.di

import android.content.Context
import com.example.daggerhiltmvvmretrofitroom.model.remote.ApiService
import com.example.daggerhiltmvvmretrofitroom.model.remote.NetworkInterceptor
import com.example.daggerhiltmvvmretrofitroom.model.remote.NetworkUtils
import com.example.daggerhiltmvvmretrofitroom.model.remote.RemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun providesLogInterceptors():HttpLoggingInterceptor{
        var logInterceptor = HttpLoggingInterceptor().apply{
            level = HttpLoggingInterceptor.Level.BODY
        }
        return logInterceptor
    }

//    @Provides
//    @Singleton
//    fun providesNetworkUtils(@ApplicationContext context: Context, logInterceptor: HttpLoggingInterceptor): NetworkUtils {
//        return NetworkUtils(context, logInterceptor)
//    }

    @Provides
    @Singleton
    fun providesClient(
        logInterceptor: HttpLoggingInterceptor,
        networkInterceptor: NetworkInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(logInterceptor)
            addInterceptor(networkInterceptor)
        }.build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient): Retrofit{
        val retrofit: Retrofit by lazy {
            Retrofit.Builder().apply {
                baseUrl("https://api.restful-api.dev/")
                client(client)
                addConverterFactory(GsonConverterFactory.create())
            }.build()
        }
        return retrofit
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit):ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesRemoteRepository(apiService: ApiService): RemoteRepository{
        return RemoteRepository(apiService)
    }
}