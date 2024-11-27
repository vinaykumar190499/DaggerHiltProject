//package com.example.daggerhiltmvvmretrofitroom.model.remote
//
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//object ApiClient {
//    val retrofit:Retrofit by lazy {
//
//        var logInterceptor = HttpLoggingInterceptor().apply{
//            level = HttpLoggingInterceptor.Level.BODY
//        }
//        val client = OkHttpClient.Builder().apply {
//            addInterceptor(logInterceptor)
//        }.build()
//        Retrofit.Builder().apply {
//            baseUrl("https://api.restful-api.dev/objects")
//            client(client)
//            addConverterFactory(GsonConverterFactory.create())
//        }.build()
//    }
//}