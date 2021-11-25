package com.hackathon.ahreview.data

import com.hackathon.ahreview.data.service.ClovaApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ClovaServer {
    val okHttpClient = OkHttpClient().newBuilder()
        .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    val clovaRetrofit = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl("https://naveropenapi.apigw.ntruss.com/")
        .build()
        .create(ClovaApi::class.java)
}