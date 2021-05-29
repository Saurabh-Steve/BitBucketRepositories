package com.bitbucketrepositories.networking

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class APIClient {

    companion object {
        private var retrofit: Retrofit? = null
        fun  getClient(): Retrofit {

            val cacheSize = (5 * 1024 * 1024).toLong()
            val client = OkHttpClient.Builder()
                .build()

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            retrofit = Retrofit.Builder()
                .baseUrl("https://api.bitbucket.org/2.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()

            return retrofit!!
        }

    }


}